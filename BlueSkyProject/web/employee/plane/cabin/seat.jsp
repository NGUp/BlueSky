<%@page import="DataTransferObject.Cabin"%>
<%@page import="DataTransferObject.Plane"%>
<%@page import="DataAccessLayer.PlaneHandler"%>
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
        <link rel="stylesheet" href="/public/css/employee-seat.css"/>
    </head>
    <body>

        <%
            if (Auth.authorizeManager(session) == false) {
                response.sendRedirect("/login.jsp");
                return;
            }
            
            String planeID = request.getParameter("plane");
            String cabinID = request.getParameter("cabin");
            
            CabinHandler cabinHandler = new CabinHandler();
            PlaneHandler planeHandler = new PlaneHandler();
            
            Plane plane = planeHandler.one(planeID);
            Cabin cabin = cabinHandler.getDetails(planeID, cabinID);
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
                    <li role="presentation"><a href="/employee/trip.jsp">Quản lý tuyến bay</a></li>
                    <li role="presentation"><a href="/employee/plane.jsp">Quản lý danh mục máy bay</a></li>
                    <li role="presentation"><a href="/employee/flight.jsp">Quản lý chuyến bay</a></li>
                    <li role="presentation"><a href="/employee/ticket.jsp">Quản lý bảng giá vé</a></li>
                </ul>
            </aside>
            <article class="col-md-9">
                <div class="group-navigator">
                    <div class="title">
                        <h3 class="plane">
                            <span id="plane-id"><%= plane.getID() %></span> - <%= plane.getManufacturer() %> <%= plane.getName() %>
                        </h3>
                        <h3>
                            Loại: <%= cabin.getName() %>
                        </h3>
                    </div>
                </div>
                <div class="content">
                    <form action="/employee/plane/cabin/updatehandler" method="post">
                        <div class="seat-input">
                            <h4>Số dãy</h4>
                            <input class="form-control" type="text" name="txtColumns" placeholder="Số dãy" autocomplete="off" spellcheck="false" value="<%= cabin.getColumns() %>"/>
                        </div>
                        <div class="seat-input">
                            <h4>Số hàng</h4>
                            <input class="form-control" type="text" name="txtRows" placeholder="Số hàng" autocomplete="off" spellcheck="false" value="<%= cabin.getRows() %>"/>
                        </div>
                        <div class="seat-input">
                            <input type="hidden" name="planeID" value="<%= planeID %>"/>
                            <input type="hidden" name="cabinID" value="<%= cabinID %>"/>
                            <button type="submit" class="btn btn-primary">Cập nhật</button>
                            <button type="button" class="btn btn-default" id="btn-cancel">Hủy</button>
                        </div>
                    </form>
                </div>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/employee-seat.js"></script>
    </body>
</html>