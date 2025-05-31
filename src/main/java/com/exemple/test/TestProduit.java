package com.exemple.test;

import com.exemple.dao.ProduitDaoImpl;
import com.exemple.model.Produit;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestProduit {

    public static void main(String[] args) {

    	Scanner scanner = new Scanner(System.in);
        ProduitDaoImpl pdao = new ProduitDaoImpl();

        Produit prod = pdao.save(new Produit("iphone 8 plus", 2800));
        System.out.println(prod);

    }
}
