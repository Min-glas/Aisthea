<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - AISTHEA</title>
    <style>
        body {
            margin: 0; padding: 0;
            font-family: 'Poppins', sans-serif;
            background: url('${pageContext.request.contextPath}/images/bg-watercolor.png') no-repeat center center fixed;
            background-size: cover;
            height: 100vh;
            display: flex; justify-content: center; align-items: center;
        }

        /* --- logo góc trái --- */
        .logo {
            position: absolute;
            top: 20px; left: 30px;
            display: flex; align-items: center; gap: 10px;
        }

        .logo img {
            height: 60px; /* giữ đồng bộ với register */
            width: auto;
            cursor: pointer;
            transition: transform 0.3s ease;
        }

        .logo img:hover { transform: scale(1.05); }

        .logo span {
            font-weight: 600;
            color: #003366;
            font-size: 20px;
            letter-spacing: 1px;
        }

        /* --- form container --- */
        .container {
            background: rgba(255,255,255,0.45);
            backdrop-filter: blur(14px);
            border-radius: 20px;
            box-shadow: 0 8px 25px rgba(0,0,0,.2);
            width: 420px; /* tăng rộng để đồng bộ register */
            padding: 40px;
            color: #003366;
            text-align: left;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #003366;
        }

        label {
            display: block;
            margin: 8px 0 4px;
            font-weight: 500;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 14px;
            border: none;
            border-radius: 8px;
            background: rgba(255,255,255,0.85);
            outline: none;
            font-size: 14px;
        }

        button {
            width: 100%;
            background: #004c99;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-weight: 600;
            transition: background 0.3s;
        }

        button:hover { background: #3366cc; }

        a {
            color: #004c99;
            text-decoration: none;
            font-weight: 500;
        }

        a:hover { text-decoration: underline; }

        .error {
            color: #cc0000;
            font-weight: 500;
            text-align: center;
            margin-top: 10px;
        }

        p.small {
            text-align: center;
            margin-top: 14px;
        }
    </style>
</head>
<body>
    <!-- logo + quay về home -->
    <div class="logo">
        <a href="${pageContext.request.contextPath}/views/index.jsp">
            <img src="${pageContext.request.contextPath}/images/ata-logo.png" alt="AISTHEA Logo">
        </a>
        <span>AISTHEA</span>
    </div>

    <div class="container">
        <h2>Login to your account</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <label>Email</label>
            <input type="email" name="email" required placeholder="Enter your email" />

            <label>Password</label>
            <input type="password" name="password" required placeholder="Enter your password" />

            <button type="submit">Login</button>
        </form>

        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

        <p class="small">Don't have an account? 
            <a href="${pageContext.request.contextPath}/register">Register</a>
        </p>
    </div>
</body>
</html>
