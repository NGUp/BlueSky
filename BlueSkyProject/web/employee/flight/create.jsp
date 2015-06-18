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
        <link rel="stylesheet" href="/public/css/employee-create-flight.css"/>
    </head>
    <body>

        <%
            if (Auth.authorizeEmployee(session) == false) {
                response.sendRedirect("/login.jsp");
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
                    <li role="presentation"><a href="/employee/trip.jsp">Quản lý tuyến bay</a></li>
                    <li role="presentation"><a href="/employee/plane.jsp">Quản lý danh mục máy bay</a></li>
                    <li role="presentation"><a href="/employee/flight.jsp">Quản lý chuyến bay</a></li>
                </ul>
            </aside>
            <article class="col-md-9">
                <div class="content">
                    <%
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        TripHandler tripHandler = new TripHandler();
                        EmployeeHandler employeeHandler = new EmployeeHandler();
                        PlaneHandler planeHandler = new PlaneHandler();
                        
                        ArrayList<Trip> trips = tripHandler.getAll();
                        ArrayList<Employee> pilots = employeeHandler.getPilot();
                        ArrayList<Employee> stewardesses = employeeHandler.getStewardess();
                        ArrayList<Plane> planes = planeHandler.getAll();
                    %>
                    <h2 class="title">Thêm chuyến bay</h2>
                    <form action="/employee/flight/createhandler" method="post">
                        <div class="flight-input">
                            <h4>Mã chuyến</h4>
                            <input type="text" class="form-control" autocomplete="off" spellcheck="false" name="txtID" placeholder="Mã chuyến bay"/>
                        </div>
                        <div class="flight-input">
                            <h4>Khởi hành</h4>
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="input-group date">
                                        <input type="text" class="form-control" name="txtDateDeparture">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-th"></i>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" autocomplete="off" spellcheck="false" name="txtTimeDeparture" placeholder="hh:mm">
                                </div>
                            </div>
                        </div>
                        <div class="flight-input">
                            <h4>Hạ cánh</h4>
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="input-group date">
                                        <input type="text" class="form-control" name="txtDateArrival">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-th"></i>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" autocomplete="off" spellcheck="false" name="txtTimeArrival" placeholder="hh:mm">
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
                            <button type="submit" class="btn btn-primary">Tạo chuyến bay</button>
                            <button type="button" class="btn btn-default" id="btn-cancel">Hủy</button>
                        </div>
                    </form>
                </div>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/bootstrap-combobox.js"></script>
        <script src="/public/js/bootstrap-datepicker.js"></script>
        <script src="/public/js/employee-create-flight.js"></script>
    </body>
</html>