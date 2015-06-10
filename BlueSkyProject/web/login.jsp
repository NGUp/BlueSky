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
        <link rel="stylesheet" href="/public/css/login.css"/>
    </head>
    <body>
        <header>
            <div class="container">
                <div class="navbar-header">
                    <a href="/" class="navbar-brand">BlueSky</a>
                </div>
            </div>
        </header>
        <div class="container">
            <section>
                <article>
                    <h2>Đăng nhập</h2>
                    <form action="/app/loginhandler" method="post">
                        <div class="login-input">
                            <input class="form-control" name="txtEmail" type="email" placeholder="Email"/>
                        </div>
                        <div class="login-input">
                            <input class="form-control" name="txtPassword" type="password" placeholder="Mật khẩu"/>
                        </div>
                        <div class="login-input">
                            <button type="button" class="btn btn-default">Hủy</button>
                            <button type="submit" class="btn btn-login">Đăng nhập</button>
                        </div>
                    </form>
                </article>
            </section>
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
    </body>
</html>
