package com.exemple.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.exemple.factory.DaoFactory;
import com.exemple.dao.IUserDao;
import com.exemple.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserController extends HttpServlet {

    private IUserDao userDao;

    @Override
    public void init() throws ServletException {
        try {
            userDao = DaoFactory.getUserDao();
            System.out.println("[INIT] UserController initialized. DAO injected successfully.");
        } catch (Exception e) {
            System.err.println("[INIT ERROR] Failed to initialize DAO: " + e.getMessage());
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println("[GET] /users called. Action: " + action);

        try {
            if ("logout".equalsIgnoreCase(action)) {
                handleLogout(request, response);
            } else if ("search".equalsIgnoreCase(action)) {
                handleSearch(request, response);
            } else {
                listUsers(request, response);
            }
        } catch (Exception e) {
            System.err.println("[GET ERROR] Error handling GET request: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne serveur");
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[LIST USERS] Fetching all users...");
        List<User> users = null;

        try {
            users = userDao.getAllUsers();

            if (users == null) {
                System.err.println("[LIST USERS] ERROR: getAllUsers() returned NULL");
                users = java.util.Collections.emptyList(); // Safe fallback
            } else {
                System.out.println("[LIST USERS] Found " + users.size() + " users:");
                for (User u : users) {
                    System.out.println("    - " + u.getLogin() + " | Role: " + u.getRole());
                }
            }

            request.setAttribute("users", users);
            request.getRequestDispatcher("users.jsp").forward(request, response);

        } catch (Exception e) {
            System.err.println("[LIST USERS ERROR] Exception occurred: " + e.getMessage());
            e.printStackTrace();
            throw new ServletException("Failed to list users", e);
        }
    }

    private void handleSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String emailFilter = request.getParameter("emailFilter");
        System.out.println("[SEARCH USERS] Searching by email filter: " + emailFilter);

        try {
            List<User> users = userDao.searchUsersByEmail(emailFilter != null ? emailFilter : "");

            if (users == null) {
                System.err.println("[SEARCH USERS] WARNING: searchUsersByEmail returned NULL");
                users = java.util.Collections.emptyList();
            } else {
                System.out.println("[SEARCH USERS] Found " + users.size() + " users.");
            }

            request.setAttribute("users", users);
            request.getRequestDispatcher("users.jsp").forward(request, response);

        } catch (Exception e) {
            System.err.println("[SEARCH USERS ERROR] Failed search: " + e.getMessage());
            e.printStackTrace();
            throw new ServletException("Erreur lors de la recherche", e);
        }
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("[LOGOUT] Invalidating session and redirecting...");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println("[POST] /users called. Action: " + action);

        try {
            if ("delete".equalsIgnoreCase(action)) {
                String email = request.getParameter("email");
                System.out.println("[DELETE USER] Email: " + email);
                userDao.deleteByEmail(email);
                response.sendRedirect("users");

            } else if ("changeRole".equalsIgnoreCase(action)) {
                String email = request.getParameter("email");
                String newRole = request.getParameter("newRole");
                System.out.println("[UPDATE ROLE] Email: " + email + ", New Role: " + newRole);
                userDao.updateUserRole(email, newRole);
                response.sendRedirect("users");

            } else {
                System.err.println("[POST ERROR] Unknown action: " + action);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action inconnue");
            }

        } catch (Exception e) {
            System.err.println("[POST ERROR] Exception: " + e.getMessage());
            e.printStackTrace();
            throw new ServletException("Erreur lors de la mise à jour", e);
        }
    }
}
