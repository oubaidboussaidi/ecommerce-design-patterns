<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.exemple.model.User" %>

<%
    User currentUser = (User) session.getAttribute("user");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    if (!"admin".equalsIgnoreCase(currentUser.getRole())) {
        response.sendRedirect("accueil.jsp");
        return;
    }

    List<User> users = (List<User>) request.getAttribute("users");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Utilisateurs</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(to right, #f0f8ff, #ffffff);
            margin: 0;
            padding: 40px 20px;
        }
        .container {
            max-width: 900px;
            margin: auto;
            background: #fff;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #0077b6;
            font-size: 28px;
        }
        table {
            width: 100%;
            margin-top: 40px;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 14px;
            text-align: center;
        }
        th {
            background-color: #0077b6;
            color: white;
            text-transform: uppercase;
        }
        .btn-small {
            padding: 8px 12px;
            font-size: 14px;
            margin: 0 5px;
            border-radius: 6px;
            text-decoration: none;
            color: #0077b6;
            border: 1.5px solid #0077b6;
            background-color: #caf0f8;
            cursor: pointer;
            transition: all 0.3s;
            display: inline-block;
        }
        .btn-small:hover {
            background-color: #0077b6;
            color: white;
        }
        .actions {
            display: flex;
            justify-content: center;
            gap: 10px;
        }
        select {
            padding: 6px 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="container">
    <h1>Gestion des Utilisateurs</h1>

    <table>
        <tr>
            <th>Email</th>
            <th>Rôle</th>
            <th>Actions</th>
        </tr>
        <%
            if (users != null && !users.isEmpty()) {
                for (User u : users) {
                    if (u.getLogin().equals(currentUser.getLogin())) continue; // don't show self
        %>
        <tr>
            <td><%= u.getLogin() %></td>
            <td><%= u.getRole() %></td>
            <td class="actions">
                <!-- Change Role Form -->
                <form action="users" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="changeRole">
                    <input type="hidden" name="email" value="<%= u.getLogin() %>">
                    <select name="newRole">
                        <option value="admin" <%= "admin".equalsIgnoreCase(u.getRole()) ? "selected" : "" %>>Admin</option>
                        <option value="user" <%= "user".equalsIgnoreCase(u.getRole()) ? "selected" : "" %>>User</option>
                    </select>
                    <button type="submit" class="btn-small">🔄 Modifier</button>
                </form>

                <!-- Delete Form -->
                <form action="users" method="post" style="display:inline;" onsubmit="return confirm('Supprimer cet utilisateur ?');">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="email" value="<%= u.getLogin() %>">
                    <button class="btn-small" type="submit">🗑️ Supprimer</button>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="3">Aucun utilisateur trouvé.</td>
        </tr>
        <% } %>
    </table>
</div>

</body>
</html>
