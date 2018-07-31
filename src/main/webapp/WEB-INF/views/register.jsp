<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="col-md-12">
    <div class="offset-md-4 col-md-4">
        <div class="login-wrapper">
            <div class="box">
                <div class="content-wrap">
                    <h3 class="text-center"><spring:message code="label.register"/></h3>
                    <form:form method="POST" action="/register" modelAttribute="registerModel">
                        <div class="form-group">
                            <form:errors path="*" cssClass="error" style="color:red"/>
                        </div>
                        <div class="form-group">
                            <label class="control-label"><spring:message code="label.name"/></label>
                            <form:input path="name" cssClass="form-control" title="name" required="true"/>

                            <label class="control-label"><spring:message code="label.password"/></label>
                            <form:password path="password" cssClass="form-control" title="Password" required="true"/>

                            <label class="control-label"><spring:message code="label.password_confirmation"/></label>
                            <form:password path="confirmPassword" cssClass="form-control" title="Password Confirmation"
                                           required="true"/>
                        </div>
                        <div class="form-group">
							<span class="input-group-btn">
								<input type="submit" class="btn btn-primary btn-block" value="<spring:message code="label.register"/>">
							</span>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>