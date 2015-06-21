<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DataAccessLayer.TaskHandler"%>
<%@page import="DataTransferObject.Task"%>
<%@page import="java.util.ArrayList"%>
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
        <link rel="stylesheet" href="/public/css/bootstrap-combobox.css"/>
        <link rel="stylesheet" href="/public/css/bootstrap-theme.css"/>
        <link rel="stylesheet" href="/public/css/employee-app.css"/>
        <link rel="stylesheet" href="/public/css/employee-task.css"/>
    </head>
    <body>

        <%
            if (!(Auth.authorizePilot(session) == true || Auth.authorizeStewardess(session) == true)) {
                response.sendRedirect("/login.jsp");
                return;
            }
            
            String month = "";
            String year = "";
            
            if (request.getParameter("month") != null) {
                month = request.getParameter("month");
            }
            
            if (request.getParameter("year") != null) {
                month = request.getParameter("year");
            }
        %>
        
        <header>
            <h1 class="navbar-brand">
                <a href="/employee/index.jsp">BlueSky</a>
            </h1>
            <ul class="nav-user">
                <li><%= session.getAttribute("userName") %></li>
                <li>
                    <a href="/admin/logouthandler">Đăng xuất</a>
                </li>
            </ul>
        </header>
        
        <section class="container row">
            <aside class="col-md-3 nav-menu">
                <h3 class="nav-header">
                    <%= session.getAttribute("userPermission") %>
                </h3>
                <ul class="nav nav-pills nav-stacked">
                    <li role="presentation"><a href="/employee/info.jsp">Cập nhật thông tin</a></li>
                    <li role="presentation"><a href="/employee/task.jsp">Phân công công việc</a></li>
                </ul>
            </aside>
            <article class="col-md-9">
                <%
                    TaskHandler taskHandler = new TaskHandler();

                    int currentPage = 1;
                    int index = 1;
                    int totalPage = 0;

                    if (request.getParameter("page") != null) {
                        currentPage = Integer.parseInt(request.getParameter("page"));
                    }

                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:MM");
                    ArrayList<Task> tasks = null;

                    if ("".equals(month) && "".equals(year)) {
                        totalPage = taskHandler.totalPageForStewardess(session.getAttribute("userID").toString());
                        if (Auth.authorizeStewardess(session)) {
                            tasks = taskHandler.limitForStewardess(session.getAttribute("userID").toString(), currentPage);
                        }
                    } else {
//                        totalPage = taskHandler.totalPageWithTime(session.getAttribute("userID").toString(), month, year);
//                        if (Auth.authorizeStewardess(session)) {
//                            tasks = taskHandler.limitForStewardessWithTime(session.getAttribute("userID").toString(), currentPage, month, year);
//                        }
//
//                        if (totalPage == 0) {
//                            currentPage = 0;
//                        }
                    }
                %>
                <h2 class="title">Phân công công việc</h2>
                <form method="get" action="/employee/task.jsp">
                    <div class="input-navigator">
                        <select class="combobox" name="month">
                            <% for (int _month = 1; _month < 13; _month++) { %>
                                <option value="<%= _month %>">Tháng <%= _month %></option>
                            <% } %>
                        </select>
                        <input type="text" name="year" class="form-control txt-year" autocomplete="off" spellcheck="false" placeholder="Năm" />
                        <button type="submit" class="btn btn-primary btn-search">Tìm kiếm</button>
                        <button class="btn btn-default btn-clear" id="btn-clear">
                            <span class="glyphicon glyphicon-repeat"></span>
                        </button>
                    </div>
                </form>
                <table class="table table-striped table-hover table-responsive">
                    <thead>
                        <th class="center">STT</th>
                        <th>Mã chuyến</th>
                        <th>Tên chuyến</th>
                        <th>Thời gian đi</th>
                        <% if (Auth.authorizeStewardess(session)) { %>
                            <th>Khoang</th>
                        <% } %>
                        <th></th>
                    </thead>
                    <tbody>
                        <% for (Task task : tasks) { %>
                            <tr>
                                <td><%= index++ %></td>
                                <td><%= task.getFlightID() %></td>
                                <td><%= task.getFlightName() %></td>
                                <td><%= formatter.format(task.getStartTime()) %></td>
                                <% if (Auth.authorizeStewardess(session)) { %>
                                    <td><%= task.getCabin() %></td>
                                <% } %>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
                <nav>
                    <ul class="pager">
                        <li><a href="javascript:void(0)" id="btn-previous">Previous</a></li>
                        <li>
                            <span id="current-page"><%= currentPage %></span> / <span id="total-page"><%= totalPage %></span>
                        </li>
                        <li><a href="javascript:void(0)" id="btn-next">Next</a></li>
                    </ul>
                </nav>
            </article>
        </section>
        
        <script src="/public/js/jquery.js"></script>
        <script src="/public/js/bootstrap.js"></script>
        <script src="/public/js/bootstrap-combobox.js"></script>
        <script src="/public/js/employee-task.js"></script>
    </body>
</html>