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
        <link rel="stylesheet" href="/public/css/admin-app.css"/>
        <link rel="stylesheet" href="/public/css/admin-info.css"/>
    </head>
    <body>

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
                    <li role="presentation"><a href="#">Tạo tài khoản nhân viên</a></li>
                    <li role="presentation"><a href="#">Quản lý tài khoản người dùng</a></li>
                    <li role="presentation"><a href="#">Quản lý cấu hình Website</a></li>
                </ul>
            </aside>
            <article class="col-md-9">
                <div class="content">
                    <h2 class="title">Thay đổi mật khẩu</h2>
                    <form action="/admin/passwordhandler" method="post">
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
                            <input type="hidden" name="txtID" value="<%= session.getAttribute("userID") %>" />
                            <button type="submit" class="btn btn-primary">Thay đổi mật khẩu</button>
                            <button type="button" class="btn btn-default" id="btn-cancel">Hủy</button>
                        </div>
                    </form>
                </div> 
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/admin-info.js"></script>
    </body>
