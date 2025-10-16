package com.aisthea.fashion.dao;

import com.aisthea.fashion.model.Product;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

public class ProductDAO {
    private Connection conn;

    public ProductDAO() {
        conn = DBConnection.getConnection();
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                    rs.getInt("productid"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getString("imageurl")
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // add inside ProductDAO class
    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE productid = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product p = new Product(
                    rs.getInt("productid"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getString("imageurl")
                );
                p.setDescription(rs.getString("description"));
                p.setStock(rs.getInt("stock"));
                p.setBrand(rs.getString("brand"));
                p.setSize(rs.getString("size"));
                p.setColor(rs.getString("color"));
                p.setDiscount(rs.getDouble("discount"));
                p.setCategoryId(rs.getInt("categoryid"));
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
