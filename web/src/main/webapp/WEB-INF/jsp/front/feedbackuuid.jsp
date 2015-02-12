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

<div class="feedback_div">
	<form class="cmxform" id="feedBackForm" method="post">
		<div class="input_line">
			<label>言行举止</label>
			<select id="behavihor" name="behavihor">
				<option value="5">5.杰出水平</option>
				<option value="4">4.优秀水平</option>
				<option value="3">3.平均水平</option>
				<option value="2">2.低于平均</option>
				<option value="1">1.能力稍弱</option>
				<option value="0">0.无此能力</option>
			</select>
	    </div>
	    <div class="input_line">
			<label>专业能力</label>
			<select id="profession" name="profession">
				<option value="5">5.杰出水平</option>
				<option value="4">4.优秀水平</option>
				<option value="3">3.平均水平</option>
				<option value="2">2.低于平均</option>
				<option value="1">1.能力稍弱</option>
				<option value="0">0.无此能力</option>
			</select>
	    </div>
	    <div class="input_line">
			<label>语言能力</label>
			<select id="language" name="language">
				<option value="5">5.杰出水平</option>
				<option value="4">4.优秀水平</option>
				<option value="3">3.平均水平</option>
				<option value="2">2.低于平均</option>
				<option value="1">1.能力稍弱</option>
				<option value="0">0.无此能力</option>
			</select>
	    </div>
	    <div class="input_line">
			<label>创新精神</label>
			<select id="innovation" name="innovation">
				<option value="5">5.杰出水平</option>
				<option value="4">4.优秀水平</option>
				<option value="3">3.平均水平</option>
				<option value="2">2.低于平均</option>
				<option value="1">1.能力稍弱</option>
				<option value="0">0.无此能力</option>
			</select>
	    </div>
	    <div class="input_line">
			<label>沟通技巧</label>
			<select id="communication" name="communication">
				<option value="5">5.杰出水平</option>
				<option value="4">4.优秀水平</option>
				<option value="3">3.平均水平</option>
				<option value="2">2.低于平均</option>
				<option value="1">1.能力稍弱</option>
				<option value="0">0.无此能力</option>
			</select>
	    </div>
	    <div class="input_line">
			<label>执行能力</label>
			<select id="execution" name="execution">
				<option value="5">5.杰出水平</option>
				<option value="4">4.优秀水平</option>
				<option value="3">3.平均水平</option>
				<option value="2">2.低于平均</option>
				<option value="1">1.能力稍弱</option>
				<option value="0">0.无此能力</option>
			</select>
	    </div>
	    <div class="input_line">
			<label>团队合作</label>
			<select id="teamwork" name="teamwork">
				<option value="5">5.杰出水平</option>
				<option value="4">4.优秀水平</option>
				<option value="3">3.平均水平</option>
				<option value="2">2.低于平均</option>
				<option value="1">1.能力稍弱</option>
				<option value="0">0.无此能力</option>
			</select>
	    </div>
	    <div class="input_line">
			<label>管理能力</label>
			<select id="management" name="management">
				<option value="5">5.杰出水平</option>
				<option value="4">4.优秀水平</option>
				<option value="3">3.平均水平</option>
				<option value="2">2.低于平均</option>
				<option value="1">1.能力稍弱</option>
				<option value="0">0.无此能力</option>
			</select>
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

<script type="text/javascript">
$(document).ready(function(){
	$("#saveBackFeed").click(function(){
		$("#feedBackForm").submit();
	});	
});
</script>

</body>
