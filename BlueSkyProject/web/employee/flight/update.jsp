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
        <link rel="stylesheet" href="/public/css/bootstrap-datepicker.css"/>
        <link rel="stylesheet" href="/public/css/employee-app.css"/>
        <link rel="stylesheet" href="/public/css/employee-update-flight.css"/>
    </head>
    <body>

        <%
            if (Auth.authorizeManager(session) == false) {
                response.sendRedirect("/login.jsp");
                return;
            }
            
            if (request.getParameter("flight") == null) {
                response.sendRedirect("/employee/flight.jsp");
                return;
            }
            
            String flightID = request.getParameter("flight");
            FlightHandler flightHandler = new FlightHandler();
            
            Flight flight = flightHandler.one(flightID);
            
            if (flight == null) {
                response.sendRedirect("/employee/flight.jsp");
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
                <%
                    DateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
                    DateFormat formatterTime = new SimpleDateFormat("HH:mm");
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    TripHandler tripHandler = new TripHandler();
                    EmployeeHandler employeeHandler = new EmployeeHandler();
                    PlaneHandler planeHandler = new PlaneHandler();

                    ArrayList<Trip> trips = tripHandler.getAll();
                    ArrayList<Employee> pilots = employeeHandler.getPilot();
                    ArrayList<Employee> stewardesses = employeeHandler.getStewardess();
                    ArrayList<Plane> planes = planeHandler.getAll();
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
                            <%
                                Plane _plane = planeHandler.one(flight.getPlane());
                            %>
                            <h4>Máy bay: <span><%= _plane.getManufacturer() %> <%= _plane.getName() %></span></h4>
                        </div>
                        <div>
                            <h4>Phi công chính: <span><%= employeeHandler.getName(flight.getMainPilot()) %></span></h4>
                        </div>
                        <div>
                            <h4>Phi công phụ: <span><%= employeeHandler.getName(flight.getVicePilot()) %></span></h4>
                        </div>
                        <div>
                            <h4>Tiếp viên trưởng: <span><%= employeeHandler.getName(flight.getMainStewardess()) %></span></h4>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <h2 class="title">Cập nhật chuyến bay</h2>
                        <form action="/employee/flight/updatehandler" method="post">
                            <input type="hidden" name="txtID" value="<%= flight.getID() %>" />
                            <div class="flight-input">
                                <h4>Khởi hành</h4>
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="input-group date">
                                            <input type="text" class="form-control" name="txtDateDeparture" value="<%= formatterDate.format(flight.getDeparture()) %>">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-th"></i>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" autocomplete="off" spellcheck="false" name="txtTimeDeparture" placeholder="hh:mm" value="<%= formatterTime.format(flight.getDeparture()) %>">
                                    </div>
                                </div>
                            </div>
                            <div class="flight-input">
                                <h4>Hạ cánh</h4>
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="input-group date">
                                            <input type="text" class="form-control" name="txtDateArrival" value="<%= formatterDate.format(flight.getArrival()) %>">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-th"></i>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" autocomplete="off" spellcheck="false" name="txtTimeArrival" placeholder="hh:mm" value="<%= formatterTime.format(flight.getArrival()) %>">
                                    </div>
                                </div>
                            </div>
                            <div class="flight-input">
                                <h4>Tuyến</h4>
                                <select class="combobox" name="cbxTrip">
                                    <% for (Trip trip : trips) { %>
                                        <option value="<%= trip.getID() %>"><%= trip.getName() %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="flight-input">
                                <h4>Máy bay</h4>
                                <select class="combobox" name="cbxPlane">
                                    <% for (Plane plane : planes) { %>
                                        <option value="<%= plane.getID() %>"><%= plane.getID() %> - <%= plane.getName() %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="flight-input">
                                <h4>Phi công trưởng</h4>
                                <select class="combobox" name="cbxMainPilot">
                                    <% for (Employee pilot : pilots) { %>
                                        <option value="<%= pilot.getID() %>"><%= pilot.getName() %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="flight-input">
                                <h4>Phi công phụ</h4>
                                <select class="combobox" name="cbxVicePilot">
                                    <% for (Employee pilot : pilots) { %>
                                        <option value="<%= pilot.getID() %>"><%= pilot.getName() %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="flight-input">
                                <h4>Tiếp viên trưởng</h4>
                                <select class="combobox" name="cbxMainStewardess">
                                    <% for (Employee stewardess : stewardesses) { %>
                                        <option value="<%= stewardess.getID() %>"><%= stewardess.getName() %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div>
                                <button type="submit" class="btn btn-primary">Cập nhật chuyến bay</button>
                                <button type="button" class="btn btn-default" id="btn-cancel">Hủy</button>
                            </div>
                        </form>
                    </div>
                </div>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/bootstrap-combobox.js"></script>
        <script src="/public/js/bootstrap-datepicker.js"></script>
        <script src="/public/js/employee-update-flight.js"></script>
    </body>
</html>