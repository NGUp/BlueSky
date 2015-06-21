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
        <link rel="stylesheet" href="/public/css/bootstrap-datepicker.css"/>
        <link rel="stylesheet" href="/public/css/bootstrap-theme.css"/>
        <link rel="stylesheet" href="/public/css/app.css"/>
        <link rel="stylesheet" href="/public/css/index.css"/>
    </head>
    <body>
        <header>
            <div class="container">
                <div class="navbar-header">
                    <a href="/" class="navbar-brand">BlueSky</a>
                </div>
                <div class="header-user-name">
                    ${sessionScope.userName}
                </div>
            </div>
        </header>
        <div class="container">
            <div class="banner">
                <img src="/public/img/plane.png" alt="BlueSky"/>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <aside>
                        <ul class="nav nav-pills nav-stacked">
                            <li role="presentation" class="active"><a href="javascript:void(0)">Menu</a></li>
                            <% if (session.getAttribute("userName") == null) {  %>
                                <li role="presentation"><a href="/login.jsp">Đăng nhập</a></li>
                                <li role="presentation"><a href="/register.jsp">Đăng ký</a></li>
                            <% } else { %>
                                <li role="presentation"><a href="/info.jsp">Cập nhật thông tin</a></li>
                                <li role="presentation"><a href="javascript:void(0)">Lịch sử mua vé</a></li>
                                <li role="presentation"><a href="/app/logouthandler">Thoát</a></li>
                            <% } %>
                        </ul>
                    </aside>
                </div>
                <div class="col-md-9">
                    <section>
                        <article class="book-content">
                            <h2>Mua vé trực tuyến</h2>
                            <form method="post" action="/search.jsp">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="row book">
                                            <div class="col-md-2 book-label">Đi từ</div>
                                            <div class="col-md-10">
                                                <input type="text" class="form-control" name="txtFrom" spellcheck="false"/>
                                            </div>
                                        </div>
                                        <div class="row book">
                                            <div class="col-md-2 book-label">Đến</div>
                                            <div class="col-md-10">
                                                <input type="text" class="form-control" name="txtTo" spellcheck="false"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="row book">
                                            <div class="col-md-3 book-label">Bắt đầu</div>
                                            <div class="col-md-9">
                                                <div class="input-group date">
                                                    <input type="text" class="form-control" name="txtStartTime">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-th"></i>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row book">
                                            <div class="col-md-3 book-label">Kết thúc</div>
                                            <div class="col-md-9">
                                                <div class="input-group date">
                                                    <input type="text" class="form-control" name="txtEndTime">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-th"></i>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary btn-book">Tìm chuyến bay</button>
                                <div class="clear"></div>
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
        <script src="public/js/bootstrap-datepicker.js"></script>
        <script src="public/js/index.js"></script>
    </body>
</html>
