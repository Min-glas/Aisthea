package com.aisthea.fashion.controller;

import com.aisthea.fashion.dao.ProductDAO;
import com.aisthea.fashion.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Product p = productDAO.getProductById(id);
                req.setAttribute("product", p);
                req.getRequestDispatcher("product.jsp").forward(req, resp);
                return;
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
