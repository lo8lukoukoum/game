package com.example.gameshare.controller;

import com.example.gameshare.model.Rating;
import com.example.gameshare.model.User;
import com.example.gameshare.service.GameService;
import com.example.gameshare.service.RatingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class RatingController {

    private final RatingService ratingService;
    private final GameService gameService;

    private static final String USER_SESSION_KEY = "loggedInUser";

    @Autowired
    public RatingController(RatingService ratingService, GameService gameService) {
        this.ratingService = ratingService;
        this.gameService = gameService;
    }

    private User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute(USER_SESSION_KEY);
    }

    @PostMapping("/games/{gameId}/ratings")
    public String addOrUpdateRating(@PathVariable("gameId") Integer gameId,
                                    @ModelAttribute Rating rating, // Expect "分数" field from form
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
        User loggedInUser = getLoggedInUser(session);
        if (loggedInUser == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "请先登录再评分。");
            return "redirect:/login";
        }

        if (gameService.findGameById(gameId) == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "游戏未找到。");
            return "redirect:/games";
        }

        if (rating.getScore() == null || rating.getScore() < 1 || rating.getScore() > 5) {
            redirectAttributes.addFlashAttribute("errorMessage", "评分必须在1到5之间。");
            return "redirect:/games/" + gameId;
        }

        rating.setGameId(gameId);
        rating.setUserId(loggedInUser.getId());
        // Creation and update times are set in the service (createOrUpdateRating)

        ratingService.createOrUpdateRating(rating);
        redirectAttributes.addFlashAttribute("successMessage", "评分成功！");
        return "redirect:/games/" + gameId;
    }
}
