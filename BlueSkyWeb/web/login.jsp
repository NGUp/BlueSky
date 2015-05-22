<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>BlueSky</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="/BlueSkyWeb/public/img/favicon.png" rel="shortcut icon">
        <link rel="stylesheet" href="/BlueSkyWeb/public/css/bootstrap.css"/>
        <link rel="stylesheet" href="/BlueSkyWeb/public/css/bootstrap-datepicker.css"/>
        <link rel="stylesheet" href="/BlueSkyWeb/public/css/bootstrap-theme.css"/>
        <link rel="stylesheet" href="/BlueSkyWeb/public/css/app.css"/>
        <link rel="stylesheet" href="/BlueSkyWeb/public/css/login.css"/>
    </head>
    <body>
        <header>
            <div class="container">
                <div class="navbar-header">
                    <a href="/BlueSkyWeb" class="navbar-brand">BlueSky</a>
                </div>
            </div>
        </header>
        <div class="container">
            <section>
                <article>
                    <h2>Đăng nhập</h2>
                    <form action="/BlueSkyWeb/loginhandler" method="post">
                        <div class="login-input">
                            <input class="form-control" type="text" placeholder="Tên đăng nhập"/>
                        </div>
                        <div class="login-input">
                            <input class="form-control" type="password" placeholder="Mật khẩu"/>
                        </div>
                        <div class="login-input">
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
