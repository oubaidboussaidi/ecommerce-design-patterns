<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.exemple.model.Produit" %>
<%
    Produit produit = (Produit) request.getAttribute("produit");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Modifier Produit</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: 'Segoe UI', sans-serif;
      background: linear-gradient(to right, #e0f2f1, #b2dfdb);
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .card {
      background: white;
      padding: 40px 30px;
      border-radius: 16px;
      box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 420px;
      animation: fadeIn 0.4s ease;
    }

    @keyframes fadeIn {
      from { opacity: 0; transform: translateY(20px); }
      to { opacity: 1; transform: translateY(0); }
    }

    h2 {
      text-align: center;
      color: #00796b;
      margin-bottom: 30px;
      font-size: 26px;
    }

    label {
      display: block;
      font-weight: 600;
      margin-bottom: 8px;
      color: #333;
    }

    input[type="text"],
    input[type="number"] {
      width: 100%;
      padding: 12px;
      margin-bottom: 20px;
      border-radius: 8px;
      border: 1px solid #ccc;
      font-size: 16px;
      transition: border-color 0.2s ease;
      background-color: #f9f9f9;
    }

    input:focus {
      border-color: #00796b;
      outline: none;
      background-color: #fff;
    }

    button {
      width: 100%;
      padding: 14px;
      border: none;
      background-color: #00796b;
      color: white;
      font-size: 16px;
      font-weight: bold;
      border-radius: 8px;
      cursor: pointer;
      text-transform: uppercase;
      letter-spacing: 1px;
      transition: background-color 0.3s ease;
    }

    button:hover {
      background-color: #004d40;
    }
  </style>
</head>
<body>
  <div class="card">
    <h2>Modifier Produit</h2>
    <form action="editProduit" method="post">
      <input type="hidden" name="id" value="<%= produit.getId() %>">

      <label for="nomProduit">Nom du produit</label>
      <input type="text" id="nomProduit" name="nomProduit" value="<%= produit.getNomProduit() %>" required>

      <label for="prix">Prix (€)</label>
      <input type="number" id="prix" name="prix" step="0.01" value="<%= produit.getPrix() %>" required>

      <button type="submit">Enregistrer</button>
    </form>
  </div>
</body>
</html>
