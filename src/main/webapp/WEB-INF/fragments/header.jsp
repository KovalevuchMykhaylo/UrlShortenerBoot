<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="navbar-header navbar form-inline">
    <div class="dropdown">
        <button class="dropbtn"><spring:message code="label.language"/></button>
        <div class="dropdown-content">
            <a class="dropdown-item" href="?lang=en">English</a>
            <a class="dropdown-item" href="?lang=uk">Українська</a>
        </div>
    </div>
    <a href="${pageContext.request.contextPath}/"><spring:message code="label.index"/></a>
    <sec:authorize access="!isAuthenticated()">
        <a href="${pageContext.request.contextPath}/register"><spring:message code="label.register"/></a>
        <a href="${pageContext.request.contextPath}/login"><spring:message code="label.login"/></a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a href="${pageContext.request.contextPath}/user/shortUrl"><spring:message code="label.short_url"/></a>
        <form:form class="navbar-form navbar-right" action="/logout" method="POST">
            <button type="submit" class="btn btn-danger"><spring:message code="label.logout"/></button>
        </form:form>
    </sec:authorize>
</div>