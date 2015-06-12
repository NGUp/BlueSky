<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataTransferObject.Customer"%>
<%@page import="DataAccessLayer.CustomerHandler"%>
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
        <link rel="stylesheet" href="/public/css/admin-app.css"/>
        <link rel="stylesheet" href="/public/css/admin-customer.css"/>
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
        
        <section class="container row">
            <%
                if (session.getAttribute("userPermission").equals("ADMIN")) {
            %>
            <aside class="col-md-3 nav-menu">
                <h3 class="nav-header">Menu</h3>
                <ul class="nav nav-pills nav-stacked">
                    <li role="presentation"><a href="/admin/info.jsp">Thay đổi mật khẩu</a></li>
                    <li role="presentation"><a href="/admin/create.jsp">Tạo tài khoản nhân viên</a></li>
                    <li role="presentation"><a href="/admin/customer.jsp">Quản lý tài khoản khách hàng</a></li>
                    <li role="presentation"><a href="/admin/employee.jsp">Quản lý tài khoản nhân viên</a></li>
                    <li role="presentation"><a href="#">Quản lý cấu hình Website</a></li>
                </ul>
            </aside>
            <% } %>
            <article class="col-md-9">
                <div class="input-group group-search">
                    <input class="form-control" placeholder="Từ khóa">
                    <span class="input-group-btn">
                        <button class="btn btn-primary">
                            <span class="glyphicon glyphicon-search"></span>
                            Tìm kiếm
                        </button>
                        <button class="btn btn-default" id="btn-clear">
                            <span class="glyphicon glyphicon-repeat"></span>
                        </button>
                    </span>
                </div>
                <table class="table table-striped table-hover table-responsive">
                    <thead>
                        <th class="center">STT</th>
                        <th>Họ tên</th>
                        <th>Email</th>
                        <th></th>
                        <th></th>
                    </thead>
                    <tbody>
                        <%
                            CustomerHandler handler = new CustomerHandler();
                            
                            int currentPage = 1;
                            int index = 1;
                            
                            if (request.getParameter("page") != null) {
                                currentPage = Integer.parseInt(request.getParameter("page"));
                            }
                            
                            ArrayList<Customer> customers = handler.limit(currentPage);
                            for (Customer customer : customers) {
                        %>
                            <tr>
                                <td class="center"><%= index++ %></td>
                                <td class="hidden"><%= customer.getID() %></td>
                                <td><%= customer.getName() %></td>
                                <td><%= customer.getEmail() %></td>
                                <td>
                                    <% if (customer.getEnable() == 1) { %>
                                        <button class="btn btn-default">Disabled</button>
                                    <% } else  { %>
                                        <button class="btn btn-success">Enabled</button>
                                    <% } %>
                                </td>
                                <td>
                                    <button class="btn btn-danger btn-remove">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </button>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/admin-customer.js"></script>
    </body>
</html>