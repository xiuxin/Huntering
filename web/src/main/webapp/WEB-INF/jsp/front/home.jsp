<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>招聘-首页</title>
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/jquery-ui.min.css"/>

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
               <div class="search_input_div">
                 <input type="email" class="search_input" placeholder="请在此输入搜索内容" id="emailHeader" onBlur="emailHeaderBlur()" />
               </div>
               <div class="search_btn_div" onclick="loginHeader()">
               </div>
               <div class="user_info_div">
                  <img src="${ctx}/static/images/user_icon.png" class="user_icon"/>
               </div>
               <div class="account_info">
                    <div class="account_header">账户设置</div>
                    <div class="account_detail">
                        <div class="account_detail_line">
                          <span class="account_left">
                          	<c:choose>
                          		<c:when test="${not empty user.userName}">
                          			<c:out value="${user.userName}"/>
                          		</c:when>
                          		<c:otherwise><c:out value="${username}"/></c:otherwise>
                          	</c:choose>
                          </span>
                          <a href="${ctx}/logout"><span class="account_right">登出</span></a>
                        </div>
                        <div class="account_detail_line">
                          <span class="account_left">上传邮箱</span>
                          <span class="account_middle">test.upload@hunter.com</span>
                        </div>
                        <div class="account_detail_line">
                          <span class="account_left">登陆邮箱</span>
                          <span class="account_middle"><c:out value="${username}"/> </span>
                          <span class="account_right">管理</span>
                        </div>
                        <div class="account_detail_line">
                          <span class="account_left">手机</span>
                          <span class="account_middle"><c:out value="${user.mdn}"/></span>
                          <span class="account_right_phone">已验证</span>
                        </div>
                        <div class="account_detail_line">
                          <span class="account_left">所属公司</span>
                          <span class="account_middle"><c:out value="${user.companyName}"/></span>
                          <span class="account_right">变更</span>
                        </div>
                        <div class="login_info">
                            <img src="${ctx}/static/images/login_icon.png" class="logn_info_img"/>
                            <span class="logn_info_text">Linkedin登陆</span>
                        </div>
                    </div>
                </div>

        </div>
        
</div>
<!--top bar end-->

<!--login middle start-->
<div class="outer_login_middle">
<div class="container main_middle">
      <div class="icon_div">
          <div class="above_more_icon">
            
          </div>
          <div>
              <img src="${ctx}/static/images/more_icon.png"/>
          </div>
      </div>

      <div class="icons_div">
          <div style="height: 76px;" class="icon_first icon_all" style="cursor: pointer;">
              <img src="${ctx}/static/images/add_assume.png" />
              <input type="file" name="file" class="upload_file">
          </div>
           <div class="icon_one icon_all">
          </div>
           <div class="icon_two icon_all">
          </div>
      </div>

