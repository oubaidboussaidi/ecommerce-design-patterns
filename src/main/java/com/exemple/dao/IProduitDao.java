package com.exemple.dao;

import java.util.List;
import com.exemple.model.*;
public interface IProduitDao {
public Produit save(Produit p);
public Produit getProduit(Long id);
public Produit updateProduit(Produit p);
public void deleteProduit(Long id);
}