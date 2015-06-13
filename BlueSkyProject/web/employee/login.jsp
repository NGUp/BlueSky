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
        <link rel="stylesheet" href="/public/css/employee-login.css"/>
    </head>
    <body>
        <header>
            <h1 class="navbar-brand">
                <a href="/employee/index.jsp">BlueSky</a>
            </h1>
        </header>
        
        <section class="container row">
            <article>
                <h2 class="title">Đăng nhập</h2>
                <form action="/employee/loginhandler" method="post">
                    <div class="login-input">
                        <input class="form-control" name="txtUsername" type="text" spellchecker="false" placeholder="Tên đăng nhập"/>
                    </div>
                    <div class="login-input">
                        <input class="form-control" name="txtPassword" type="password" placeholder="Mật khẩu"/>
                    </div>
                    <div class="login-input">
                        <button type="submit" class="btn btn-primary">Đăng nhập</button>
                        <button type="button" class="btn btn-default">Hủy</button>
                    </div>
                </form>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
    </body>
</html>