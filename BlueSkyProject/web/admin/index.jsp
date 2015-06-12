<%@page import="Core.Auth"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>BlueSky</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="/public/img/favicon.png" rel="shortcut icon">
        <link rel="stylesheet" href="/public/css/bootstrap.css"/>
        <link rel="stylesheet" href="/public/css/bootstrap-theme.css"/>
        <link rel="stylesheet" href="/public/css/admin-app.css"/>
        <link rel="stylesheet" href="/public/css/admin-index.css"/>
    </head>
    <body>

        <%
            if (Auth.authorizeAdmin(session) == false) {
                response.sendRedirect("/login.jsp");
                return;
            }
        %>
        
        <header>
            <h1 class="navbar-brand">
                <a href="/admin/index.jsp">BlueSky</a>
            </h1>
            <ul class="nav-user">
                <li><%= session.getAttribute("userName") %></li>
                <li>
                    <a href="/admin/logouthandler">Đăng xuất</a>
                </li>
            </ul>
        </header>
        
        <section class="container row">
            <%
                if (session.getAttribute("userPermission").equals("ADMIN")) {
            %>
            <aside class="col-md-3 nav-menu">
                <h3 class="nav-header">Menu</h3>
                <ul class="nav nav-pills nav-stacked">
                    <li role="presentation"><a href="/admin/info.jsp">Thay đổi mật khẩu</a></li>
                    <li role="presentation"><a href="/admin/create.jsp">Tạo tài khoản nhân viên</a></li>
                    <li role="presentation"><a href="/admin/customer.jsp">Quản lý tài khoản khách hàng</a></li>
                    <li role="presentation"><a href="/admin/employee.jsp">Quản lý tài khoản nhân viên</a></li>
                    <li role="presentation"><a href="#">Quản lý cấu hình Website</a></li>
                </ul>
            </aside>
            <% } %>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
    </body>
</html>