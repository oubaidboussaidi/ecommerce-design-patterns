<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .navbar {
        background-color: #0077b6;
        color: white;
        padding: 16px 32px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-family: 'Segoe UI', sans-serif;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    }

    .navbar .logo {
        font-size: 20px;
        font-weight: bold;
        letter-spacing: 1px;
    }

    .navbar .nav-links a {
        color: white;
        text-decoration: none;
        margin-left: 24px;
        font-weight: 500;
        transition: opacity 0.2s ease;
    }

    .navbar .nav-links a:hover {
        opacity: 0.85;
    }

    .navbar form {
        display: inline;
        margin-left: 24px;
    }

    .navbar form button {
        background: none;
        border: none;
        color: white;
        font-weight: bold;
        cursor: pointer;
        font-family: inherit;
        font-size: 1rem;
        padding: 0;
    }

    .navbar form button:hover {
        opacity: 0.85;
    }
</style>

<div class="navbar">
    <div class="logo">🛍️ MyShop</div>
    <div class="nav-links">
        <a href="accueil.jsp">Boutique</a>
        <%
            com.exemple.model.User user = (com.exemple.model.User) session.getAttribute("user");
            if (user != null && "admin".equalsIgnoreCase(user.getRole())) {
        %>
        <a href="view.jsp">Gestion des produits</a>
        <a href="users.jsp">Gestion des utilisateurs</a>
        <% } %>
        <form action="logout" method="post">
            <button type="submit">Se déconnecter</button>
        </form>
    </div>
</div>
