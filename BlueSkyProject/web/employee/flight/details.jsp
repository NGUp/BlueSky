<%@page import="DataAccessLayer.TicketPriceHandler"%>
<%@page import="DataTransferObject.Cabin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataAccessLayer.CabinHandler"%>
<%@page import="DataAccessLayer.AirportHandler"%>
<%@page import="DataTransferObject.Trip"%>
<%@page import="DataAccessLayer.PlaneHandler"%>
<%@page import="DataTransferObject.Flight"%>
<%@page import="DataAccessLayer.FlightHandler"%>
<%@page import="DataAccessLayer.TripHandler"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
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
        <link rel="stylesheet" href="/public/css/employee-flight-details.css"/>
    </head>
    <body>

        <%
            if (Auth.authorizeConductor(session) == false) {
                response.sendRedirect("/login.jsp");
                return;
            }
            
            if (request.getParameter("flight") == null) {
                response.sendRedirect("/employee/search.jsp");
                return;
            }
            
            String flightID = request.getParameter("flight");
            FlightHandler flightHandler = new FlightHandler();
            
            Flight flight = flightHandler.one(flightID);
            
            if (flight == null) {
                response.sendRedirect("/employee/search.jsp");
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
                    <li role="presentation"><a href="/employee/search.jsp">Tra cứu</a></li>
                </ul>
            </aside>
            <article class="col-md-9">
                <%
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    TripHandler tripHandler = new TripHandler();
                    PlaneHandler planeHandler = new PlaneHandler();
                    AirportHandler airpottHandler = new AirportHandler();
                    CabinHandler cabinHandler = new CabinHandler();
                    TicketPriceHandler ticketPriceHandler = new TicketPriceHandler();
                    
                    int index = 1;
                    
                    Plane plane = planeHandler.one(flight.getPlane());
                    Trip trip = tripHandler.one(flight.getTrip());
                    ArrayList<Cabin> cabins = cabinHandler.getByPlane(plane.getID());
                %>
                <div class="row">
                    <div class="col-md-6 flight-info">
                        <h2 class="title">Thông tin chuyến bay</h2>
                        <div>
                            <h4>Mã chuyến: <span><%= flight.getID() %></span></h4>
                        </div>
                        <div>
                            <h4>Thời gian khởi hành: <span><%= formatter.format(flight.getDeparture()) %></span></h4>
                        </div>
                        <div>
                            <h4>Thời gian hạ cánh: <span><%= formatter.format(flight.getArrival()) %></span></h4>
                        </div>
                        <div>
                            <h4>Tuyến: <span><%= tripHandler.getName(flight.getTrip()) %></span></h4>
                        </div>
                        <div>
                            <h4>Máy bay: <span><%= plane.getManufacturer() %> <%= plane.getName() %></span></h4>
                        </div>
                        <div>
                            <h4>Sân bay đi: <span><%= airpottHandler.getName(trip.getFrom()) %></span></h4>
                        </div>
                        <div>
                            <h4>Sân bay đến: <span><%= airpottHandler.getName(trip.getTo()) %></span></h4>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <form method="post" action="/employee/ticket/seat.jsp">
                            <input type="hidden" name="txtFlightID" value="<%= flight.getID() %>"/>
                            <input type="hidden" name="txtPlaneID" value="<%= plane.getID() %>"/>
                            <h4 class="title-cabin">Danh sách khoang</h4>
                            <table class="table table-striped table-hover table-responsive">
                                <thead>
                                    <tr>
                                        <th class="index">STT</th>
                                        <th>Tên khoang</th>
                                        <th>Giá vé</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (Cabin cabin : cabins) { %>
                                    <tr>
                                        <td class="index"><%= index++ %></td>
                                        <td><%= cabin.getName() %></td>
                                        <td><%= ticketPriceHandler.getPriceByFlightCabin(flightID, cabin.getID()) %></td>
                                        <td>
                                            <input type="radio" name="rbtnCabin" value="<%= cabin.getID() %>">
                                        </td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                            <div class="info-input">
                                <button type="submit" class="btn btn-primary">Đặt vé</button>
                                <button type="button" class="btn btn-default" id="btn-cancel">Trở về</button>
                            </div>
                        </form>
                    </div>
                </div>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/employee-flight.js"></script>
        <script src="/public/js/employee-search.js"></script>
    </body>
</html>