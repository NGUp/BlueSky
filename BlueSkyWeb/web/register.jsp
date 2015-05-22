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
        <link rel="stylesheet" href="/BlueSkyWeb/public/css/register.css"/>
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
                    <h2>Đăng ký</h2>
                    <form action="/BlueSkyWeb/registerhandler" method="post">
                        <div class="register-input">
                            <h4>Họ tên</h4>
                            <input class="form-control" type="text" placeholder="Họ tên"/>
                        </div>
                        <div class="register-input">
                            <h4>Email</h4>
                            <input class="form-control" type="email" placeholder="Email"/>
                        </div>
                        <div class="register-input">
                            <h4>Mật khẩu</h4>
                            <input class="form-control" type="password" placeholder="Mật khẩu"/>
                        </div>
                        <div class="register-input">
                            <h4>Xác nhận mật khẩu</h4>
                            <input class="form-control" type="password" placeholder="Xác nhận mật khẩu"/>
                        </div>
                        <div class="register-input">
                            <button type="submit" class="btn btn-register">Đăng ký</button>
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
