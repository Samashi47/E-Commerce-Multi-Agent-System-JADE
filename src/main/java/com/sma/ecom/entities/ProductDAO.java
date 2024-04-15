package com.sma.ecom.entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private SessionFactory factory;

    public ProductDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public Product GetProductByID(int id) {
        Product product = null;
        try {
            Session session = this.factory.openSession();
            product = session.get(Product.class, id);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public boolean saveProduct(Product product) {
        boolean f = false;

        try {

            Session session = this.factory.openSession();
            Transaction tx = session.beginTransaction();

            session.save(product);
            f = true;


            tx.commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            f = false;
        }
        return f;
    }
    //get all products
    public ArrayList<Product> FindProductByName(String name) {
        ArrayList<Product> liste = new ArrayList<>();
        try {
            Session session = this.factory.openSession();
            Query query = session.createQuery("FROM Product WHERE name LIKE :name");
            query.setParameter("name", "%" + name + "%");
            List<Product> produits = query.list();
            for (Product product : produits) {
                System.out.println(product.getName());
                liste.add(product);
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
    public List<Product> getAllProducts() {
        Session s = this.factory.openSession();
        Query query = s.createQuery("FROM Product");
        List<Product> list = query.list();
        return list;

    }
}
