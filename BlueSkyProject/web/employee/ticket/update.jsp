<%@page import="DataTransferObject.Cabin"%>
<%@page import="DataAccessLayer.CabinHandler"%>
<%@page import="DataTransferObject.Trip"%>
<%@page import="DataTransferObject.Plane"%>
<%@page import="java.text.DateFormat"%>
<%@page import="DataTransferObject.TicketPrice"%>
<%@page import="DataAccessLayer.TicketPriceHandler"%>
<%@page import="DataAccessLayer.TripHandler"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DataTransferObject.Flight"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataAccessLayer.FlightHandler"%>
<%@page import="Core.Auth"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html ng-app="update-ticket-price">
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
        <link rel="stylesheet" href="/public/css/employee-update-ticketprice.css"/>
    </head>
    <body>

        <%
            if (Auth.authorizeManager(session) == false) {
                response.sendRedirect("/login.jsp");
                return;
            }
            
            if (request.getParameter("ticketprice") == null) {
                response.sendRedirect("/employee/ticket.jsp");
                return;
            }
            
            String ticketPriceID = request.getParameter("ticketprice");
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
            <article class="col-md-9" ng-controller="UpdateCtrl">
                <%
                    FlightHandler flightHandler = new FlightHandler();
                    TripHandler tripHandler = new TripHandler();
                    TicketPriceHandler ticketPriceHandler = new TicketPriceHandler();
                    CabinHandler cabinHandler = new CabinHandler();
                    
                    TicketPrice ticketPrice = ticketPriceHandler.one(ticketPriceID);
                    
                    if (ticketPrice == null) {
                        response.sendRedirect("/employee/ticket.jsp");
                        return;
                    }

                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    ArrayList<Flight> flights = flightHandler.getAll();
                %>
                <div class="group-navigator">
                    <h2 class="title">Cập nhật giá vé</h2>
                </div>
                <div class="row">
                    <div class="col-md-6 ticket-price-info">
                        <h2 class="sub-title">Thông tin giá vé</h2>
                        <div>
                            <h4>Mã giá vé: <span><%= ticketPriceID %></span></h4>
                        </div>
                        <div>
                            <h4>Thời gian bắt đầu: <span><%= formatter.format(ticketPrice.getStartTime()) %></span></h4>
                        </div>
                        <div>
                            <h4>Thời gian kết thúc: <span><%= formatter.format(ticketPrice.getEndTime()) %></span></h4>
                        </div>
                        <div>
                            <h4>Giá: <span><%= ticketPrice.getPrice() %></span></h4>
                        </div>
                        <div>
                            <%
                                Flight flight = flightHandler.one(ticketPrice.getFlight());
                                Trip trip = tripHandler.one(flight.getTrip());
                            %>
                            <h4>Chuyến bay: <span><%= trip.getName() %></span></h4>
                        </div>
                        <div>
                            <%
                                Cabin cabin = cabinHandler.one(ticketPrice.getCabin());
                            %>
                            <h4>Khoang: <span><%= cabin.getName() %></span></h4>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <form action="/employee/ticket/price/updatehandler" method="post">
                            <input type="hidden" name="txtID" value="<%= ticketPriceID %>" />
                            <div class="ticket-input">
                                <h4>Ngày bắt đầu</h4>
                                <div class="input-group date">
                                    <input type="text" class="form-control" name="txtStartTime" value="<%= formatter.format(ticketPrice.getStartTime()) %>">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-th"></i>
                                    </span>
                                </div>
                            </div>
                                <div class="ticket-input">
                                <h4>Ngày kết thúc</h4>
                                <div class="input-group date">
                                    <input type="text" class="form-control" name="txtEndTime" value="<%= formatter.format(ticketPrice.getEndTime()) %>">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-th"></i>
                                    </span>
                                </div>
                            </div>
                            <div class="ticket-input">
                                <h4>Giá vé</h4>
                                <input class="form-control" name="txtPrice" autocomplete="off" spellcheck="false" placeholder="Giá vé" value="<%= ticketPrice.getPrice() %>"/>
                            </div>
                            <div class="ticket-input">
                                <h4>Chuyến bay</h4>
                                <select class="combobox" name="cbxFlight" id="cbx-flight">
                                    <% for (Flight _flight : flights) { %>
                                    <option value="<%= _flight.getID() %>"><%= tripHandler.getName(_flight.getTrip())%></option>
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
                                <button type="submit" class="btn btn-primary">Cập nhật giá vé</button>
                                <button type="button" class="btn btn-default" id="btn-cancel">Hủy</button>
                            </div>
                        </form>
                    </div>
                </div>
            </article>
        </section>
        
        <script src="/public/js/angular.js"></script>
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/bootstrap-combobox.js"></script>
        <script src="/public/js/bootstrap-datepicker.js"></script>
        <script src="/public/js/employee-update-ticketprice.js"></script>
    </body>
</html>