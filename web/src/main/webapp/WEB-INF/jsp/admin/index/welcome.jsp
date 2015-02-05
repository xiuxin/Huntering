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
<form class="cmxform" id="interviewForm" method="get" action="">
                  <fieldset>
                    <div class="input_line">
                      <label for="startTime"><img src="/static/images/must_icon.png" class="must_blue">开始时间</label>
                      <input name="startTime" type="text" id="date_time" class="right_input date_time hasDatepicker" placeholder="2015/01/21 22:05">
                    </div>
                    <div class="input_line">
                      <label for="endTime"><img src="/static/images/must_icon.png" class="must_blue">结束时间</label>
                      <input name="endTime" type="text" id="date_time" class="right_input date_time hasDatepicker" placeholder="2015/01/21 22:05">
                    </div>
                    <div class="input_line">
                      <label for="companyName"><img src="/static/images/must_icon.png" class="must_blue">公司</label>
                      <input name="companyName" type="text" class="right_input" placeholder="公司名称">
                    </div>
                    <div class="input_line">
                      <label for="job"><img src="/static/images/must_icon.png" class="must_blue">岗位</label>
                      <input name="job" type="text" class="right_input" placeholder="岗位名称">
                    </div>
                    <div class="input_line">
                      <label for="position"><span class="not_must">地点</span></label>
                      <input name="position" type="text" class="right_input" placeholder="面试地点">
                    </div>
                    <div class="input_line">
                      <label for="candidate"><img src="/static/images/must_icon.png" class="must_blue">候选人</label>
                      <input name="candidateName" type="text" class="right_input1" placeholder="姓名">
                      <input name="candidatePhone" type="text" class="right_input2" placeholder="手机号">
                      <input name="candidateEmail" type="text" class="right_input3" placeholder="邮箱">
                    </div>
                    <div class="input_line">
                      <label for="candidate"><img src="/static/images/must_icon.png" class="must_blue">面试官</label>
                      <input name="interviewerName" type="text" class="right_input1" placeholder="姓名">
                      <input name="interviewerPhone" type="text" class="right_input2" placeholder="手机号">
                      <input name="interviewerEmail" type="text" class="right_input3" placeholder="邮箱">
                    </div>
                    <div class="input_line">
                      <label for="candidate"><span class="not_must">参与人</span></label>
                      <input name="candidateName1" type="text" class="right_input1" placeholder="姓名">
                      <input name="candidatePhone1" type="text" class="right_input2" placeholder="手机号">
                      <input name="candidateEmail1" type="text" class="right_input3" placeholder="邮箱">
                    </div>
                    <div class="input_line">
                      <label for="candidate"><span class="not_must">参与人</span></label>
                      <input name="candidateName2" type="text" class="right_input1" placeholder="姓名">
                      <input name="candidatePhone2" type="text" class="right_input2" placeholder="手机号">
                      <input name="candidateEmail2" type="text" class="right_input3" placeholder="邮箱">
                    </div>
                    <div class="input_line">
                      <label for="feedback"><img src="/static/images/must_icon.png" class="must_blue">反馈表</label>
                      <select class="right_selecter" name="feedback">
                        <option value="volvo">IT基本知识</option>
                        <option value="saab">.NET开发</option>
                        <option value="opel">Java</option>
                        <option value="audi">JS</option>
                      </select>
                    </div>
                    <div class="input_line">
                      <label for="feedback"><span class="not_must">备注</span></label>
                      <textarea class="backup_textarea">                      </textarea>
                    </div>
                    <div class="submit_area">
                          <div class="mes_btn" onclick="submitInterview(this)">
                            <input class="submit mes_btn_submit" type="submit">
                          </div>
                          <div class="cancel_btn" onclick="cancelInterview()"></div>
                    </div>
                  </fieldset>
                </form>
</div>
<div>
<label>Add Activity Round</label>
<form class="cmxform" id="interviewForm" method="get" action="/">
                  <fieldset>
                    <div class="input_line">
                      <label for="startTime"><img src="/static/images/must_icon.png" class="must_blue">开始时间</label>
                      <input name="startTime" type="text" id="date_time" class="right_input date_time hasDatepicker" placeholder="2015/01/21 22:05">
                    </div>
                    <div class="input_line">
                      <label for="endTime"><img src="/static/images/must_icon.png" class="must_blue">结束时间</label>
                      <input name="endTime" type="text" id="date_time" class="right_input date_time hasDatepicker" placeholder="2015/01/21 22:05">
                    </div>
                    <div class="input_line">
                      <label for="companyName"><img src="/static/images/must_icon.png" class="must_blue">公司</label>
                      <input name="companyName" type="text" class="right_input" placeholder="公司名称">
                    </div>
                    <div class="input_line">
                      <label for="job"><img src="/static/images/must_icon.png" class="must_blue">岗位</label>
                      <input name="job" type="text" class="right_input" placeholder="岗位名称">
                    </div>
                    <div class="input_line">
                      <label for="position"><span class="not_must">地点</span></label>
                      <input name="position" type="text" class="right_input" placeholder="面试地点">
                    </div>
                    <div class="input_line">
                      <label for="candidate"><img src="/static/images/must_icon.png" class="must_blue">候选人</label>
                      <input name="candidateName" type="text" class="right_input1" placeholder="姓名">
                      <input name="candidatePhone" type="text" class="right_input2" placeholder="手机号">
                      <input name="candidateEmail" type="text" class="right_input3" placeholder="邮箱">
                    </div>
                    <div class="input_line">
                      <label for="candidate"><img src="/static/images/must_icon.png" class="must_blue">面试官</label>
                      <input name="interviewerName" type="text" class="right_input1" placeholder="姓名">
                      <input name="interviewerPhone" type="text" class="right_input2" placeholder="手机号">
                      <input name="interviewerEmail" type="text" class="right_input3" placeholder="邮箱">
                    </div>
                    <div class="input_line">
                      <label for="candidate"><span class="not_must">参与人</span></label>
                      <input name="candidateName1" type="text" class="right_input1" placeholder="姓名">
                      <input name="candidatePhone1" type="text" class="right_input2" placeholder="手机号">
                      <input name="candidateEmail1" type="text" class="right_input3" placeholder="邮箱">
                    </div>
                    <div class="input_line">
                      <label for="candidate"><span class="not_must">参与人</span></label>
                      <input name="candidateName2" type="text" class="right_input1" placeholder="姓名">
                      <input name="candidatePhone2" type="text" class="right_input2" placeholder="手机号">
                      <input name="candidateEmail2" type="text" class="right_input3" placeholder="邮箱">
                    </div>
                    <div class="input_line">
                      <label for="feedback"><img src="/static/images/must_icon.png" class="must_blue">反馈表</label>
                      <select class="right_selecter" name="feedback">
                        <option value="volvo">IT基本知识</option>
                        <option value="saab">.NET开发</option>
                        <option value="opel">Java</option>
                        <option value="audi">JS</option>
                      </select>
                    </div>
                    <div class="input_line">
                      <label for="feedback"><span class="not_must">备注</span></label>
                      <textarea class="backup_textarea">                      </textarea>
                    </div>
                    <div class="submit_area">
                          <div class="mes_btn" onclick="submitInterview(this)">
                            <input class="submit mes_btn_submit" type="submit">
                          </div>
                          <div class="cancel_btn" onclick="cancelInterview()"></div>
                    </div>
                  </fieldset>
                </form>
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
