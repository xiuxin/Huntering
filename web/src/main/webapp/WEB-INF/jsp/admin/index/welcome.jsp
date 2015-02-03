<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<es:contentHeader/>
<style>
    legend {
        cursor: pointer;
    }
    .fc-button-add {
        margin-right: 10px!important;
    }

    #loading {
        position: absolute;
        top: 5px;
        right: 5px;
    }

    .ui-dialog {
        overflow: visible!important;
    }
    .ui-dialog-content {
        overflow: visible!important;
    }

    #calendar {
        width: 750px;
        margin: 0 auto;
    }
</style>
<div  style="margin: 5px;margin-top: 10px;">
<h1>Welcome. </h1>
<h2>Your login emails are: </h2>
<c:forEach var="email" items="${user.emails}">
	<li><c:out value="${email.email}"></c:out></li>
</c:forEach>
<a href="${ctx}/upload/ajax/create/${user.id}">简历上传</a>
</div>
<div>
Home page: <a href="${ctx}/home">here</a>
</div>
<es:contentFooter/>
