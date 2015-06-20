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
        <link rel="stylesheet" href="/public/css/bootstrap-datepicker.css"/>
        <link rel="stylesheet" href="/public/css/employee-app.css"/>
        <link rel="stylesheet" href="/public/css/employee-info.css"/>
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
                    <% if (session.getAttribute("userPermission").equals("MANAGER")) { %>
                        <!-- for Manager -->
                        <li role="presentation"><a href="/employee/trip.jsp">Quản lý tuyến bay</a></li>
                        <li role="presentation"><a href="/employee/plane.jsp">Quản lý danh mục máy bay</a></li>
                        <li role="presentation"><a href="/employee/flight.jsp">Quản lý chuyến bay</a></li>
                        <li role="presentation"><a href="/employee/ticket.jsp">Quản lý bảng giá vé</a></li>
                    <% } else if (session.getAttribute("userPermission").equals("CONDUCTOR")) { %>
                        <!-- for Conductor -->
                        <li role="presentation"><a href="/employee/search.jsp">Tra cứu</a></li>
                        <li role="presentation"><a href="/employee/payment.jsp">Thanh toán</a></li>
                    <% } else if (session.getAttribute("userPermission").equals("PILOT")) { %>
                        <!-- for Pilot -->
                    <% } else if (session.getAttribute("userPermission").equals("STEWARDESS")) { %>
                        <!-- for Stewardess -->
                    <% } %>
                </ul>
            </aside>
            <article class="col-md-9">
                <div class="row">
                    <%
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        EmployeeHandler handler = new EmployeeHandler();
                        Employee employee = handler.one(session.getAttribute("userID").toString());
                    %>
                    <div class="col-md-6">
                        <h2 class="title">Cập nhật tài khoản nhân viên</h2>
                        <form action="/employee/infohandler" method="post">
                            <div class="info-input">
                                <h4>Họ tên</h4>
                                <input type="text" class="form-control" autocomplete="off" spellcheck="false" name="txtFullName" placeholder="Họ tên" value="<%= employee.getName() %>" />
                            </div>
                            <div class="info-input">
                                <h4>CMND</h4>
                                <input type="text" class="form-control" autocomplete="off" spellcheck="false" name="txtIdentityCard" placeholder="CMND" value="<%= employee.getIdentityCard() %>" />
                            </div>
                            <div class="info-input">
                                <h4>Email</h4>
                                <input type="email" class="form-control" autocomplete="off" spellcheck="false" name="txtEmail" placeholder="Email" value="<%= employee.getEmail() %>" />
                            </div>
                            <div class="info-input">
                                <h4>Ngày sinh</h4>
                                <div class="input-group date">
                                    <input type="text" class="form-control" name="txtBirthday" value="<%= formatter.format(employee.getBirthday()) %>">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-th"></i>
                                    </span>
                                </div>
                            </div>
                            <div class="info-input">
                                <h4>Địa chỉ</h4>
                                <input type="text" class="form-control" autocomplete="off" spellcheck="false" name="txtAddress" placeholder="Địa chỉ" value="<%= employee.getAddress() %>" />
                            </div>
                            <div class="info-input">
                                <h4>Số điện thoại</h4>
                                <input type="tel" class="form-control" autocomplete="off" spellcheck="false" name="txtPhone" placeholder="Điện thoại" value="<%= employee.getPhone() %>" />
                            </div>
                            <div>
                                <button type="submit" class="btn btn-primary">Tạo tài khoản</button>
                                <button type="button" class="btn btn-default">Hủy</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <h2 class="title">Thay đổi mật khẩu</h2>
                        <form action="/employee/passwordhandler" method="post">
                            <div class="info-input">
                                <h4>Mật khẩu cũ</h4>
                                <input class="form-control" type="password" name="txtPasswordOld" placeholder="Mật khẩu cũ"/>
                            </div>
                            <div class="info-input">
                                <h4>Mật khẩu mới</h4>
                                <input class="form-control" type="password" name="txtPasswordNew" placeholder="Mật khẩu mới"/>
                            </div>
                            <div class="info-input">
                                <h4>Xác nhận mật khẩu mới</h4>
                                <input class="form-control" type="password" name="txtPasswordConfirm" placeholder="Xác nhận mật khẩu mới"/>
                            </div>
                            <div class="info-input">
                                <button type="submit" class="btn btn-primary">Thay đổi mật khẩu</button>
                                <button type="button" class="btn btn-default" id="btn-cancel">Hủy</button>
                            </div>
                        </form>
                    </div>
                </div>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/bootstrap-datepicker.js"></script>
        <script src="/public/js/employee-info.js"></script>
    </body>
</html>