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
        <link rel="stylesheet" href="/public/css/admin-app.css"/>
        <link rel="stylesheet" href="/public/css/admin-create-employee.css"/>
    </head>
    <body>
        
        <%
            if (Auth.authorizeAdmin(session) == false) {
                response.sendRedirect("/login.jsp");
                return;
            }
        %>
        
        <header>
            <h1 class="navbar-brand">
                <a href="/admin/index.jsp">BlueSky</a>
            </h1>
            <ul class="nav-user">
                <li><%= session.getAttribute("userName") %></li>
                <li>
                    <a href="/admin/logouthandler">Đăng xuất</a>
                </li>
            </ul>
        </header>
        
        <section class="container">
            <aside class="col-md-3 nav-menu">
                <h3 class="nav-header">Menu</h3>
                <ul class="nav nav-pills nav-stacked">
                    <li role="presentation"><a href="/admin/info.jsp">Thay đổi mật khẩu</a></li>
                    <li role="presentation"><a href="/admin/create.jsp">Tạo tài khoản nhân viên</a></li>
                    <li role="presentation"><a href="/admin/customer.jsp">Quản lý tài khoản khách hàng</a></li>
                    <li role="presentation"><a href="/admin/employee.jsp">Quản lý tài khoản nhân viên</a></li>
                    <li role="presentation"><a href="/admin/config.jsp">Quản lý cấu hình Website</a></li>
                </ul>
            </aside>
            <article class="col-md-9">
                <div class="row">
                    <div class="col-md-6">
                        <h2 class="title">Tạo tài khoản nhân viên</h2>
                        <form action="/admin/employee/createhandler" method="post">
                            <div class="employee-input">
                                <h4>Họ tên</h4>
                                <input type="text" class="form-control" autocomplete="off" spellcheck="false" name="txtFullName" placeholder="Họ tên" />
                            </div>
                            <div class="employee-input">
                                <h4>CMND</h4>
                                <input type="text" class="form-control" autocomplete="off" spellcheck="false" name="txtID" placeholder="CMND" />
                            </div>
                            <div class="employee-input">
                                <h4>Email</h4>
                                <input type="email" class="form-control" autocomplete="off" spellcheck="false" name="txtEmail" placeholder="Email" />
                            </div>
                            <div class="employee-input">
                                <h4>CMND</h4>
                                <select class="combobox" name="cbxGender">
                                    <option value="Nam">Nam</option>
                                    <option value="Nữ">Nữ</option>
                                </select>
                            </div>
                            <div class="employee-input">
                                <h4>Tên đăng nhập</h4>
                                <input type="text" class="form-control" autocomplete="off" spellcheck="false" name="txtUserName" placeholder="Tên đăng nhập" />
                            </div>
                            <div class="employee-input">
                                <h4>Ngày sinh</h4>
                                <div class="input-group date">
                                    <input type="text" class="form-control" name="txtBirthday">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-th"></i>
                                    </span>
                                </div>
                            </div>
                            <div class="employee-input">
                                <h4>Địa chỉ</h4>
                                <input type="text" class="form-control" autocomplete="off" spellcheck="false" name="txtAddress" placeholder="Địa chỉ" />
                            </div>
                            <div class="employee-input">
                                <h4>Số điện thoại</h4>
                                <input type="tel" class="form-control" autocomplete="off" spellcheck="false" name="txtPhone" placeholder="Điện thoại" />
                            </div>
                            <div>
                                <button type="submit" class="btn btn-primary">Tạo tài khoản</button>
                                <button type="button" class="btn btn-default">Hủy</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <h2 class="title">Import tài khoản</h2>
                        <div>
                            Chưa hỗ trợ chức năng này.
                        </div>
                    </div>
                </div>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/bootstrap-combobox.js"></script>
        <script src="/public/js/bootstrap-datepicker.js"></script>
        <script src="/public/js/admin-create-employee.js"></script>
    </body>
