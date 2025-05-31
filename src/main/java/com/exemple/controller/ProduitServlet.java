package com.exemple.controller;

import com.exemple.dao.ProduitDaoImpl;
import com.exemple.model.Produit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {

    private ProduitDaoImpl produitDao;

    @Override
    public void init() throws ServletException {
        super.init();
        produitDao = new ProduitDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Produit> produits = produitDao.getAllProduits();
        request.setAttribute("produits", produits);
        request.getRequestDispatcher("view.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equalsIgnoreCase(action)) {
            String nomProduit = request.getParameter("nomProduit");
            String prixStr = request.getParameter("prix");
            double prix = 0.0;
            try {
                prix = Double.parseDouble(prixStr);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Prix invalide");
                doGet(request, response);
                return;
            }

            Produit produit = new Produit();
            produit.setNomProduit(nomProduit);
            produit.setPrix(prix);

            produitDao.save(produit);
            response.sendRedirect(request.getContextPath() + "/produits");

        } else if ("edit".equalsIgnoreCase(action)) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                String nomProduit = request.getParameter("nomProduit");
                double prix = Double.parseDouble(request.getParameter("prix"));

                Produit produit = new Produit();
                produit.setId(id);
                produit.setNomProduit(nomProduit);
                produit.setPrix(prix);

                produitDao.updateProduit(produit);
                response.sendRedirect("editProduit.jsp");

            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Données invalides pour la modification");
                doGet(request, response);
            }

        } else if ("delete".equalsIgnoreCase(action)) {
            String idStr = request.getParameter("id");
            try {
                long id = Long.parseLong(idStr);
                produitDao.deleteProduit(id);
                response.sendRedirect(request.getContextPath() + "/produits");
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "ID invalide pour suppression");
                doGet(request, response);
            }

        } else {
            doGet(request, response);
        }
    }
}
