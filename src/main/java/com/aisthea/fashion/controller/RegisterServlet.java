package com.aisthea.fashion.controller;

import com.aisthea.fashion.dao.UserDAO;
import com.aisthea.fashion.model.User;
import com.aisthea.fashion.utils.BCryptUtil;
import com.aisthea.fashion.utils.MailUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirmPassword");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // Kiểm tra mật khẩu xác nhận
        if (!password.equals(confirm)) {
            request.setAttribute("error", "Mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
            return;
        }

        // Hash mật khẩu 1 lần
        String hashedPassword = BCryptUtil.hashPassword(password);

        User newUser = new User();
        newUser.setUsername(email);
        newUser.setFullname(fullname);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword); // Đã hash
        newUser.setGender(gender);
        newUser.setPhone(phone);
        newUser.setAddress(address);
        newUser.setRole("USER");

        boolean success = UserDAO.insertUser(newUser);

        if (success) {
            // Gửi mail kích hoạt
            String subject = "Kích hoạt tài khoản AISTHEA";
            String activateLink = "http://localhost:8080/FashionProject/activate?email=" + email;
            String html = "<html><body>" +
                    "<h2>Chào " + fullname + "!</h2>" +
                    "<p>Bạn đã đăng ký tài khoản tại <b>AISTHEA Fashion</b>.</p>" +
                    "<p>Vui lòng nhấn vào nút bên dưới để kích hoạt tài khoản:</p>" +
                    "<a href='" + activateLink + "' style='background:#4CAF50;color:white;padding:10px 15px;text-decoration:none;border-radius:5px;'>Kích hoạt ngay</a>" +
                    "<p>Nếu bạn không đăng ký, vui lòng bỏ qua email này.</p>" +
                    "</body></html>";

            MailUtil.sendMail(email, subject, html);
            request.setAttribute("message", "Đăng ký thành công! Vui lòng kiểm tra email để kích hoạt tài khoản.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Đăng ký thất bại! Vui lòng thử lại.");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        }
    }
}