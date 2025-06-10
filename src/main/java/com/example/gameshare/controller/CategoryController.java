package com.example.gameshare.controller;

import com.example.gameshare.model.Category;
import com.example.gameshare.model.User;
import com.example.gameshare.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/categories") // Base path for admin category management
public class CategoryController {

    private final CategoryService categoryService;

    private static final String ADMIN_ROLE_NAME = "管理员";
    private static final String USER_SESSION_KEY = "loggedInUser";

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute(USER_SESSION_KEY);
        if (user == null || user.get角色列表() == null) return false;
        return user.get角色列表().stream().anyMatch(role -> ADMIN_ROLE_NAME.equals(role.get角色名称()));
    }

    @GetMapping("") // Maps to /admin/categories
    public String listCategories(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login"; // Or an access denied page
        }
        model.addAttribute("categories", categoryService.findAllCategories());
        return "admin/categories-list"; // View: admin/categories-list.html
    }

    @GetMapping("/new") // Maps to /admin/categories/new
    public String showNewCategoryForm(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("category", new Category());
        return "admin/category-edit"; // View: admin/category-edit.html (serves for new and edit)
    }

    @PostMapping("/new") // Maps to /admin/categories/new
    public String createNewCategory(@ModelAttribute Category category, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        try {
            categoryService.createCategory(category);
            redirectAttributes.addFlashAttribute("successMessage", "分类创建成功！");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "创建失败: " + e.getMessage());
            return "redirect:/admin/categories/new"; // Stay on form with error
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}") // Maps to /admin/categories/edit/{id}
    public String showEditCategoryForm(@PathVariable("id") Integer id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        Category category = categoryService.findCategoryById(id);
        if (category == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "分类未找到。");
            return "redirect:/admin/categories";
        }
        model.addAttribute("category", category);
        return "admin/category-edit"; // View: admin/category-edit.html
    }

    @PostMapping("/edit") // Maps to /admin/categories/edit
    public String updateCategory(@ModelAttribute Category category, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        try {
            categoryService.updateCategory(category);
            redirectAttributes.addFlashAttribute("successMessage", "分类更新成功！");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "更新失败: " + e.getMessage());
            return "redirect:/admin/categories/edit/" + category.getId(); // Stay on form
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}") // Maps to /admin/categories/delete/{id}
    public String deleteCategory(@PathVariable("id") Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        try {
            // Consider implications: what happens to games in this category?
            // Service layer might throw an exception if category is in use.
            categoryService.deleteCategory(id);
            redirectAttributes.addFlashAttribute("successMessage", "分类删除成功！");
        } catch (RuntimeException e) {
            // e.g., DataIntegrityViolationException if games are associated and no cascade delete
            redirectAttributes.addFlashAttribute("errorMessage", "删除失败: " + e.getMessage());
        }
        return "redirect:/admin/categories";
    }
}
