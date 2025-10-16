<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AISTHÉA - Trang chính</title>
    <style>
        body {
            font-family: "Segoe UI", sans-serif;
            background: #fafafa;
            color: #333;
            text-align: center;
            padding: 50px;
        }
        a {
            text-decoration: none;
            color: #333;
            background: #f0c040;
            padding: 10px 20px;
            border-radius: 6px;
        }
        a:hover {
            background: #d4a10f;
        }
    </style>
</head>
<body>
    <h1>✨ Welcome to AISTHÉA ✨</h1>

    <%
        Object userObj = session.getAttribute("user");
        if (userObj != null) {
            com.aisthea.fashion.model.User user = (com.aisthea.fashion.model.User) userObj;
    %>
        <p>Hello, <strong><%= user.getFullname() %></strong>!</p>
        <p>Bạn đã đăng nhập thành công 🎉</p>
        <a href="logout">Đăng xuất</a>
    <%
        } else {
    %>
        <p>Bạn chưa đăng nhập!</p>
        <a href="login">Đăng nhập</a> |
        <a href="register">Đăng ký</a>
    <%
        }
    %>
</body>
</html>
