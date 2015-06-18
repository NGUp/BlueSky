<%@page import="DataTransferObject.Cabin"%>
<%@page import="DataAccessLayer.CabinHandler"%>
<%@page import="DataTransferObject.Flight"%>
<%@page import="DataAccessLayer.FlightHandler"%>
<%@page import="DataTransferObject.Plane"%>
<%@page import="DataAccessLayer.PlaneHandler"%>
<%@page import="DataTransferObject.Trip"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataAccessLayer.TripHandler"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DataTransferObject.Employee"%>
<%@page import="DataAccessLayer.EmployeeHandler"%>
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
        <link rel="stylesheet" href="/public/css/employee-app.css"/>
        <link rel="stylesheet" href="/public/css/employee-details-stewardess.css"/>
    </head>
    <body>

        <%
            if (Auth.authorizeManager(session) == false) {
                response.sendRedirect("/login.jsp");
                return;
            }
            
            String flightID = request.getParameter("flight");
            FlightHandler flightHandler = new FlightHandler();
            
            Flight flight = flightHandler.one(flightID);
            
            if (flight == null) {
                response.sendRedirect("/employee/flight.jsp");
                return;
            }
            
            TripHandler tripHandler = new TripHandler();
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
                </ul>
            </aside>
            <article class="col-md-9">
                <div class="group-navigator">
                    <div class="btn-navigate">
                        <button class="btn btn-warning" id="btn-back">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                        </button>
                    </div>
                    <h2 class="title" >
                        Chuyến bay: <span id="flight-id"><%= flight.getID() %></span> - <%= tripHandler.getName(flight.getTrip()) %>
                    </h2>
                </div>
                <%
                    CabinHandler cabinHandler = new CabinHandler();
                    EmployeeHandler employeeHandler = new EmployeeHandler();
                    
                    ArrayList<Cabin> cabins = cabinHandler.getByPlane(flight.getPlane());
                    
                    for (Cabin cabin : cabins) {
                %>
                    <div class="cabin">
                        <%
                            ArrayList<Employee> stewardesses = employeeHandler.getStewardessForAddByCabin(flight.getID(), cabin.getID());
                        %>
                        <h3>Loại khoang: <%= cabin.getName() %></h3>
                        <form action="/employee/flight/cabin/stewardess/addhandler" method="post">
                            <div class="row">
                                <div class="col-md-8">
                                    <h4>Nhân viên</h4>
                                    <input type="hidden" name="txtFlightID" value="<%= flight.getID() %>">
                                    <input type="hidden" name="txtCabinID" value="<%= cabin.getID() %>">
                                    <select class="combobox" name="cbxStewardess">
                                        <% for (Employee stewardess : stewardesses) { %>
                                            <option value="<%= stewardess.getID() %>"><%= stewardess.getName() %></option>
                                        <% } %>
                                    </select>
                                </div>
                                <div class="col-md-4">
                                    <button type="submit" class="btn btn-primary btn-insert">Thêm nhân viên</button>
                                </div>
                            </div>
                        </form>
                        <table class="table table-striped table-hover table-responsive">
                            <thead>
                                <tr>
                                    <th class="index">STT</th>
                                    <th>Tên tiếp viên</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<Employee> _stewardesses = employeeHandler.getStewardessByCabin(flight.getID(), cabin.getID());
                                    
                                    int index = 1;
                                    for (Employee _stewardess : _stewardesses) {
                                %>
                                    <tr>
                                        <td class="index"><%= index++ %></td>
                                        <td class="cabin-id"><%= cabin.getID() %></td>
                                        <td class="stewardess-id"><%= _stewardess.getID() %></td>
                                        <td><%= _stewardess.getName() %></td>
                                        <td>
                                            <button class="btn btn-danger btn-remove">
                                                <span class="glyphicon glyphicon-remove"></span>
                                            </button>
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                <%
                    }
                %>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/bootstrap-combobox.js"></script>
        <script src="/public/js/employee-details-stewardess.js"></script>
    </body>
</html>