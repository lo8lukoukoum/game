package com.example.gameshare.interceptor;

import com.example.gameshare.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component // So it can be @Autowired in WebConfig
public class AuthInterceptor implements HandlerInterceptor {

    private static final String USER_SESSION_KEY = "loggedInUser";
    private static final String ADMIN_ROLE_NAME = "管理员";

    // Define paths that require login but not necessarily admin rights
    // Using startsWith for simplicity here. Regex could be more precise.
    private static final List<String> USER_AUTH_REQUIRED_PATHS = Arrays.asList(
            "/my-collections",
            "/profile",
            "/collections/add", // Covers /collections/add/*
            "/collections/remove" // Covers /collections/remove/*
            // Specific game actions like commenting/rating are handled by path.matches below
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession(false); // false = don't create if it doesn't exist
        User currentUser = (session != null) ? (User) session.getAttribute(USER_SESSION_KEY) : null;
        String path = request.getRequestURI();

        // Admin Path Protection
        if (path.startsWith("/admin/")) {
            if (currentUser == null) {
                response.sendRedirect(request.getContextPath() + "/login?source=" + path); // Add source for potential redirect back
                return false;
            }
            boolean isAdmin = false;
            if (currentUser.getRoles() != null) {
                isAdmin = currentUser.getRoles().stream()
                                     .anyMatch(role -> ADMIN_ROLE_NAME.equals(role.getName()));
            }
            if (!isAdmin) {
                response.sendRedirect(request.getContextPath() + "/access-denied");
                return false;
            }
            return true; // Admin access granted
        }

        // User-Specific Path Protection
        // More specific checks for actions like commenting or rating on a game
        if (path.matches("/games/\\d+/comments") || path.matches("/games/\\d+/ratings")) {
             if (request.getMethod().equalsIgnoreCase("POST")) { // Typically POST requests for these actions
                if (currentUser == null) {
                    response.sendRedirect(request.getContextPath() + "/login?source=" + path);
                    return false;
                }
            }
            return true; // Allow GET for viewing comments/ratings even if not logged in. POST is protected.
        }

        // General user-auth paths
        for (String authPath : USER_AUTH_REQUIRED_PATHS) {
            if (path.startsWith(authPath)) {
                if (currentUser == null) {
                    response.sendRedirect(request.getContextPath() + "/login?source=" + path);
                    return false;
                }
                break; // Found a match, user is logged in, proceed
            }
        }

        return true; // Path is public or user is authorized
    }
}
