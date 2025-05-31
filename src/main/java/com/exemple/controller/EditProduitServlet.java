package com.exemple.controller;

import com.exemple.dao.ProduitDaoImpl;
import com.exemple.model.Produit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/editProduit")
public class EditProduitServlet extends HttpServlet {

    private ProduitDaoImpl produitDao;

    @Override
    public void init() throws ServletException {
        produitDao = new ProduitDaoImpl();
    }

    // GET: show form with product info
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            long id = Long.parseLong(request.getParameter("id"));
            Produit produit = produitDao.getProduit(id);

            if (produit != null) {
                request.setAttribute("produit", produit);
                request.getRequestDispatcher("editProduit.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/produits");
        }
    }

    // POST: update product
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long id = Long.parseLong(request.getParameter("id"));
            String nomProduit = request.getParameter("nomProduit");
            double prix = Double.parseDouble(request.getParameter("prix"));

            Produit produit = new Produit();
            produit.setId(id);
            produit.setNomProduit(nomProduit);
            produit.setPrix(prix);

            produitDao.updateProduit(produit);

            response.sendRedirect(request.getContextPath() + "/produits");

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Données invalides");
            request.getRequestDispatcher("editProduit.jsp").forward(request, response);
        }
    }
}
