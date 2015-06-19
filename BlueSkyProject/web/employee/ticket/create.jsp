<%@page import="DataAccessLayer.TripHandler"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DataTransferObject.Flight"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataAccessLayer.FlightHandler"%>
<%@page import="Core.Auth"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html ng-app="create-ticket-price">
    <head>
        <meta charset="UTF-8"/>
        <title>BlueSky</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="/public/img/favicon.png" rel="shortcut icon">
        <link rel="stylesheet" href="/public/css/bootstrap.css"/>
        <link rel="stylesheet" href="/public/css/bootstrap-theme.css"/>
        <link rel="stylesheet" href="/public/css/bootstrap-combobox.css"/>
        <link rel="stylesheet" href="/public/css/bootstrap-datepicker.css"/>
        <link rel="stylesheet" href="/public/css/employee-app.css"/>
        <link rel="stylesheet" href="/public/css/employee-create-ticketprice.css"/>
    </head>
    <body>

        <%
            if (Auth.authorizeManager(session) == false) {
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
                    <li role="presentation"><a href="/employee/trip.jsp">Quản lý tuyến bay</a></li>
                    <li role="presentation"><a href="/employee/plane.jsp">Quản lý danh mục máy bay</a></li>
                    <li role="presentation"><a href="/employee/flight.jsp">Quản lý chuyến bay</a></li>
                    <li role="presentation"><a href="/employee/ticket.jsp">Quản lý bảng giá vé</a></li>
                </ul>
            </aside>
            <article class="col-md-9" ng-controller="CreateCtrl">
                <%
                    FlightHandler flightHandler = new FlightHandler();
                    TripHandler tripHandler = new TripHandler();

                    ArrayList<Flight> flights = flightHandler.getAll();
                %>
                <div class="content">
                    <div class="group-navigator">
                        <h2 class="title">Thêm giá vé</h2>
                    </div>
                    <form action="/employee/ticket/price/createhandler" method="post">
                        <div class="ticket-input">
                            <h4>Ngày bắt đầu</h4>
                            <div class="input-group date">
                                <input type="text" class="form-control" name="txtStartTime">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-th"></i>
                                </span>
                            </div>
                        </div>
                            <div class="ticket-input">
                            <h4>Ngày kết thúc</h4>
                            <div class="input-group date">
                                <input type="text" class="form-control" name="txtEndTime">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-th"></i>
                                </span>
                            </div>
                        </div>
                        <div class="ticket-input">
                            <h4>Giá vé</h4>
                            <input class="form-control" name="txtPrice" autocomplete="off" spellcheck="false" placeholder="Giá vé"/>
                        </div>
                        <div class="ticket-input">
                            <h4>Chuyến bay</h4>
                            <select class="combobox" name="cbxFlight" id="cbx-flight">
                                <% for (Flight flight : flights) { %>
                                <option value="<%= flight.getID() %>"><%= tripHandler.getName(flight.getTrip())%></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="ticket-input">
                            <h4>Khoang</h4>
                            <select class="form-control" name="cbxCabin">
                                <option ng-repeat="cabin in cabins" value="{{cabin.ID}}">{{cabin.Name}}</option>
                            </select>
                        </div>
                        <div class="ticket-input">
                            <button type="submit" class="btn btn-primary">Thêm giá vé</button>
                            <button type="button" class="btn btn-default" id="btn-cancel">Hủy</button>
                        </div>
                    </form>
                </div>
            </article>
        </section>
        
        <script src="/public/js/angular.js"></script>
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/bootstrap-combobox.js"></script>
        <script src="/public/js/bootstrap-datepicker.js"></script>
        <script src="/public/js/employee-create-ticketprice.js"></script>
    </body>
</html>