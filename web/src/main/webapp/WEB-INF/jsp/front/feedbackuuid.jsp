<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>招聘-反馈</title>
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/ui/jquery-ui.min.css"/>
<style>
.input_radio{
margin: -2px 0 0 20px !important;
}
</style>
<body>
<!--top bar start-->
<div class="top_bar">
        <div class="container">
               <div class="com_logo_div">
                   <img src="${ctx}/static/images/com_logo.png" />
               </div>
               <div class="com_name_div">
                   Huntering 
               </div>
        </div>
        
</div>
<!--top bar end-->

<div class="outer_login_middle">
<div class="container main_middle">
<c:choose>
<c:when test="${not empty message}">
	<div style="color:red;padding: 40px 0 140px 20px;font-size: 30;"><c:out value="${message}"></c:out></div>
</c:when>
<c:otherwise>
<div class="container feedback_div">
	<form class="cmxform" id="feedBackForm" method="post" action="${ctx}/activity/updatefeedback?uuid=${uuid}">
	<div class="clear"></div>
	<div>
		<span class="mes_line_one_account"><c:out value="${feedBack.activityRound.activity.job.title}"></c:out></span>
        <img src="${ctx}/static/images/user_line.png" class="user_line"/>
        <span class="mes_line_one_account"><c:out value="${feedBack.activityRound.activity.people.fullName}"></c:out></span>
        <img src="${ctx}/static/images/user_line.png" class="user_line"/>
        <span class="mes_line_one_account"><c:out value="${feedBack.activityRound.startDate}"></c:out></span>
	</div>
	    <div class="clear"></div>
	    <div>
	   	<c:choose>
	   		<c:when test="${feedBack.result == 2}">
	   			<input type="radio" name="result" value="2" checked/>通过
	    		<input type="radio" name="result" value="1" class="input_radio"/>待定
	    		<input type="radio" name="result" value="0" class="input_radio"/>失败
	   		</c:when>
	   		<c:when test="${feedBack.result == 0}">
	   			<input type="radio" name="result" value="2"/>通过
	    		<input type="radio" name="result" value="1" class="input_radio"/>待定
	    		<input type="radio" name="result" value="0" class="input_radio" checked/>失败
	   		</c:when>
	   		<c:otherwise>
	   			<input type="radio" name="result" value="2"/>通过
	    		<input type="radio" name="result" value="1" class="input_radio" checked/>待定
	    		<input type="radio" name="result" value="0" class="input_radio"/>失败
	   		</c:otherwise>
	   	</c:choose>
	    </div>
	    <div class="clear"></div>
		<div class="col-xs-3">
			<textarea id="feedback_detail" name="detail" rows="5" cols="15">${feedBack.detail}</textarea>
	   	</div>
		<div>
			<span class="feedback_desb" style="margin: 0 10px 5px 0;cursor: pointer;">英语好的</span>
			<span class="feedback_desb" style="margin: 0 10px 5px 0;cursor: pointer;">英语差的</span>
			<span class="feedback_desb" style="margin: 0 10px 5px 0;cursor: pointer;">沟通好的</span>
			<span class="feedback_desb" style="margin: 0 10px 5px 0;cursor: pointer;">沟通差的</span>
		</div>
	    <div class="form-group">
  			<button type="button" id="saveBackFeed" data-loading-text="Processing.." class="btn btn-primary" onclick="submit()">保存</button>
  		</div>
	</form>
</div>

</c:otherwise>
</c:choose>

</div>
</div>
<!--login middle end-->
<div class="blank_area"></div>

 <!--footer start-->
    <div class="above_footer_line"></div>
    <div class="container footer">
      <footer>
            <div class="copy_right_first">
                <a href="#" style="color: #0073c6;">关于我们</a><img src="${ctx}/static/images/footer_line.png" class="footer_line" />
                <a href="#" style="color: #0073c6;">加入我们</a><img src="${ctx}/static/images/footer_line.png" class="footer_line" />
                <a href="#" style="color: #0073c6;">联系我们</a><img src="${ctx}/static/images/footer_line.png" class="footer_line" />
                <a href="#" style="color: #0073c6;">邀请我们</a>
            </div>
            <div class="copy_right_second">
                <span>Copyright&nbsp;@&nbsp;aaaahuntering.com&nbsp;2015-2025</span>
            </div>
      </footer>    
    </div>
 <!--footer end-->

<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/static/js/ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/feedback.js"></script>

<script type="text/javascript">
	function submit() {
	}
</script>
</body>
