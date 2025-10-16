package com.aisthea.fashion.controller;

import com.aisthea.fashion.dao.UserDAO;
import com.aisthea.fashion.model.User;
import com.aisthea.fashion.utils.BCryptUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = UserDAO.getUserByEmail(email);

        if (user != null) {
            if (!user.isActive()) {
                request.setAttribute("error", "Tài khoản chưa được kích hoạt! Vui lòng xác nhận email.");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
                return;
            }

            if (BCryptUtil.checkPassword(password, user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/views/home.jsp");
                return;
            }
        }

        request.setAttribute("error", "Sai email hoặc mật khẩu!");
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }
}