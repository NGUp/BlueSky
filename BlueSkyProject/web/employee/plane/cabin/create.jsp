<%@page import="DataTransferObject.Cabin"%>
<%@page import="DataAccessLayer.CabinHandler"%>
<%@page import="DataAccessLayer.PlaneHandler"%>
<%@page import="java.text.DateFormat"%>
<%@page import="DataTransferObject.Plane"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
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
        <link rel="stylesheet" href="/public/css/bootstrap-combobox.css"/>
        <link rel="stylesheet" href="/public/css/employee-app.css"/>
        <link rel="stylesheet" href="/public/css/employee-create-cabin.css"/>
    </head>
    <body>

        <%
            if (Auth.authorizeManager(session) == false) {
                response.sendRedirect("/login.jsp");
                return;
            }
            
            String planeID = request.getParameter("plane");
            
            CabinHandler cabinHandler = new CabinHandler();
            ArrayList<Cabin> cabins = cabinHandler.getAvailableCabins(planeID);
            
            if (cabins.size() < 1) {
                response.sendRedirect("/employee/plane/cabin.jsp?plane=" + planeID);
                return;
            }
            
            
            PlaneHandler planeHandler = new PlaneHandler();
            Plane plane = planeHandler.one(planeID);
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
                <div class="content">
                    <div class="group-navigator">
                        <div class="btn-navigate">
                            <button class="btn btn-warning" id="btn-back">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </button>
                        </div>
                        <h2 class="title">
                            <span id="plane-id"><%= plane.getID() %></span> - <%= plane.getManufacturer() %> <%= plane.getName() %>
                        </h2>
                    </div>
                    <form action="/employee/plane/cabin/createhandler" method="post">
                        <div class="cabin-input">
                            <h4>Loại khoang máy bay</h4>
                            <select class="combobox" name="cbxCabin">
                                <% for (Cabin cabin : cabins) { %>
                                    <option value="<%= cabin.getID() %>"><%= cabin.getName() %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="cabin-input">
                            <input type="hidden" name="planeID" value="<%= plane.getID() %>" />
                            <button type="submit" class="btn btn-primary">Thêm khoang</button>
                            <button type="button" class="btn btn-default" id="btn-cancel">Hủy</button>
                        </div>
                    </form>
                </div>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/bootstrap-combobox.js"></script>
        <script src="/public/js/employee-create-cabin.js"></script>
    </body>
</html>