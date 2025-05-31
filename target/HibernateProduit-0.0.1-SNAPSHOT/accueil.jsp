<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.exemple.model.User" %>
<%@ page import="com.exemple.model.Produit" %>
<%@ page import="com.exemple.dao.ProduitDaoImpl" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        // Redirect to login if user not authenticated
        response.sendRedirect("login.jsp");
        return;
    }

    // Try to get product list from request attribute
    List<Produit> produits = (List<Produit>) request.getAttribute("produits");

    // If product list is not set or empty, fetch from DAO
    if (produits == null || produits.isEmpty()) {
        ProduitDaoImpl produitDao = new ProduitDaoImpl();
        produits = produitDao.getAllProduits();  // Correct method name
        request.setAttribute("produits", produits);
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Accueil</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0; padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            background: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            width: 400px;
            text-align: center;
        }
        h1, h2 {
            color: #333;
        }
        .product {
            background: #fff;
            border: 1px solid #e0e0e0;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .product h3 {
            font-size: 20px;
            margin-bottom: 10px;
        }
        .product p {
            font-size: 16px;
            color: #666;
        }
        .btn, .logout-btn {
            width: 100%;
            padding: 12px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            border: none;
            margin-top: 20px;
        }
        .btn {
            background-color: #4CAF50;
            color: white;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .logout-btn {
            background-color: #f44336;
            margin-bottom: 30px;
            color: white;
            transition: background-color 0.3s ease;
        }
        .logout-btn:hover {
            background-color: #e53935;
        }
        .no-products {
            color: #ff5722;
            font-size: 18px;
            font-weight: bold;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome <%= user.getLogin() %></h1>

        <!-- Logout form -->
        <form action="logout" method="post">
            <button type="submit" class="logout-btn">Logout</button>
        </form>

        <h2>Product List</h2>

        <% if (produits != null && !produits.isEmpty()) { %>
            <div class="products-list">
                <% for (Produit produit : produits) { %>
                    <div class="product">
                        <h3><%= produit.getNomProduit() %></h3>
                        <p>Price: <%= produit.getPrix() %> €</p>
                    </div>
                <% } %>
            </div>
        <% } else { %>
            <p class="no-products">No products available at the moment.</p>
        <% } %>

        <button class="btn">Explore More Products</button>
    </div>
</body>
</html>
