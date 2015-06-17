<%@page import="DataTransferObject.Trip"%>
<%@page import="DataAccessLayer.TripHandler"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataAccessLayer.AirportHandler"%>
<%@page import="DataTransferObject.Airport"%>
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
        <link rel="stylesheet" href="/public/css/admin-app.css"/>
        <link rel="stylesheet" href="/public/css/employee-update-trip.css"/>
    </head>
    <body>
        
        <%
            if (Auth.authorizeEmployee(session) == false) {
                response.sendRedirect("/login.jsp");
                return;
            }
            
            if (request.getParameter("trip") == null) {
                response.sendRedirect("/employee/trip.jsp");
                return;
            }
            
            String tripID = request.getParameter("trip");
            
            AirportHandler airportHandler = new AirportHandler();
            TripHandler tripHandler = new TripHandler();
            
            Trip trip = tripHandler.one(tripID);
            ArrayList<Airport> airports = airportHandler.getAll();
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
        
        <section class="container">
            <aside class="col-md-3 nav-menu">
                <h3 class="nav-header">Menu</h3>
                <ul class="nav nav-pills nav-stacked">
                    <li role="presentation"><a href="/employee/info.jsp">Cập nhật thông tin</a></li>
                    <li role="presentation"><a href="/employee/trip.jsp">Quản lý tuyến bay</a></li>
                    <li role="presentation"><a href="/employee/plane.jsp">Quản lý danh mục máy bay</a></li>
                    <li role="presentation"><a href="/employee/flight.jsp">Quản lý chuyến bay</a></li>
                </ul>
            </aside>
            <article class="col-md-9">
                <div class="content">
                    <div class="group-navigator">
                        <h2 class="title">
                            Cập nhật tuyến bay
                        </h2>
                    </div>
                    <form action="/employee/trip/updatehandler" method="post">
                        <div class="trip-input">
                            <h4>Mã tuyến bay</h4>
                            <h5 class="trip-id"><%= tripID %></h5>
                            <input type="hidden" name="txtID" value="<%= tripID %>"/>
                        </div>
                        <div class="trip-input">
                            <h4>Tên tuyến bay</h4>
                            <input class="form-control" type="text" name="txtName" placeholder="Tên tuyến bay" autocomplete="off" spellcheck="false" value="<%= trip.getName() %>"/>
                        </div>
                        <div class="trip-input">
                            <h4>Sân bay đi</h4>
                            <select class="combobox" name="cbxFrom" default="<%= trip.getFrom() %>">
                                <% for (Airport airport : airports) { %>
                                    <option value="<%= airport.getID() %>"><%= airport.getName() %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="trip-input">
                            <h4>Sân bay đến</h4>
                            <select class="combobox" name="cbxTo" default="<%= trip.getTo() %>">
                                <% for (Airport airport : airports) { %>
                                    <option value="<%= airport.getID() %>"><%= airport.getName() %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="trip-input">
                            <button type="submit" class="btn btn-primary">Cập nhật tuyến bay</button>
                            <button type="button" class="btn btn-default" id="btn-cancel">Hủy</button>
                        </div>
                    </form>
                </div>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/bootstrap-combobox.js"></script>
        <script src="/public/js/employee-update-trip.js"></script>
    </body>
