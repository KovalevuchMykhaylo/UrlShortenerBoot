<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/resources/img/spike.png" type="image/png">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="col-md-12 col-xs-12 text-center">
            <h1 style="color: red"><spring:message code="label.bad_short_url_request"/>)))</h1>
            <video width="pixels" controls autoplay>
                <source src="${pageContext.request.contextPath}/resources/video/404.mp4" type="video/mp4">
            </video>
        </div>
        <div class="col-md-12 col-xs-12 text-center">
            <a class="btn btn-danger" href="${pageContext.request.contextPath}/" style="width: 30vh; margin: 10px">???</a>
        </div>
    </body>
    <style>
        body{
            background: #000000;
        }
    </style>
</html>
