package com.example.gameshare.controller;

import com.example.gameshare.model.Collection;
import com.example.gameshare.model.User;
import com.example.gameshare.service.CollectionService;
import com.example.gameshare.service.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class CollectionController {

    private final CollectionService collectionService;
    private final GameService gameService;

    private static final String USER_SESSION_KEY = "loggedInUser";

    @Autowired
    public CollectionController(CollectionService collectionService, GameService gameService) {
        this.collectionService = collectionService;
        this.gameService = gameService;
    }

    private User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute(USER_SESSION_KEY);
    }

    @GetMapping("/my-collections")
    public String showMyCollections(Model model, HttpSession session) {
        User loggedInUser = getLoggedInUser(session);
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("collections", collectionService.getUserCollection(loggedInUser.getId()));
        return "my-collections"; // View: my-collections.html
    }

    @PostMapping("/collections/add/{gameId}")
    public String addToCollection(@PathVariable("gameId") Integer gameId,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {
        User loggedInUser = getLoggedInUser(session);
        if (loggedInUser == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "请先登录。");
            return "redirect:/login";
        }

        if (gameService.findGameById(gameId) == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "游戏未找到。");
            return "redirect:/games"; // Or back to where the user was
        }

        if (collectionService.isGameInUserCollection(loggedInUser.getId(), gameId)) {
            redirectAttributes.addFlashAttribute("infoMessage", "该游戏已在您的收藏中。");
            return "redirect:/games/" + gameId;
        }

        Collection collection = new Collection();
        collection.set用户id(loggedInUser.getId());
        collection.set游戏id(gameId);
        // Collection time is set in service or by default in DB

        collectionService.addToCollection(collection);
        redirectAttributes.addFlashAttribute("successMessage", "成功添加到收藏！");
        return "redirect:/games/" + gameId; // Or redirect to /my-collections
    }

    @PostMapping("/collections/remove/{gameId}")
    public String removeFromCollection(@PathVariable("gameId") Integer gameId,
                                       HttpSession session,
                                       RedirectAttributes redirectAttributes) {
        User loggedInUser = getLoggedInUser(session);
        if (loggedInUser == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "请先登录。");
            return "redirect:/login";
        }

        if (!collectionService.isGameInUserCollection(loggedInUser.getId(), gameId)) {
            redirectAttributes.addFlashAttribute("errorMessage", "该游戏不在您的收藏中。");
            return "redirect:/games/" + gameId; // Or /my-collections
        }

        collectionService.removeFromCollection(loggedInUser.getId(), gameId);
        redirectAttributes.addFlashAttribute("successMessage", "成功从收藏中移除。");

        // Determine the best page to redirect to
        String referer = session.getServletContext().getContextPath() + "/my-collections"; // A bit of a guess for referer
        // String requestURI = (String) session.getAttribute("javax.servlet.forward.request_uri"); // this is null
        // if (requestURI != null && requestURI.contains("/my-collections")) {
        // return "redirect:/my-collections";
        // }
        return "redirect:/games/" + gameId; // Default redirect to game page
    }
}
