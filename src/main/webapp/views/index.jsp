<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>AISTHÉA - Home</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; }
        header { display:flex; align-items:center; justify-content:space-between; padding:16px 40px; background:#fff; box-shadow: 0 2px 6px rgba(0,0,0,.05); }
        .logo { font-weight:700; font-size:22px; color:#d32; }
        nav a { margin:0 12px; color:#333; text-decoration:none; }
        .grid { display:flex; flex-wrap:wrap; gap:20px; padding:24px; justify-content:center; }
        .card { width:220px; border:1px solid #eee; border-radius:8px; overflow:hidden; text-align:center; background:#fff; box-shadow:0 6px 18px rgba(0,0,0,.03); }
        .card img { width:100%; height:220px; object-fit:cover; }
        .card .title { padding:12px; font-weight:600; }
        .card .price { padding-bottom:12px; color:#1a8f5b; font-weight:700; }
    </style>
</head>
<body>
<header>
    <div class="logo">AISTHÉA</div>
    <nav>
        <a href="${pageContext.request.contextPath}/home">Home</a>
        <a href="${pageContext.request.contextPath}/login">Login</a>
        <a href="${pageContext.request.contextPath}/register">Register</a>
    </nav>
</header>

<main>
    <section class="grid">
        <c:forEach var="p" items="${products}">
            <div class="card">
                <a href="${pageContext.request.contextPath}/product?id=${p.productId}">
                    <img src="${p.imageUrl != null ? p.imageUrl : 'images/placeholder.png'}" alt="${p.name}" />
                </a>
                <div class="title">${p.name}</div>
                <div class="price">$<fmt:formatNumber value="${p.price}" type="number" minFractionDigits="2"/></div>
            </div>
        </c:forEach>
        <c:if test="${empty products}">
            <p>No products found.</p>
        </c:if>
    </section>
</main>

</body>
</html>
