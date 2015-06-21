<%@page import="DataAccessLayer.FlightHandler"%>
<%@page import="DataTransferObject.Flight"%>
<%@page import="DataTransferObject.Cabin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataTransferObject.Trip"%>
<%@page import="DataTransferObject.Plane"%>
<%@page import="DataAccessLayer.TicketPriceHandler"%>
<%@page import="DataAccessLayer.CabinHandler"%>
<%@page import="DataAccessLayer.AirportHandler"%>
<%@page import="DataAccessLayer.PlaneHandler"%>
<%@page import="java.text.DateFormat"%>
<%@page import="DataAccessLayer.TripHandler"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
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
        <link rel="stylesheet" href="/public/css/app.css"/>
        <link rel="stylesheet" href="/public/css/book.css"/>
    </head>
    <body>
        <%
            if (session.getAttribute("userID") == null) {
                response.sendRedirect("/login.jsp");
                return;
            }
        %>
        <header>
            <div class="container">
                <div class="navbar-header">
                    <a href="/" class="navbar-brand">BlueSky</a>
                </div>
                <div class="header-user-name">
                    ${sessionScope.userName}
                </div>
            </div>
        </header>
        <div class="container">
            <div class="banner">
                <img src="/public/img/plane.png" alt="BlueSky"/>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <aside>
                        <ul class="nav nav-pills nav-stacked">
                            <li role="presentation" class="active"><a href="javascript:void(0)">Menu</a></li>
                            <% if (session.getAttribute("userName") == null) {  %>
                                <li role="presentation"><a href="/login.jsp">Đăng nhập</a></li>
                                <li role="presentation"><a href="/register.jsp">Đăng ký</a></li>
                            <% } else { %>
                                <li role="presentation"><a href="/info.jsp">Cập nhật thông tin</a></li>
                                <li role="presentation"><a href="javascript:void(0)">Lịch sử mua vé</a></li>
                                <li role="presentation"><a href="/app/logouthandler">Thoát</a></li>
                            <% } %>
                        </ul>
                    </aside>
                </div>
                <div class="col-md-9">
                    <section class="content">
                        <%
                            String flightID = request.getParameter("flightID");
                            FlightHandler flightHandler = new FlightHandler();

                            Flight flight = flightHandler.one(flightID);

                            if (flight == null) {
                                response.sendRedirect("/");
                                return;
                            }
                            
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
                                <form method="post" action="/seat.jsp">
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
                    </section>
                </div>
            </div>  
        </div>
        <footer>
            <div class="container">
                <div class="footer-copyright">
                    &copy; <%
                        GregorianCalendar cal = new GregorianCalendar();
                        out.print(cal.get(Calendar.YEAR));
                    %> - 110001NP Development Team
                </div>
            </div>
        </footer>
        
        <script src="public/js/jquery.js"></script>
        <script src="public/js/bootstrap.js"></script>
        <script src="public/js/book.js"></script>
    </body>
</html>
