package com.example.gameshare.controller;

import com.example.gameshare.model.Role;
import com.example.gameshare.model.User;
import com.example.gameshare.service.RoleService;
import com.example.gameshare.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService; // For assigning roles

    private static final String ADMIN_ROLE_NAME = "管理员";
    private static final String USER_SESSION_KEY = "loggedInUser";

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // --- Helper method for security checks ---
    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute(USER_SESSION_KEY);
        if (user == null || user.get角色列表() == null) {
            return false;
        }
        return user.get角色列表().stream().anyMatch(role -> ADMIN_ROLE_NAME.equals(role.get角色名称()));
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    private User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute(USER_SESSION_KEY);
    }


    // --- Registration, Login, Logout ---

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // View: register.html
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            // Password is plain text as per previous requirements
            // Default role "普通用户" is assigned in UserServiceImpl
            userService.register(user);
            redirectAttributes.addFlashAttribute("successMessage", "注册成功！请登录。");
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, @RequestParam(value = "error", required = false) String error, HttpSession session) {
        if (isLoggedIn(session)) {
            return "redirect:/";
        }
        if (error != null) {
            model.addAttribute("errorMessage", "用户名或密码无效。");
        }
        model.addAttribute("user", new User()); // For form binding
        return "login"; // View: login.html
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute User userForm, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        User user = userService.login(userForm.get用户名(), userForm.get密码());
        if (user != null) {
            session.setAttribute(USER_SESSION_KEY, user);
            // User object from service should have roles loaded
            return "redirect:/";
        } else {
            model.addAttribute("user", userForm); // Keep username in form
            model.addAttribute("errorMessage", "用户名或密码无效。");
            return "login"; // Stay on login page with error
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("successMessage", "您已成功注销。");
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String userProfile(Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        User user = getLoggedInUser(session);
        model.addAttribute("user", userService.findById(user.getId())); // Refresh data
        return "profile"; // View: profile.html
    }

    // --- Admin: User Management ---

    @GetMapping("/admin/users")
    public String listUsers(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/"; // Or an error page "access-denied"
        }
        model.addAttribute("users", userService.findAllUsers());
        return "admin/users-list"; // View: admin/users-list.html
    }

    @GetMapping("/admin/users/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Integer id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        User user = userService.findById(id);
        if (user == null) {
            // Handle user not found
            return "redirect:/admin/users";
        }
        model.addAttribute("user", user);
        List<Role> allRoles = roleService.findAllRoles(); // Assuming RoleService has findAllRoles
        model.addAttribute("allRoles", allRoles);
        // User's current roles (IDs) for checkbox pre-selection
        List<Integer> userRoleIds = user.get角色列表() != null ? user.get角色列表().stream().map(Role::getId).collect(Collectors.toList()) : List.of();
        model.addAttribute("userRoleIds", userRoleIds);
        return "admin/user-edit"; // View: admin/user-edit.html
    }

    @PostMapping("/admin/users/edit")
    public String updateUser(@ModelAttribute User user, @RequestParam(value="roleIds", required = false) List<Integer> roleIds, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        // Update basic user info
        User existingUser = userService.findById(user.getId());
        if(existingUser == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "用户未找到。");
            return "redirect:/admin/users";
        }
        // Preserve creation time and password if not being changed in this form
        user.set创建时间(existingUser.get创建时间());
        if (user.get密码() == null || user.get密码().isEmpty()) {
            user.set密码(existingUser.get密码());
        }
        userService.updateUser(user);

        // Update roles - remove all existing roles and add selected ones
        // This requires a method in UserMapper/UserService like `deleteUserRolesByUserId`
        userService.clearUserRoles(user.getId()); // Assumes this method exists
        if (roleIds != null) {
            for (Integer roleId : roleIds) {
                userService.assignRoleToUser(user.getId(), roleId);
            }
        }
        redirectAttributes.addFlashAttribute("successMessage", "用户更新成功。");
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/update-roles") // More specific endpoint if only roles are updated
    public String updateUserRoles(@RequestParam("userId") Integer userId, @RequestParam(value = "roleIds", required = false) List<Integer> roleIds, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        User user = userService.findById(userId);
        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "用户未找到。");
            return "redirect:/admin/users";
        }

        userService.clearUserRoles(userId);
        if (roleIds != null) {
            for (Integer roleId : roleIds) {
                userService.assignRoleToUser(userId, roleId);
            }
        }
        redirectAttributes.addFlashAttribute("successMessage", "用户角色更新成功。");
        return "redirect:/admin/users/edit/" + userId;
    }


    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        // Ensure user is not deleting themselves, or handle appropriately
        User loggedInUser = getLoggedInUser(session);
        if (loggedInUser.getId().equals(id)) {
            redirectAttributes.addFlashAttribute("errorMessage", "不能删除当前登录的用户。");
            return "redirect:/admin/users";
        }

        userService.clearUserRoles(id); // Delete associated roles first
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("successMessage", "用户删除成功。");
        return "redirect:/admin/users";
    }
}
