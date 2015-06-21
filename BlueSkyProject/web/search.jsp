<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="DataTransferObject.Flight"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
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
        <link rel="stylesheet" href="/public/css/search.css"/>
    </head>
    <body>
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
                            String from = request.getParameter("txtFrom");
                            String to = request.getParameter("txtTo");
                            String start = request.getParameter("txtStartTime");
                            String end = request.getParameter("txtEndTime");
                            
                            int index = 1;
                            FlightHandler flightHandler = new FlightHandler();
                            
                            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:MM");
                            ArrayList<Flight> flights = flightHandler.find(from, to, new Date(start), new Date(end));
                        %>
                        <article>
                            <h2>Danh sách chuyến bay</h2>
                            <table class="table table-striped table-hover table-responsive">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Chuyến bay</th>
                                        <th>Khởi hành</th>
                                        <th>Hạ cánh</th>
                                        <% if (session.getAttribute("userName") != null) {  %>
                                            <th></th>
                                        <% } %>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (Flight flight : flights) { %>
                                        <tr>
                                            <td><%= index++ %></td>
                                            <td class="hidden"><%= flight.getID() %></td>
                                            <td><%= flight.getTrip() %></td>
                                            <td><%= formatter.format(flight.getDeparture()) %></td>
                                            <td><%= formatter.format(flight.getArrival()) %></td>
                                            <% if (session.getAttribute("userName") != null) {  %>
                                            <td>
                                                <button class="btn btn-primary btn-book">
                                                    <span class="glyphicon glyphicon-list-alt"></span>
                                                </button>
                                            </td>
                                        <% } %>
                                        </tr>
                                    <% } %>
                                </tbody>
                            </table>
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
        <script src="public/js/search.js"></script>
    </body>
</html>
