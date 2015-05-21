<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="/BlueSky/public/css/foundation.css"/>
        <link href="/public/img/favicon.png" rel="shortcut icon">
    </head>
    <body>
        <%= request.getAttribute("titleAttribute") %>
                
        <form method="POST" action="/BlueSkyWeb/admin/login">
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
