<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>招聘-反馈</title>
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/ui/jquery-ui.min.css"/>

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
<div class="feedback_div">
	<form class="cmxform" id="feedBackForm" method="post" action="${ctx}/activity/updatefeedback?uuid=${uuid}">
		<div>
			<textarea id="feedback_detail" name="detail" rows="5" cols="15" value="${feedBack.detail}"></textarea>
		</div>
	    <div class="clear"></div>
	    <div class="feedback_result">
			<label>本轮面试结果</label>
			<select id="result" name="result">
				<option value="2">通过</option>
				<option value="1">失败</option>
				<option value="0">待定</option>
			</select>
	    </div>
	    <div class="form-group">
  						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  						<button type="button" id="saveBackFeed" data-loading-text="Processing.." class="btn btn-primary">保存</button>
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
<script type="text/javascript" src="${ctx}/static/js/ui/jquery-ui-timepicker-addon.js"></script>

</body>
