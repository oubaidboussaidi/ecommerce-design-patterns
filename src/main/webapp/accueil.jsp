<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.exemple.model.User" %>
<%@ page import="com.exemple.model.Produit" %>
<%@ page import="com.exemple.dao.ProduitDaoImpl" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Produit> produits = (List<Produit>) request.getAttribute("produits");
    if (produits == null || produits.isEmpty()) {
        ProduitDaoImpl produitDao = new ProduitDaoImpl();
        produits = produitDao.getAllProduits();
        request.setAttribute("produits", produits);
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <title>Accueil</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(135deg, #e0f7fa, #e1f5fe);
            display: flex;
            justify-content: center;
            align-items: start;
            min-height: 100vh;
            padding: 40px 20px;
        }

        .container {
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
            max-width: 900px;
            width: 100%;
        }

        h1 {
            font-size: 28px;
            margin-bottom: 10px;
            color: #0077b6;
        }

        h2 {
            font-size: 22px;
            color: #023e8a;
            margin-top: 30px;
            margin-bottom: 20px;
        }

        .logout-btn {
            background-color: #f44336;
            color: white;
            border: none;
            padding: 10px 16px;
            border-radius: 8px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
            float: right;
        }

        .logout-btn:hover {
            background-color: #d32f2f;
        }

        .products-list {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
            gap: 20px;
        }

        .product {
            background: #f9f9f9;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 3px 8px rgba(0, 0, 0, 0.07);
            transition: transform 0.2s ease;
        }

        .product:hover {
            transform: translateY(-4px);
        }

        .product h3 {
            margin: 0 0 10px;
            color: #0077b6;
        }

        .product p {
            margin: 0;
            color: #444;
            font-weight: bold;
        }

        .no-products {
            color: #ff5722;
            font-size: 18px;
            font-weight: bold;
            text-align: center;
            margin-top: 20px;
        }

        .btn {
            margin-top: 30px;
            display: block;
            width: 100%;
            padding: 14px;
            background-color: #0077b6;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: bold;
            text-transform: uppercase;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color: #023e8a;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        @media (max-width: 600px) {
            .products-list {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Bienvenue, <%= user.getLogin() %></h1>
        <form action="logout" method="post">
            <button type="submit" class="logout-btn">Se déconnecter</button>
        </form>
    </div>

    <h2>Nos produits</h2>

    <% if (produits != null && !produits.isEmpty()) { %>
    <div class="products-list">
        <% for (Produit produit : produits) { %>
        <div class="product">
            <h3><%= produit.getNomProduit() %></h3>
            <p><%= produit.getPrix() %> €</p>
        </div>
        <% } %>
    </div>
    <% } else { %>
    <p class="no-products">Aucun produit disponible pour le moment.</p>
    <% } %>

    <button class="btn">Explorer plus de produits</button>
</div>
</body>
</html>
