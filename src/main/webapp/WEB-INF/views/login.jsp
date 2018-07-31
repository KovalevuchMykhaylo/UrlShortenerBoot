<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:if test="${param.fail}">
    <div class="col-sm-12 col-xs-12 text-center">
        <h2>Fail to authorize</h2>
    </div>
</c:if>
<div class="col-md-12">
    <div class="row">
        <div class="offset-md-4 col-md-4">
            <form:form class="form-horizontal form-maggin" action="${pageContext.request.contextPath}/login"
                       method="POST">
                <div class="form-group">
                    <label for="login" class="col-sm-12 control-label"><spring:message code="label.login"/></label>
                    <div class="col-sm-12">
                        <input class="form-control" name="login" id="login" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-12 control-label"><spring:message
                            code="label.password"/></label>
                    <div class="col-sm-12">
                        <input type="password" class="form-control" name="password" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12">
                        <div class="checkbox">
                            <label> <input name="rememberMe" type="checkbox">
                                <spring:message code="label.remember_me"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn-primary"><spring:message code="label.sing_in"/></button>
                    </div>
                </div>
            </form:form>
            <div class="already">
                <p><spring:message code="label.not_register_yet"/>
                    <a href="${pageContext.request.contextPath}/register"><spring:message code="label.register"/></a>
                </p>
            </div>
        </div>
    </div>
</div>