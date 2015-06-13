<%@page import="DataTransferObject.Plane"%>
<%@page import="DataAccessLayer.PlaneHandler"%>
<%@page import="DataTransferObject.Cabin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataAccessLayer.CabinHandler"%>
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
        <link rel="stylesheet" href="/public/css/employee-cabin.css"/>
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
                <%
                    String planeID = request.getParameter("plane");
                    
                    int currentPage = 1;
                    int index = 1;
                    CabinHandler cabinHandler = new CabinHandler();
                    PlaneHandler planeHandler = new PlaneHandler();
                    Plane plane = planeHandler.one(planeID);
                %>
                <div class="group-title">
                    <div class="btn-navigate">
                        <button class="btn btn-warning" id="btn-back">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                        </button>
                    </div>
                    <h2 class="title">
                        <span id="plane-id"><%= plane.getID() %></span> - <%= plane.getManufacturer() %> <%= plane.getName() %>
                    </h2>
                    <div class="btn-navigate btn-insert">
                        <button class="btn btn-success" id="btn-insert">Thêm khoang</button>
                    </div>
                </div>
                <table class="table table-striped table-hover table-responsive">
                    <thead>
                        <th class="center">STT</th>
                        <th>Mã khoang</th>
                        <th>Tên khoang</th>
                        <th></th>
                        <th></th>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Cabin> cabins = cabinHandler.getByPlane(planeID.replace("'", "''"));
                            for (Cabin cabin : cabins) {
                        %>
                            <tr>
                                <td class="center"><%= index++ %></td>
                                <td class="cabin-id"><%= cabin.getID() %></td>
                                <td><%= cabin.getName() %></td>
                                <td>
                                    <button class="btn btn-default btn-details">
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
        <script src="/public/js/employee-cabin.js"></script>
    </body>
</html>