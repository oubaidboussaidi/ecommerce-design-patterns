package com.exemple.dao;

import com.exemple.model.Produit;
import com.exemple.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProduitDaoImpl implements IProduitDao {

    @Override
    public Produit save(Produit p) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
            System.out.println("Produit enregistré !");
        }
        return p;
    }

    @Override
    public Produit getProduit(Long id) {
        Produit produit = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            produit = session.get(Produit.class, id);
            session.getTransaction().commit();
        }
        return produit;
    }

    @Override
    public Produit updateProduit(Produit p) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.update(p);
            session.getTransaction().commit();
            System.out.println("Produit mis à jour !");
        }
        return p;
    }

    @Override
    public void deleteProduit(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Produit produit = session.get(Produit.class, id);
            if (produit != null) {
                session.delete(produit);
                System.out.println("Produit supprimé !");
            } else {
                System.out.println("Produit introuvable pour suppression.");
            }
            session.getTransaction().commit();
        }
    }

    public List<Produit> getAllProduits() {
        List<Produit> produits;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            produits = session.createQuery("from Produit", Produit.class).getResultList();
            session.getTransaction().commit();
        }
        return produits;
    }
}
