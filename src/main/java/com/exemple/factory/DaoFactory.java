package com.exemple.factory;

import com.exemple.dao.IProduitDao;
import com.exemple.dao.IUserDao;
import com.exemple.dao.ProduitDaoImpl;
import com.exemple.dao.UserDao;

public class DaoFactory {

    private static final IUserDao userDao = new UserDao();
    private static final IProduitDao produitDao = new ProduitDaoImpl();

    public static IUserDao getUserDao() {
        return userDao;
    }

    public static IProduitDao getProduitDao() {
        return produitDao;
    }
}
