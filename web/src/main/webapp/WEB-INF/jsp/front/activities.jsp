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
    .margin-banner {
        margin:  3px 0 3px 0;
    }
</style>
<div  style="margin: 5px;margin-top: 10px;">
<h1>Welcome to activity page. </h1>
<c:forEach var="activity" items="${activities}">
	<div>
	<label>Description: <c:out value="${activity.description}"></c:out></label>
	<label>feedback: <c:out value="${activity.feedback}"></c:out></label>
	<c:forEach varStatus="i" var="activityRound" items="${activity.activityRounds}">
		<h4>ActivityRound <c:out value="${i.index + 1}"></c:out><br/></h4>
		<label>Start Date: <c:out value="${activityRound.startDate}"></c:out></label>
		<label>End Date: <c:out value="${activityRound.endDate}"></c:out> </label>
		<label>Address: <c:out value="${activityRound.address}"></c:out></label> 
	</c:forEach>
	</div>
	<div class="margin-banner">---------------------------------------------------------</div>
</c:forEach>
</div>
