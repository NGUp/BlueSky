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
        <link rel="stylesheet" href="/BlueSkyWeb/public/css/index.css"/>
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
            <div class="banner">
                <img src="/BlueSkyWeb/public/img/plane.png" alt="BlueSky"/>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <aside>
                        <ul class="nav nav-pills nav-stacked">
                            <li role="presentation" class="active"><a href="javascript:void(0)">Home</a></li>
                            <li role="presentation"><a href="/BlueSkyWeb/login.jsp">Đăng nhập</a></li>
                            <li role="presentation"><a href="/BlueSkyWeb/register.jsp">Đăng ký</a></li>
                            <li role="presentation"><a href="javascript:void(0)">Cập nhật thông tin</a></li>
                            <li role="presentation"><a href="javascript:void(0)">Mua vé</a></li>
                            <li role="presentation"><a href="javascript:void(0)">Lịch sử mua vé</a></li>
                            <li role="presentation"><a href="javascript:void(0)">Thoát</a></li>
                        </ul>
                    </aside>
                </div>
                <div class="col-md-9">
                    <section>
                        <article class="book-content">
                            <h2>Mua vé trực tuyến</h2>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="row book">
                                        <div class="col-md-2 book-label">Đi từ</div>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" spellcheck="false"/>
                                        </div>
                                    </div>
                                    <div class="row book">
                                        <div class="col-md-2 book-label">Đến</div>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" spellcheck="false"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="row book">
                                        <div class="col-md-2 book-label">Đi từ</div>
                                        <div class="col-md-10">
                                            <div class="input-group date">
                                                <input type="text" class="form-control"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row book">
                                        <div class="col-md-2 book-label">Đến</div>
                                        <div class="col-md-10">
                                            <div class="input-group date">
                                                <input type="text" class="form-control"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <button type="button" class="btn btn-primary btn-book">Tìm chuyến bay</button>
                            <div class="clear"></div>
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
        <script src="public/js/bootstrap-datepicker.js"></script>
        
        <script type="text/javascript">
            $('.input-group.date').datepicker({
                daysOfWeekDisabled: "1"
            });
        </script>
    </body>
</html>