<c:if test="${not empty messages}">
<c:forEach var="message" items="${messages}">
	<c:choose>
		<c:when test="${message.type == 'RESUME'}">
			<div class="single_resume">
            <div class="mes_line_one">
                  <img src="${ctx}/static/images/system_img.png" class="user_img"/>
                  <span class="mes_line_one_account">系统账号</span>
                  <img src="${ctx}/static/images/user_line.png" class="user_line"/>
                  <span class="mes_line_one_type">档案更新</span>
                  <%-- <img src="${ctx}/static/images/user_line.png" class="user_line"/>
                  <span class="mes_line_one_serial">Test</span> --%>
                  <span class="mes_date">${message.updateDate}</span>
            </div>
			<c:choose>
				<c:when test="${not empty message.people.fullName}">
	            <div class="clear"></div>
	            <div class="mes_line_two">
	                  <p>您导入了<c:out value="${message.people.fullName}"></c:out>的简历，您可以为他创建面试。</p>
	            </div>
	            <div class="mes_line_three">
	                  <div class="start_interview_div">
	                      <div class="start_interview" data-personid="${message.people.id}" onclick="statInterview1(this)">安排面试</div>
	                      <div class="interview_white_trangle">
	                          <img src="${ctx}/static/images/white_trangle.png" id="interview_white_trangle_img_${message.people.id}" class="interview_white_trangle_img"/>
	                     </div>
	                  </div>
	                  <div class="remark_div">
	                      <div class="start_remark" data-personid="${message.people.id}" onclick="statRemark1(this)">备注</div>
	                      <div class="remark_white_trangle">
	                          <img src="${ctx}/static/images/white_trangle.png" id="remark_white_trangle_img_${message.people.id}" class="remark_white_trangle_img"/>
	                     </div>
	                  </div>
	                  
	            </div>
				</c:when>
				<c:otherwise>
					<div class="clear"></div>
		            <div class="mes_line_two">
		                  <p>您已成功导入了一份简历，系统正在自动识别并会在第一时间通知到您。</p>
		            </div>
				</c:otherwise>
			</c:choose>
      		</div>
      		<c:if test="${not empty message.people.fullName}">
      			<div class="interview_dialog" id="interview_dialog_${message.people.id}">
                  <form class="cmxform" id="interviewForm" method="post" action="${ctx}/activity/add/${message.people.id}">
                  <fieldset>
                    <div class="input_line">
                      <label for="startTime"><img src="${ctx}/static/images/must_icon.png" class="must_blue" />开始时间</label>
                      <input name="startTime" type="text" id="date_time" class="right_input date_time" placeholder="2015/01/21 22:05">
                    </div>
                    <div class="input_line">
                      <label for="endTime"><img src="${ctx}/static/images/must_icon.png" class="must_blue" />结束时间</label>
                      <input name="endTime" type="text" id="date_time" class="right_input date_time" placeholder="2015/01/21 22:05">
                    </div>
                    <div class="input_line">
                      <label for="companyName"><img src="${ctx}/static/images/must_icon.png" class="must_blue" />公司</label>
                      <input name="companyName" type="text" class="right_input" placeholder="公司名称">
                    </div>
                    <div class="input_line">
                      <label for="jobTitle"><img src="${ctx}/static/images/must_icon.png" class="must_blue" />岗位</label>
                      <input name="jobTitle" type="text" class="right_input" placeholder="岗位名称">
                    </div>
                    <div class="input_line">
                      <label for="address"><span class="not_must">地点</span></label>
                      <input name="address" type="text" class="right_input" placeholder="面试地点">
                    </div>
                    <div class="input_line">
                      <label for="candidate"><img src="${ctx}/static/images/must_icon.png" class="must_blue" />候选人</label>
                      <input name="candidateName" type="text" class="right_input1" placeholder="姓名">
                      <input name="candidatePhone" type="text" class="right_input2" placeholder="手机号">
                      <input name="candidateEmail" type="text" class="right_input3" placeholder="邮箱">
                    </div>
                    <div class="input_line">
                      <label for="candidate"><img src="${ctx}/static/images/must_icon.png" class="must_blue" />面试官</label>
                      <input name="interviewerName" type="text" class="right_input1" placeholder="姓名">
                      <input name="interviewerMdn" type="text" class="right_input2" placeholder="手机号">
                      <input name="interviewerEmail" type="text" class="right_input3" placeholder="邮箱">
                    </div>
                    <div class="input_line">
                      <label for="candidate"><span class="not_must">参与人</span></label>
                      <input name="participantName1" type="text" class="right_input1" placeholder="姓名">
                      <input name="participantMdn1" type="text" class="right_input2" placeholder="手机号">
                      <input name="candidateEmail1" type="text" class="right_input3" placeholder="邮箱">
                    </div>
                    <div class="input_line">
                      <label for="candidate"><span class="not_must">参与人</span></label>
                      <input name="participantName2" type="text" class="right_input1" placeholder="姓名">
                      <input name="participantMdn2" type="text" class="right_input2" placeholder="手机号">
                      <input name="participantEmail2" type="text" class="right_input3" placeholder="邮箱">
                    </div>
                    <div class="input_line">
                      <label for="feedbackType"><img src="${ctx}/static/images/must_icon.png" class="must_blue" />反馈表</label>
                      <select class="right_selecter" name="feedbackType">
                        <option value ="volvo">IT基本知识</option>
                        <option value ="saab">.NET开发</option>
                        <option value="opel">Java</option>
                        <option value="audi">JS</option>
                      </select>
                    </div>
                    <div class="input_line">
                      <label for="comment"><span class="not_must">备注</span></label>
                      <textarea class="comment">
                      </textarea>
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

            <div class="remark_dialog" id="remark_dialog_${message.people.id}">
                  <form class="cmxform" id="remarkForm" method="get" action="">
                  <fieldset>
                    <div class="input_line">
                      <label for="comment"><span class="not_must">备注</span></label>
                      <textarea class="comment">
                      </textarea>
                    </div>
                    <div class="remark_submit_area">
                          <div class="mes_btn"></div>
                          <div class="cancel_btn" onclick="cancelRemark()"></div>
                    </div>
                  </fieldset>
                </form>
            </div>
      		
      		</c:if>
		</c:when>
		<c:otherwise>
			<div class="single_resume">
	            <div class="mes_line_one">
	                  <img src="${ctx}/static/images/system_img.png" class="user_img"/>
	                  <span class="mes_line_one_account">系统账号</span>
	                  <img src="${ctx}/static/images/user_line.png" class="user_line"/>
	                  <span class="mes_line_one_type">简历追踪</span>
	                  <img src="${ctx}/static/images/user_line.png" class="user_line"/>
	                  <span class="mes_line_one_serial">Test</span>
	                  <span class="mes_date">${message.updateDate}</span>
	            </div>
	            <div class="clear"></div>
	            <div class="mes_line_two">
	                  <p>${message.activity.job.company.name}&nbsp;|&nbsp;${message.activity.job.title}&nbsp;|&nbsp;${message.activity.people.fullName}&nbsp;</p>
	            </div>
	            <div class="mes_line_center">
	            	<c:forEach var="act_round" items="${message.activity.activityRounds}">
	               	<div class="activity_round">
						<span>第<c:out value="${act_round.round}"/>轮</span>
						<c:choose>
							<c:when test="${not empty act_round.feedBack}">
								<span data-roundid="${act_round.id}" data-ctx="${ctx}"
