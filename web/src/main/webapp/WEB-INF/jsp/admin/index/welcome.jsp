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
<div>
<label>Add Activity</label>
</div>
<div>
<label>Add Activity Round FeedBack</label>
<form class="cmxform" id="interviewForm" method="post" action="/activity/activityround/1/updatefeedback">
	<div class="input_line">
		<label>behavihor</label>
		<select name="behavihor">
			<option value="5">Fancy</option>
			<option value="4">Good</option>
			<option value="3">Well</option>
			<option value="2">OK</option>
			<option value="1">Bad</option>
			<option value="0">No</option>
		</select>
    </div>
    <div class="input_line">
		<label>profession</label>
		<select name="profession">
			<option value="5">Fancy</option>
			<option value="4">Good</option>
			<option value="3">Well</option>
			<option value="2">OK</option>
			<option value="1">Bad</option>
			<option value="0">No</option>
		</select>
    </div>
    <div class="input_line">
		<label>language</label>
		<select name="language">
			<option value="5">Fancy</option>
			<option value="4">Good</option>
			<option value="3">Well</option>
			<option value="2">OK</option>
			<option value="1">Bad</option>
			<option value="0">No</option>
		</select>
    </div>
    <div class="input_line">
		<label>innovation</label>
		<select name="innovation">abcss
			<option value="5">Fancy</option>
			<option value="4">Good</option>
			<option value="3">Well</option>
			<option value="2">OK</option>
			<option value="1">Bad</option>
			<option value="0">No</option>
		</select>
    </div>
    <div class="input_line">
		<label>communication</label>
		<select name="communication">abcss
			<option value="5">Fancy</option>
			<option value="4">Good</option>
			<option value="3">Well</option>
			<option value="2">OK</option>
			<option value="1">Bad</option>
			<option value="0">No</option>
		</select>
    </div>
    <div class="input_line">
		<label>execution</label>
		<select name="execution">
			<option value="5">Fancy</option>
			<option value="4">Good</option>
			<option value="3">Well</option>
			<option value="2">OK</option>
			<option value="1">Bad</option>
			<option value="0">No</option>
		</select>
    </div>
    <div class="input_line">
		<label>teamwork</label>
		<select name="teamwork">abcss
			<option value="5">Fancy</option>
			<option value="4">Good</option>
			<option value="3">Well</option>
			<option value="2">OK</option>
			<option value="1">Bad</option>
			<option value="0">No</option>
		</select>
    </div>
    <div class="input_line">
		<label>management</label>
		<select name="management">abcss
			<option value="5">Fancy</option>
			<option value="4">Good</option>
			<option value="3">Well</option>
			<option value="2">OK</option>
			<option value="1">Bad</option>
			<option value="0">No</option>
		</select>
    </div>
    
     <div class="input_line">
		<label>result</label>
		<select name="result">
			<option value="2">通过</option>
			<option value="1">失败</option>
			<option value="0">进行中</option>
		</select>
    </div>
    
    <div class="submit_area">
                          <div class="mes_btn" onclick="submitInterview(this)">
                            <input class="submit mes_btn_submit" type="submit">
                          </div>
                          <div class="cancel_btn" onclick="cancelInterview()"></div>
                    </div>
</form>
</div>
<es:contentFooter/>
