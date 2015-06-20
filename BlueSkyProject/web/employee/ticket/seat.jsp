<%@page import="DataAccessLayer.TicketHandler"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataAccessLayer.TripHandler"%>
<%@page import="DataTransferObject.Cabin"%>
<%@page import="DataTransferObject.Plane"%>
<%@page import="DataAccessLayer.CabinHandler"%>
<%@page import="DataAccessLayer.PlaneHandler"%>
<%@page import="DataTransferObject.Flight"%>
<%@page import="DataAccessLayer.FlightHandler"%>
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
        <link rel="stylesheet" href="/public/css/employee-book-seat.css"/>
    </head>
    <body>

        <%
            if (Auth.authorizeConductor(session) == false) {
                response.sendRedirect("/login.jsp");
                return;
            }
            
            if (request.getParameter("txtFlightID") == null ||
                    request.getParameter("txtPlaneID") == null || 
                    request.getParameter("rbtnCabin") == null) {
                String referer = request.getHeader("Referer");
                response.sendRedirect(referer);
                return;
            }
            
            String flightID = request.getParameter("txtFlightID");
            String planeID = request.getParameter("txtPlaneID");
            String cabinID = request.getParameter("rbtnCabin");
            
            FlightHandler flightHandler = new FlightHandler();
            PlaneHandler planeHandler = new PlaneHandler();
            CabinHandler cabinHandler = new CabinHandler();
            TripHandler tripHandler = new TripHandler();
            TicketHandler ticketHandler = new TicketHandler();
            
            Flight flight = flightHandler.one(flightID);
            Plane plane = planeHandler.one(planeID);
            Cabin cabin = cabinHandler.getDetails(planeID, cabinID);
            
            if (flight == null || plane == null || cabin == null) {
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
                <div class="group-navigator">
                    <div class="button-back">
                        <button class="btn btn-warning" id="btn-back">
                            <span class="glyphicon glyphicon-arrow-left"></span>
                        </button>
                    </div>
                    <h2 class="title">
                        Đặt vé
                    </h2>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <h4 class="sub-title">Chuyến bay: <span><%= tripHandler.getName(flight.getTrip()) %></span></h4>
                        <h4 class="sub-title">Khoang: <span><%= cabin.getName() %></span></h4>
                        <h4 class="sub-title">Sơ đồ khoang</h4>
                        <div>
                            <table class="seats">
                                <tbody>
                                    <% int index = 1; %>
                                    <% for (int row = 0; row < cabin.getRows(); row++) { %>
                                        <tr>
                                            <% for (int column = 0; column < cabin.getColumns() + (cabin.getColumns() - 1); column++) { %>
                                                <% if (column % 2 == 0) { %>
                                                    <td class="seat" id="<%= index %>"><%= index++ %></td>
                                                <% } else {%>
                                                    <td></td>
                                                <% } %>
                                            <% } %>
                                        </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <%
                            ArrayList<String> bookedSeats = ticketHandler.getBookedSeats(flightID, cabinID);
                            
                            if (bookedSeats.size() < (cabin.getColumns() * cabin.getRows())) {
                        %>
                            <div class="group-info">
                                <h3 class="sub-title">Khách hàng thành viên</h3>
                                <form method="post" action="/employee/ticket/book/memberhandler">
                                    <input type="hidden" name="txtFlightID" class="txt-flight" value="<%= flightID %>" />
                                    <input type="hidden" name="txtCabinID" class="txt-cabin" value="<%= cabinID %>" />
                                    <input type="hidden" name="txtSeat" class="txt-seat"/>
                                    <div class="info">
                                        <h4>Email</h4>
                                        <input type="email" class="form-control" autocomplete="off" spellcheck="false" name="txtEmail" placeholder="Email" />
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-payment">Thanh toán</button>
                                </form>
                                <div class="clear"></div>
                            </div>
                            <div class="group-info">
                                <h3 class="sub-title">Khách hàng chưa là thành viên</h3>
                                <form method="post" action="/employee/ticket/book/nonmemberhandler">
                                    <input type="hidden" name="txtFlightID" class="txt-flight" value="<%= flightID %>" />
                                    <input type="hidden" name="txtCabinID" class="txt-cabin" value="<%= cabinID %>" />
                                    <input type="hidden" name="txtSeat" class="txt-seat"/>
                                    <div class="info">
                                        <h4>Họ tên</h4>
                                        <input type="text" class="form-control" autocomplete="off" spellcheck="false" name="txtName" placeholder="Email" />
                                    </div>
                                    <div class="info">
                                        <h4>Email</h4>
                                        <input type="email" class="form-control" autocomplete="off" spellcheck="false" name="txtEmail" placeholder="Email" />
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-payment">Thanh toán</button>
                                </form>
                            </div>
                        <%
                            }
                        %>
                    </div>
                </div>
                
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/employee-flight.js"></script>
        <script src="/public/js/employee-book-seat.js"></script>
    </body>
</html>