data-behavihor=${act_round.feedBack.behavihor}
data-profession=${act_round.feedBack.profession}
data-language=${act_round.feedBack.language}
data-innovation=${act_round.feedBack.innovation}
data-communication=${act_round.feedBack.communication}
data-execution=${act_round.feedBack.execution}
data-teamwork=${act_round.feedBack.teamwork}
data-management=${act_round.feedBack.management}
data-result=${act_round.feedBack.result} data-toggle="modal" data-target="#feedBackModal" class="start_feedback">添加反馈</span>
							</c:when>
							<c:otherwise>
								<span data-roundid="${act_round.id}" data-ctx="${ctx}"
data-behavihor=0
data-profession=0
data-language=0
data-innovation=0
data-communication=0
data-execution=0
data-teamwork=0
data-management=0
data-result=0 data-toggle="modal" data-target="#feedBackModal" class="start_feedback">添加反馈</span>
							</c:otherwise>
						</c:choose>
						<%-- <span data-roundid="${act_round.id}" data-toggle="modal" data-target="#feedBackModal" class="start_feedback">添加反馈</span> --%>
						<span class="remark_span">备注</span>
						<span>通过</span>
	                   	<span class="start_interview">安排面试</span>
					</div>                      
	            	</c:forEach>
				</div>
			</div>    
		</c:otherwise>
	</c:choose>
</c:forEach>
	<div class="modal fade" id="feedBackModal" tabindex="-1" role="dialog" aria-labelledby="feedBackModalLabel" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
   					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
   					<h4 class="modal-title" id="feedBackModalLabel">添加面试反馈</h4>
 				</div>
 				<div class="modal-body" id="fogot-modal-body">
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
	</form>
		</div>
 				</div>
				<div class="modal-footer">
					<div class="form-group">
  						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  						<button type="button" id="saveBackFeed" data-loading-text="Processing.." class="btn btn-primary">保存</button>
  					</div>
 				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</c:if>

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
<script type="text/javascript" src="${ctx}/static/js/unit.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery-ui-timepicker-addon.js"></script>

<script type="text/javascript">
  //start validate user's input
$.validator.setDefaults({
	    submitHandler: function() {
	      return true;
	    }
	  });

	  function submitInterview(e){
	    // validate signup form on keyup and submit
		  console.log(e);
	    $("#interviewForm").validate({
	      rules: {
	        startTime: "required",
	        endTime: "required",
	        companyName: "required",
	        jobTitle: "required",
	        candidateEmail: "required",
	        interviewerEmail: "required"
	      },
	      messages: {
	        startTime: "请输入开始时间",
	        endTime: "请输入结束时间",
	        companyName: "请输入公司名称",
	        jobTitle: "请输入岗位名称",
	        feedback: "请选择反馈表",
	        candidateEmail: "请完善候选人信息",
	        interviewerEmail: "请完善面试官信息",
	      }
	    });
	}

</script>
</body>
