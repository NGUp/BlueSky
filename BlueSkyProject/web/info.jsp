<%@page import="DataTransferObject.Customer"%>
<%@page import="DataAccessLayer.CustomerHandler"%>
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
        <link rel="stylesheet" href="/public/css/info.css"/>
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
                                <li role="presentation"><a href="javascript:void(0)">Mua vé</a></li>
                                <li role="presentation"><a href="javascript:void(0)">Lịch sử mua vé</a></li>
                                <li role="presentation"><a href="/app/logouthandler">Thoát</a></li>
                            <% } %>
                        </ul>
                    </aside>
                </div>
                <div class="col-md-9">
                    <section class="row content">
                        <% 
                            CustomerHandler handler = new CustomerHandler();
                            Customer customer = handler.one(session.getAttribute("userID").toString());
                        %>
                        <h2>Cập nhật thông tin</h2>
                        <article class="col-md-6">
                            <form action="/app/infohandler" method="post">
                                <div class="info-input">
                                    <h4>Chứng minh thư</h4>
                                    <input class="form-control" type="text" name="txtIdentityCard" placeholder="Chứng minh thư" autocomplete="off" spellcheck="false" value="<%= customer.getIdentityCard() %>"/>
                                </div>
                                <div class="info-input">
                                    <h4>Họ tên</h4>
                                    <input class="form-control" type="text" name="txtFullName" placeholder="Họ tên" autocomplete="off" spellcheck="false" value="<%= customer.getName() %>"/>
                                </div>
                                <div class="info-input">
                                    <h4>Email</h4>
                                    <input class="form-control" type="email" name="txtEmail" placeholder="Email" disabled="true" autocomplete="off" spellcheck="false" value="<%= customer.getEmail() %>"/>
                                </div>
                                <div class="info-input">
                                    <h4>Số điện thoại</h4>
                                    <input class="form-control" type="tel" name="txtPhone" placeholder="Số điện thoại" autocomplete="off" spellcheck="false"value="<%= customer.getPhone() %>"/>
                                </div>
                                <div class="info-input">
                                    <h4>Địa chỉ</h4>
                                    <input class="form-control" type="text" name="txtAddress" placeholder="Địa chỉ" autocomplete="off" spellcheck="false"value="<%= customer.getAddress() %>"/>
                                </div>
                                <div class="info-input">
                                    <input type="hidden" name="txtID" value="<%= session.getAttribute("userID") %>" />
                                    <button type="submit" class="btn btn-update">Cập nhật thông tin</button>
                                    <button type="button" class="btn btn-cancel">Hủy</button>
                                </div>
                            </form>
                        </article>
                        <article class="col-md-6">
                            <h2>Thay đổi mật khẩu</h2>
                            <form action="/app/passwordhandler" method="post">
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
                                    <button type="submit" class="btn btn-update">Thay đổi mật khẩu</button>
                                    <button type="button" class="btn btn-cancel">Hủy</button>
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
        <script src="public/js/info.js"></script>
    </body>
</html>
