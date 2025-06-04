<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription</title>
    <style>
        body {
            background: linear-gradient(135deg, #e0f7fa, #e1f5fe);
            font-family: 'Segoe UI', sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .login-container {
            background: #fff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        .login-container h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #0077b6;
            font-size: 28px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: 600;
            color: #333;
        }

        input[type="text"],
        input[type="password"],
        select {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 16px;
            background-color: #f9f9f9;
        }

        input:focus, select:focus {
            border-color: #0077b6;
            outline: none;
            background-color: #fff;
        }

        button {
            width: 100%;
            padding: 14px;
            background-color: #0077b6;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-weight: bold;
            text-transform: uppercase;
        }

        button:hover {
            background-color: #023e8a;
        }

        .message {
            margin-top: 15px;
            text-align: center;
            font-size: 14px;
        }

        .error-message {
            color: #e53935;
        }

        .success-message {
            color: #43a047;
        }

        .register-link {
            margin-top: 20px;
            text-align: center;
        }

        .register-link a {
            color: #0077b6;
            text-decoration: none;
            font-weight: bold;
        }

        .register-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Inscription</h2>
    <form action="auth" method="post">
        <input type="hidden" name="action" value="register">

        <div class="form-group">
            <label for="username">Email</label>
            <input type="text" id="username" name="username" placeholder="Entrez votre email" required>
        </div>

        <div class="form-group">
            <label for="password">Mot de passe</label>
            <input type="password" id="password" name="password" placeholder="Entrez votre mot de passe" required>
        </div>

        <div class="form-group">
            <label for="role">Rôle</label>
            <select id="role" name="role" required>
                <option value="">Sélectionnez un rôle</option>
                <option value="user">Utilisateur</option>
                <option value="admin">Administrateur</option>
            </select>
        </div>

        <button type="submit">S'inscrire</button>

        <div class="message">
            <% if (request.getAttribute("error") != null) { %>
            <div class="error-message"><%= request.getAttribute("error") %></div>
            <% } %>
            <% if (request.getAttribute("message") != null) { %>
            <div class="success-message"><%= request.getAttribute("message") %></div>
            <% } %>
        </div>
    </form>

    <div class="register-link">
        Vous avez déjà un compte ? <a href="login.jsp">Connectez-vous ici</a>
    </div>
</div>
</body>
</html>
