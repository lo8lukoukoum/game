package com.example.gameshare.controller;

import com.example.gameshare.model.Category;
import com.example.gameshare.model.Game;
import com.example.gameshare.model.User;
import com.example.gameshare.model.Comment; // Added
import com.example.gameshare.model.Rating; // Added
import com.example.gameshare.service.*; // Added
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors; // Added

@Controller
public class GameController {

    private final GameService gameService;
    private final CategoryService categoryService;
    private final CommentService commentService;
    private final RatingService ratingService;
    private final CollectionService collectionService; // Added CollectionService

    private static final String ADMIN_ROLE_NAME = "管理员";
    private static final String USER_SESSION_KEY = "loggedInUser";


    @Autowired
    public GameController(GameService gameService, CategoryService categoryService, CommentService commentService, RatingService ratingService, CollectionService collectionService) { // Added CollectionService
        this.gameService = gameService;
        this.categoryService = categoryService;
        this.commentService = commentService;
        this.ratingService = ratingService;
        this.collectionService = collectionService; // Added CollectionService
    }

    // --- Helper methods for security ---
    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute(USER_SESSION_KEY);
        if (user == null || user.getRoles() == null) return false;
        return user.getRoles().stream().anyMatch(role -> ADMIN_ROLE_NAME.equals(role.getName()));
    }

    private User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute(USER_SESSION_KEY);
    }


    // --- Public Game Views ---

    @GetMapping("/games")
    public String listAllGames(Model model) {
        model.addAttribute("games", gameService.findAllGames());
        return "games-list"; // View: games-list.html
    }

    @GetMapping("/games/{id}")
    public String gameDetails(@PathVariable("id") Integer id, Model model, HttpSession session) {
        Game game = gameService.findGameById(id);
        if (game == null) {
            return "redirect:/games"; // Or a 404 page
        }
        model.addAttribute("game", game);
        model.addAttribute("comments", commentService.findCommentsByGameId(id));

        List<Rating> ratings = ratingService.getRatingByGameId(id);
        double averageRating = ratings.stream().mapToInt(Rating::getScore).average().orElse(0.0);
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("ratingsCount", ratings.size());

        if (getLoggedInUser(session) != null) {
            Rating userRating = ratingService.getUserRatingForGame(getLoggedInUser(session).getId(), id);
            model.addAttribute("userRating", userRating != null ? userRating.getScore() : 0);
            boolean isCollected = collectionService.isGameInUserCollection(getLoggedInUser(session).getId(), id); // collectionService was missing from constructor
            model.addAttribute("isCollected", isCollected);
        } else {
            model.addAttribute("userRating", 0);
            model.addAttribute("isCollected", false);
        }
        model.addAttribute("newComment", new Comment()); // For submitting new comment
        model.addAttribute("newRating", new Rating());   // For submitting new rating

        return "game-details"; // View: game-details.html
    }

    @GetMapping("/games/category/{categoryId}")
    public String listGamesByCategory(@PathVariable("categoryId") Integer categoryId, Model model) {
        Category category = categoryService.findCategoryById(categoryId);
        if (category == null) {
            return "redirect:/games"; // Or a 404 page for category not found
        }
        model.addAttribute("category", category);
        model.addAttribute("games", gameService.findGamesByCategoryId(categoryId));
        return "games-by-category"; // View: games-by-category.html
    }

    @GetMapping("/games/search")
    public String searchGames(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("games", gameService.searchGames(keyword));
        model.addAttribute("keyword", keyword);
        return "games-search-results"; // View: games-search-results.html
    }

    // --- Admin: Game Management ---

    @GetMapping("/admin/games")
    public String adminListGames(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        model.addAttribute("games", gameService.findAllGames());
        return "admin/games-list"; // View: admin/games-list.html (can be different from public)
    }


    @GetMapping("/admin/games/new")
    public String showNewGameForm(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("game", new Game());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "admin/game-edit"; // View: admin/game-edit.html (can serve for new and edit)
    }

    @PostMapping("/admin/games/new")
    public String createNewGame(@ModelAttribute Game game, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        gameService.createGame(game);
        redirectAttributes.addFlashAttribute("successMessage", "游戏创建成功！");
        return "redirect:/admin/games";
    }

    @GetMapping("/admin/games/edit/{id}")
    public String showEditGameForm(@PathVariable("id") Integer id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        Game game = gameService.findGameById(id);
        if (game == null) {
            return "redirect:/admin/games";
        }
        model.addAttribute("game", game);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "admin/game-edit"; // View: admin/game-edit.html
    }

    @PostMapping("/admin/games/edit")
    public String updateGame(@ModelAttribute Game game, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        gameService.updateGame(game);
        redirectAttributes.addFlashAttribute("successMessage", "游戏更新成功！");
        return "redirect:/admin/games";
    }

    @GetMapping("/admin/games/delete/{id}")
    public String deleteGame(@PathVariable("id") Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        // Consider related entities: comments, ratings, collections.
        // Service layer should ideally handle cascading or checks.
        gameService.deleteGame(id);
        redirectAttributes.addFlashAttribute("successMessage", "游戏删除成功！");
        return "redirect:/admin/games";
    }
}
