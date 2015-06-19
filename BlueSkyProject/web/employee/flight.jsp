<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DataAccessLayer.FlightHandler"%>
<%@page import="DataTransferObject.Flight"%>
<%@page import="java.util.ArrayList"%>
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
        <link rel="stylesheet" href="/public/css/employee-flight.css"/>
    </head>
    <body>

        <%
            if (Auth.authorizeManager(session) == false) {
                response.sendRedirect("/login.jsp");
                return;
            }
            
            String keyword = "";
            
            if (request.getParameter("keyword") != null) {
                keyword = request.getParameter("keyword");
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
            <article class="col-md-9">
                <h2 class="title">Quản lý chuyến bay</h2>
                <div class="input-navigator">
                    <div class="input-group group-search">
                        <input class="form-control" placeholder="Từ khóa"id="keyword" value="<%= keyword %>">
                        <span class="input-group-btn">
                            <button class="btn btn-primary" id="btn-search">
                                <span class="glyphicon glyphicon-search"></span>
                                Tìm kiếm
                            </button>
                            <button class="btn btn-default" id="btn-clear">
                                <span class="glyphicon glyphicon-repeat"></span>
                            </button>
                        </span>
                    </div>
                    <div>
                        <button class="btn btn-success btn-insert" id="btn-insert">Thêm chuyến bay</button>
                    </div>
                </div>
                <table class="table table-striped table-hover table-responsive">
                    <thead>
                        <th class="center">STT</th>
                        <th class="flight-id">Mã chuyến</th>
                        <th>Khởi hành</th>
                        <th>Hạ cánh</th>
                        <th>Mã tuyến</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </thead>
                    <tbody>
                        <%
                            FlightHandler flightHandler = new FlightHandler();
                            
                            int currentPage = 1;
                            int index = 1;
                            
                            if (request.getParameter("page") != null) {
                                currentPage = Integer.parseInt(request.getParameter("page"));
                            }
                            
                            int totalPage = 0;
                            ArrayList<Flight> flights = null;
                            
                            if ("".equals(keyword)) {
                                totalPage = flightHandler.totalPage();
                                flights = flightHandler.limit(currentPage);
                            } else {
                                totalPage = flightHandler.totalPageWithKeyword(keyword);                                
                                flights = flightHandler.limitWithKeyword(currentPage, keyword);
                                
                                if (totalPage == 0) {
                                    currentPage = 0;
                                }
                            }
                            
                            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            for (Flight flight : flights) {
                        %>
                            <tr>
                                <td class="center"><%= index++ %></td>
                                <td class="trip-id"><%= flight.getID() %></td>
                                <td><%= format.format(flight.getDeparture()) %></td>
                                <td><%= format.format(flight.getArrival()) %></td>
                                <td><%= flight.getTrip() %></td>
                                <td>
                                    <button class="btn btn-default btn-details">
                                        <span class="glyphicon glyphicon-list-alt"></span>
                                    </button>
                                </td>
                                <td>
                                    <button class="btn btn-default btn-employee">
                                        <span class="glyphicon glyphicon-user"></span>
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
                <nav>
                    <ul class="pager">
                        <li><a href="javascript:void(0)" id="btn-previous">Previous</a></li>
                        <li>
                            <span id="current-page"><%= currentPage %></span> / <span id="total-page"><%= totalPage %></span>
                        </li>
                        <li><a href="javascript:void(0)" id="btn-next">Next</a></li>
                    </ul>
                </nav>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/employee-flight.js"></script>
    </body>
</html>