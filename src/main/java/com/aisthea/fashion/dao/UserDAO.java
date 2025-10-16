package com.aisthea.fashion.dao;

import com.aisthea.fashion.model.User;
import com.aisthea.fashion.utils.BCryptUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    // Insert user (password đã được hash trước khi gọi hàm này)
    public static boolean insertUser(User user) {
        String sql = "INSERT INTO users (username, password, fullname, email, gender, phone, address, role, active, createdat, updatedat) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0, GETDATE(), GETDATE())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getEmail()); // username = email
            ps.setString(2, user.getPassword()); // mật khẩu đã hash
            ps.setString(3, user.getFullname());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getGender());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getAddress());
            ps.setString(8, user.getRole());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kiểm tra email đã tồn tại
    public static boolean isEmailExist(String email) {
        String sql = "SELECT 1 FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Đăng nhập bằng email + password (plaintext) -> trả về User nếu OK
    public static User login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashed = rs.getString("password");
                    // check password
                    if (BCryptUtil.checkPassword(password, hashed)) {
                        User u = new User();
                        u.setUserId(rs.getInt("userid"));
                        u.setUsername(rs.getString("username"));
                        u.setFullname(rs.getString("fullname"));
                        u.setEmail(rs.getString("email"));
                        u.setGender(rs.getString("gender"));
                        u.setPhone(rs.getString("phone"));
                        u.setAddress(rs.getString("address"));
                        u.setRole(rs.getString("role"));
                        u.setActive(rs.getBoolean("active"));
                        return u;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy user theo email
    public static User getUserByEmail(String email) {
        User user = null;
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setUserId(rs.getInt("userid"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFullname(rs.getString("fullname"));
                    user.setEmail(rs.getString("email"));
                    user.setGender(rs.getString("gender"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setRole(rs.getString("role"));
                    user.setActive(rs.getBoolean("active"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static boolean activateUser(String email) {
        boolean success = false;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "UPDATE users SET active = 1 WHERE email = ? AND active = 0")) {
            stmt.setString(1, email);
            success = stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
