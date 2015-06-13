<%@page import="DataAccessLayer.PlaneHandler"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataTransferObject.Plane"%>
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
        <link rel="stylesheet" href="/public/css/employee-plane.css"/>
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
                    <%
                        if (session.getAttribute("userPermission").equals("ADMIN")) {
                    %>
                        <!-- for Administrator -->
                    <% } else if (session.getAttribute("userPermission").equals("MANAGER")) { %>
                        <!-- for Manager -->
                        <li><a href="/employee/plane.jsp">Quản lý danh mục máy bay</a></li>
                    <% } else if (session.getAttribute("userPermission").equals("CONDUCTOR")) { %>
                        <!-- for Conductor -->
                    <% } else if (session.getAttribute("userPermission").equals("PILOT")) { %>
                        <!-- for Pilot -->
                    <% } else if (session.getAttribute("userPermission").equals("STEWARDESS")) { %>
                        <!-- for Stewardess -->
                    <% } %>
                </ul>
            </aside>
            <article class="col-md-9">
                <h2 class="title">Quản lý danh mục máy bay</h2>
                <div class="input-group group-search">
                    <input class="form-control" placeholder="Từ khóa">
                    <span class="input-group-btn">
                        <button class="btn btn-primary">
                            <span class="glyphicon glyphicon-search"></span>
                            Tìm kiếm
                        </button>
                        <button class="btn btn-default" id="btn-clear">
                            <span class="glyphicon glyphicon-repeat"></span>
                        </button>
                    </span>
                </div>
                <table class="table table-striped table-hover table-responsive">
                    <thead>
                        <th class="center">STT</th>
                        <th>Tên</th>
                        <th>Hãng SX</th>
                        <th>Ngày vận hành</th>
                        <th></th>
                        <th></th>
                    </thead>
                    <tbody>
                        <%
                            PlaneHandler handler = new PlaneHandler();
                            
                            int currentPage = 1;
                            int index = 1;
                            
                            if (request.getParameter("page") != null) {
                                currentPage = Integer.parseInt(request.getParameter("page"));
                            }
                            
                            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            ArrayList<Plane> planes = handler.limit(currentPage);
                            for (Plane plane : planes) {
                        %>
                            <tr>
                                <td class="center"><%= index++ %></td>
                                <td class="hidden"><%= plane.getID() %></td>
                                <td><%= plane.getName() %></td>
                                <td><%= plane.getManufacturer()%></td>
                                <td><%= formatter.format(plane.getStart()) %></td>
                                <td>
                                    <button class="btn btn-default btn-edit">
                                        <span class="glyphicon glyphicon-list-alt"></span>
                                    </button>
                                </td>
                                <td>
                                    <button class="btn btn-danger btn-remove">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </button>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
    </body>
</html>