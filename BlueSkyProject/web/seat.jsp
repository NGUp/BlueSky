<%@page import="DataTransferObject.Cabin"%>
<%@page import="DataTransferObject.Plane"%>
<%@page import="DataTransferObject.Flight"%>
<%@page import="DataAccessLayer.TicketHandler"%>
<%@page import="DataAccessLayer.TripHandler"%>
<%@page import="DataAccessLayer.CabinHandler"%>
<%@page import="DataAccessLayer.PlaneHandler"%>
<%@page import="DataAccessLayer.FlightHandler"%>
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
        <link rel="stylesheet" href="/public/css/seat.css"/>
    </head>
    <body>
        <%
            if (session.getAttribute("userID") == null) {
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
                String referer = request.getHeader("Referer");
                response.sendRedirect(referer);
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
                            <li role="presentation"><a href="/info.jsp">Cập nhật thông tin</a></li>
                            <li role="presentation"><a href="/history.jsp">Lịch sử mua vé</a></li>
                            <li role="presentation"><a href="/app/logouthandler">Thoát</a></li>
                        </ul>
                    </aside>
                </div>
                <div class="col-md-9">
                    <section>
                        <article>
                            <h2 class="title">Đặt vé</h2>
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
                        </article>
                        <article>
                            <form method="post" action="/customer/payment">
                                <input type="hidden" name="txtFlightID" class="txt-flight" value="<%= flightID %>" />
                                <input type="hidden" name="txtCabinID" class="txt-cabin" value="<%= cabinID %>" />
                                <input type="hidden" name="txtSeat" class="txt-seat"/>
                                <input type="hidden" name="txtUserID" value="<%= session.getAttribute("userID") %>"/>
                                <button class="btn btn-success" type="submit">Đặt vé</button>
                            </form>
                        </article>
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
        <script src="public/js/bootstrap-datepicker.js"></script>
        <script src="public/js/seat.js"></script>
    </body>
</html>
