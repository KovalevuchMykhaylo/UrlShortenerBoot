<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:getAsString name="title"/></title>
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/resources/img/spike.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/MyScript.js"></script>

    <!--   <sec:csrfMetaTags/> -->
    <script type="text/javascript" language="javascript">
        var client = rest.chain(csrf, {
            token: $("meta[name='_csrf']").attr("content"),
            name: $("meta[name='_csrf_header']").attr("content")
        });
    </script>


</head>
<body class="container-fluid">

<tiles:insertAttribute name="header"/>

<div class="bodyContent">
    <tiles:insertAttribute name="body"/>
</div>

</body>
</html>