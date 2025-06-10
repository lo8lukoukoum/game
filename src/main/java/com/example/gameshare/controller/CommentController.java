package com.example.gameshare.controller;

import com.example.gameshare.model.Comment;
import com.example.gameshare.model.User;
import com.example.gameshare.service.CommentService;
import com.example.gameshare.service.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping; // Added for admin delete
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final GameService gameService; // To check if game exists

    private static final String ADMIN_ROLE_NAME = "管理员";
    private static final String USER_SESSION_KEY = "loggedInUser";

    @Autowired
    public CommentController(CommentService commentService, GameService gameService) {
        this.commentService = commentService;
        this.gameService = gameService;
    }

    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute(USER_SESSION_KEY);
        if (user == null || user.get角色列表() == null) return false;
        return user.get角色列表().stream().anyMatch(role -> ADMIN_ROLE_NAME.equals(role.get角色名称()));
    }

    private User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute(USER_SESSION_KEY);
    }

    @PostMapping("/games/{gameId}/comments")
    public String addComment(@PathVariable("gameId") Integer gameId,
                             @ModelAttribute Comment comment,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        User loggedInUser = getLoggedInUser(session);
        if (loggedInUser == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "请先登录再评论。");
            return "redirect:/login";
        }

        if (gameService.findGameById(gameId) == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "游戏未找到。");
            return "redirect:/games";
        }

        if (comment.get评论内容() == null || comment.get评论内容().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "评论内容不能为空。");
            return "redirect:/games/" + gameId;
        }

        comment.set游戏id(gameId);
        comment.set用户id(loggedInUser.getId());
        // Creation and update times are set in service

        commentService.createComment(comment);
        redirectAttributes.addFlashAttribute("successMessage", "评论成功！");
        return "redirect:/games/" + gameId;
    }

    @GetMapping("/admin/comments/delete/{id}")
    public String deleteComment(@PathVariable("id") Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "无权操作。");
            return "redirect:/";
        }

        Comment comment = commentService.findCommentById(id);
        if (comment == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "评论未找到。");
            return "redirect:/admin/games"; // Or some other relevant admin page
        }

        Integer gameId = comment.get游戏id(); // For redirecting back to the game page or admin game list

        commentService.deleteComment(id);
        redirectAttributes.addFlashAttribute("successMessage", "评论删除成功。");

        // Redirect to the game's detail page, or an admin page if more appropriate
        if (gameId != null) {
            return "redirect:/games/" + gameId;
        }
        return "redirect:/admin/games"; // Fallback redirect
    }
}
