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
        <link rel="stylesheet" href="/BlueSkyWeb/public/css/info.css"/>
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
            <div class="row">
                <div class="col-md-3">
                    <aside>
                        <ul class="nav nav-pills nav-stacked">
                            <li role="presentation" class="active"><a href="javascript:void(0)">Menu</a></li>
                            <li role="presentation"><a href="javascript:void(0)">Mua vé</a></li>
                            <li role="presentation"><a href="javascript:void(0)">Lịch sử mua vé</a></li>
                            <li role="presentation"><a href="/BlueSkyWeb/logouthander">Thoát</a></li>
                        </ul>
                    </aside>
                </div>
                <div class="col-md-9">
                    <section>
                        <article>
                            <h2>Cập nhật thông tin</h2>
                            <form action="/BlueSkyWeb/infohandler" method="post">
                                <div class="info-input">
                                    <h4>Họ tên</h4>
                                    <input class="form-control" type="text" placeholder="Họ tên"/>
                                </div>
                                <div class="info-input">
                                    <h4>Mật khẩu cũ</h4>
                                    <input class="form-control" type="password" placeholder="Mật khẩu cũ"/>
                                </div>
                                <div class="info-input">
                                    <h4>Mật khẩu mới</h4>
                                    <input class="form-control" type="password" placeholder="Mật khẩu mới"/>
                                </div>
                                <div class="info-input">
                                    <h4>Xác nhận mật khẩu mới</h4>
                                    <input class="form-control" type="password" placeholder="Xác nhận mật khẩu"/>
                                </div>
                                <div class="info-input">
                                    <button type="submit" class="btn btn-info">Cập nhật</button>
                                </div>
                            </form>
                        </article>
                    </section>
                </div>
            </div>  
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
