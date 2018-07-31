<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
    <form:form class="form-horizontal" action="${pageContext.request.contextPath}/" method="POST"
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
            </div>
        </div>
    </form:form>
</div>
<div class="col-md-12 col-xs-12">
    <c:if test="${!empty shortUrl}">
        <h1><spring:message code="label.your_short_url"/>: ${shortUrl}</h1>
    </c:if>
</div>
<div class="col-md-12 col-xs-12">
    <spring:message code="label.index_text"/>
</div>
<script>
    $('label').each(function () {
        if (!$(this).html()) $(this).parent('div').hide();
    });
</script>