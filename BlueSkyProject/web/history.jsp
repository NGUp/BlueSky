<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DataTransferObject.Bill"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataAccessLayer.BillHandler"%>
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
        <link rel="stylesheet" href="/public/css/history.css"/>
    </head>
    <body>
        <%
            if (session.getAttribute("userID") == null) {
                response.sendRedirect("/login.jsp");
                return;
            }
        %>
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
                            <li role="presentation"><a href="/info.jsp">Cập nhật thông tin</a></li>
                            <li role="presentation"><a href="/history.jsp">Lịch sử mua vé</a></li>
                            <li role="presentation"><a href="/app/logouthandler">Thoát</a></li>
                        </ul>
                    </aside>
                </div>
                <div class="col-md-9">
                    <section>
                        <%
                            String userID = session.getAttribute("userID").toString();
                            
                            int index = 1;
                            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:MM");
                            BillHandler billHandler = new BillHandler();
                            ArrayList<Bill> bills = billHandler.getByCustomer(userID);
                        %>
                        <h2 class="title">Lịch sử mua vé</h2>
                        <table class="table table-striped table-hover table-responsive">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Ngày</th>
                                    <th>Só tiền</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Bill bill : bills) { %>
                                <tr>
                                    <td><%= index++ %></td>
                                    <td><%= formatter.format(bill.getTime()) %></td>
                                    <td><%= bill.getTotal() %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
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
    </body>
</html>
