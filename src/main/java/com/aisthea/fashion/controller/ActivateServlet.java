package com.aisthea.fashion.controller;

import com.aisthea.fashion.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/activate")
public class ActivateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        if (email != null && !email.isEmpty()) {
            boolean activated = UserDAO.activateUser(email);
            if (activated) {
                request.setAttribute("message", "Tài khoản đã được kích hoạt! Bạn có thể đăng nhập.");
            } else {
                request.setAttribute("error", "Kích hoạt thất bại. Email không hợp lệ hoặc đã kích hoạt.");
            }
        } else {
            request.setAttribute("error", "Email không hợp lệ!");
        }
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }
}