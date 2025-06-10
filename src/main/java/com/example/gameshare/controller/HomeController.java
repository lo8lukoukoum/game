package com.example.gameshare.controller;

import com.example.gameshare.model.Game;
import com.example.gameshare.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final GameService gameService;

    @Autowired
    public HomeController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Display a few games on the homepage, e.g., recently added or popular
        // For now, let's just get all games and limit in view or here if pagination was implemented
        List<Game> games = gameService.findAllGames();
        // Example: Get top 5 games or a subset
        // List<Game> featuredGames = games.size() > 5 ? games.subList(0, 5) : games;
        model.addAttribute("games", games);
        return "index"; // Thymeleaf view name: index.html
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied"; // Thymeleaf view name: access-denied.html
    }
}
