<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="col-md-12 col-xs-12">
    <div class="row">
        <div class="col-md-2 col-xs-2 text-center">
            <spring:message var="titleName" code="label.size_tag_title"/>
            <custom:size posibleSizes="1,2,5,10" size="${page.size}" title="${titleName}"/>
            <div class="dropdown">
                <button class="btn btn-primary">
                    <spring:message code="label.sort_tag"/>
                </button>
                <div class="dropdown-content">
                    <custom:sort innerHtml="Clicks asc" paramValue="shortUrlClicks"/>
                    <custom:sort innerHtml="Clicks desc" paramValue="shortUrlClicks,desc"/>
                </div>
            </div>
        </div>
        <div class="col-md-8 col-xs-8">
            <form:form class="form-horizontal" action="${pageContext.request.contextPath}/user/shortUrl" method="POST"
                       modelAttribute="createCustomUrlDto">
                <div class="form-group">
                    <label for="longUrl" class="col-sm-10 col-sm-offset-2 control-label errorsStyle"><form:errors
                            path="longUrl"/></label>
                </div>
                <div class="form-group">
                    <label for="longUrl" class="col-sm-2 control-label"><spring:message code="label.long_url"/></label>
                    <spring:message code="label.url_length"/> <span id="inputCharsLength">0</span>/1024
                    <div class="col-sm-10">
                        <form:input class="form-control" path="longUrl" required="true" maxlength="1024"
                                    placeholder="Your long url" id="urlinput"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary"><spring:message code="label.create"/></button>
                        <a href="${pageContext.request.contextPath}/user/shortUrl/cancel"
                           class="btn btn-primary"><spring:message code="label.cancel"/></a>
                    </div>
                </div>
            </form:form>
        </div>
        <div class="col-md-2 col-xs-2">
            <canvas class="watch" id="canvas" height="150">
            </canvas>
        </div>
    </div>
</div>
<div class="col-md-12 col-xs-12">
    <div class="row">
        <div class="col-md-4 col-xs-4"><h3><spring:message code="label.long_url"/></h3></div>
        <div class="col-md-2 col-xs-2"><h3><spring:message code="label.short_url"/></h3></div>
        <div class="col-md-2 col-xs-2"><h3><spring:message code="label.count"/></h3></div>
        <div class="col-md-2 col-xs-2"><h3><spring:message code="label.date_creation"/></h3></div>
        <div class="col-md-2 col-xs-2"><h3><spring:message code="label.delete"/></h3></div>
    </div>
</div>
<c:forEach items="${page.content}" var="customShortUrl">
    <div class="row listOfUrls">
        <p </p>
        <div class="col-md-4 col-xs-4"><a target="_blank" rel="noopener noreferrer"
                                          href="${customShortUrl.longUrl}" title="${customShortUrl.longUrl}"><spring:message code="label.long_url"/> </a>
        </div>
        <div class="col-md-2 col-xs-2">${customShortUrl.shortUrl}</div>
        <div class="col-md-2 col-xs-2">${customShortUrl.shortUrlClicks}</div>
        <div class="col-md-2 col-xs-2">${customShortUrl.dateCreation}</div>
        <div class="col-md-2 col-xs-2"><a class="btn btn-danger"
                                          href="/user/shortUrl/delete/${customShortUrl.id}<custom:allParams/>"><spring:message
                code="label.delete"/></a>
        </div>
    </div>
</c:forEach>
<c:if test="${page.totalPages != 0}">
    <div class="col-md-12 col-xs-12 myFooter">
        <custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
    </div>
</c:if>
<script>
    $('label').each(function () {
        if (!$(this).html()) $(this).parent('div').hide();
    });
</script>
<script>
    var canvas = document.getElementById("canvas");
    var ctx = canvas.getContext("2d");
    var radius = canvas.height / 2;
    ctx.canvas.width  = canvas.height;
    ctx.translate(radius, radius);
    radius = radius * 0.90;
    setInterval(drawClock, 1000);

    function drawClock() {
        drawFace(ctx, radius);
        drawNumbers(ctx, radius);
        drawTime(ctx, radius);
    }

    function drawFace(ctx, radius) {
        var grad;
        ctx.beginPath();
        ctx.arc(0, 0, radius, 0, 2 * Math.PI);
        ctx.fillStyle = 'white';
        ctx.fill();
        grad = ctx.createRadialGradient(0, 0, radius * 0.95, 0, 0, radius * 1.05);
        grad.addColorStop(0, '#333');
        grad.addColorStop(0.5, 'white');
        grad.addColorStop(1, '#333');
        ctx.strokeStyle = grad;
        ctx.lineWidth = radius * 0.1;
        ctx.stroke();
        ctx.beginPath();
        ctx.arc(0, 0, radius * 0.1, 0, 2 * Math.PI);
        ctx.fillStyle = '#333';
        ctx.fill();
    }

    function drawNumbers(ctx, radius) {
        var ang;
        var num;
        ctx.font = radius * 0.15 + "px arial";
        ctx.textBaseline = "middle";
        ctx.textAlign = "center";
        for (num = 1; num < 13; num++) {
            ang = num * Math.PI / 6;
            ctx.rotate(ang);
            ctx.translate(0, -radius * 0.85);
            ctx.rotate(-ang);
            ctx.fillText(num.toString(), 0, 0);
            ctx.rotate(ang);
            ctx.translate(0, radius * 0.85);
            ctx.rotate(-ang);
        }
    }

    function drawTime(ctx, radius) {
        var now = new Date();
        var hour = now.getHours();
        var minute = now.getMinutes();
        var second = now.getSeconds();
        //hour
        hour = hour % 12;
        hour = (hour * Math.PI / 6) +
            (minute * Math.PI / (6 * 60)) +
            (second * Math.PI / (360 * 60));
        drawHand(ctx, hour, radius * 0.5, radius * 0.07);
        //minute
        minute = (minute * Math.PI / 30) + (second * Math.PI / (30 * 60));
        drawHand(ctx, minute, radius * 0.8, radius * 0.07);
        // second
        second = (second * Math.PI / 30);
        drawHand(ctx, second, radius * 0.9, radius * 0.02);
    }

    function drawHand(ctx, pos, length, width) {
        ctx.beginPath();
        ctx.lineWidth = width;
        ctx.lineCap = "round";
        ctx.moveTo(0, 0);
        ctx.rotate(pos);
        ctx.lineTo(0, -length);
        ctx.stroke();
        ctx.rotate(-pos);
    }
</script>
