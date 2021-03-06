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
        <link rel="stylesheet" href="/public/css/employee-app.css"/>
        <link rel="stylesheet" href="/public/css/employee-index.css"/>
    </head>
    <body>

        <%
            if (Auth.authorizeEmployee(session) == false) {
                response.sendRedirect("/login.jsp");
                return;
            }
        %>
        
        <header>
            <h1 class="navbar-brand">
                <a href="/employee/index.jsp">BlueSky</a>
            </h1>
            <ul class="nav-user">
                <li><%= session.getAttribute("userName") %></li>
                <li>
                    <a href="/admin/logouthandler">Đăng xuất</a>
                </li>
            </ul>
        </header>
        
        <section class="container row">
            <aside class="col-md-3 nav-menu">
                <h3 class="nav-header">
                    <%= session.getAttribute("userPermission") %>
                </h3>
                <ul class="nav nav-pills nav-stacked">
                    <li role="presentation"><a href="/employee/info.jsp">Cập nhật thông tin</a></li>
                    <% if (session.getAttribute("userPermission").equals("MANAGER")) { %>
                        <!-- for Manager -->
                        <li role="presentation"><a href="/employee/trip.jsp">Quản lý tuyến bay</a></li>
                        <li role="presentation"><a href="/employee/plane.jsp">Quản lý danh mục máy bay</a></li>
                        <li role="presentation"><a href="/employee/flight.jsp">Quản lý chuyến bay</a></li>
                        <li role="presentation"><a href="/employee/ticket.jsp">Quản lý bảng giá vé</a></li>
                    <% } else if (session.getAttribute("userPermission").equals("CONDUCTOR")) { %>
                        <!-- for Conductor -->
                        <li role="presentation"><a href="/employee/search.jsp">Tra cứu</a></li>
                    <% } else if (session.getAttribute("userPermission").equals("PILOT") || session.getAttribute("userPermission").equals("STEWARDESS")) { %>
                        <!-- for Pilot and Stewardess -->
                        <li role="presentation"><a href="/employee/task.jsp">Phân công công việc</a></li>
                    <% } %>
                </ul>
            </aside>
            <article class="col-md-9">
                <div>
                    <img class="aero-plane" src="/public/img/aero-plane.png" alt="Aero Plane"/>
                </div>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
    </body>
</html>