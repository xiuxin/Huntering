<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>招聘-首页</title>
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/style.css"/>


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
                          <span class="account_left">老刘</span>
                          <span class="account_right">登出</span>
                        </div>
                        <div class="account_detail_line">
                          <span class="account_left">上传邮箱</span>
                          <span class="account_middle">francoliuad.net@163.com</span>
                        </div>
                        <div class="account_detail_line">
                          <span class="account_left">登陆邮箱</span>
                          <span class="account_middle">francoliuad.net@163.com</span>
                          <span class="account_right">管理</span>
                        </div>
                        <div class="account_detail_line">
                          <span class="account_left">手机</span>
                          <span class="account_middle">15902187456</span>
                          <span class="account_right_phone">已验证</span>
                        </div>
                        <div class="account_detail_line">
                          <span class="account_left">所属公司</span>
                          <span class="account_middle">上海微创</span>
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

      <div class="single_resume">
            <div class="mes_line_one">
                  <img src="${ctx}/static/images/system_img.png" class="user_img"/>
                  <span class="mes_line_one_account">系统账号</span>
                  <img src="${ctx}/static/images/user_line.png" class="user_line"/>
                  <span class="mes_line_one_type">简历追踪</span>
                  <img src="${ctx}/static/images/user_line.png" class="user_line"/>
                  <span class="mes_line_one_serial">Test</span>
                  <span class="mes_date">2014.10.12 12.12</span>
            </div>
            <div class="clear"></div>
            <div class="mes_line_two">
                  <p>您已成功通过邮件导入了一份简历，系统正在自动识别并会在第一时间通知到您。</p>
            </div>
            <div class="mes_line_three">
                  <div class="start_interview_div">
                      <div class="start_interview" onclick="statInterview(this)">安排面试</div>
                      <div class="interview_white_trangle">
                          <img src="${ctx}/static/images/white_trangle.png" class="interview_white_trangle_img"/>
                     </div>
                  </div>
                  <div class="remark_div">
                      <div class="start_remark" onclick="statRemark(this)">备注</div>
                      <div class="remark_white_trangle">
                          <img src="${ctx}/static/images/white_trangle.png" class="remark_white_trangle_img"/>
                     </div>
                  </div>
                  
            </div>
            
      </div>
      <div class="interview_dialog">
                  <form class="cmxform" id="interviewForm" method="get" action="">
                  <fieldset>
                    <div class="input_line">
                      <label for="startTime"><img src="${ctx}/static/images/must_icon.png" class="must_blue" />开始时间</label>
                      <input name="startTime" type="text" class="right_input" placeholder="2015/01/21 22:05">
                    </div>
                    <div class="input_line">
                      <label for="endTime"><img src="${ctx}/static/images/must_icon.png" class="must_blue" />结束时间</label>
                      <input name="endTime" type="text" class="right_input" placeholder="2015/01/21 22:05">
                    </div>
                    <div class="input_line">
                      <label for="companyName"><img src="${ctx}/static/images/must_icon.png" class="must_blue" />公司</label>
                      <input name="companyName" type="text" class="right_input" placeholder="公司名称">
                    </div>
                    <div class="input_line">
                      <label for="job"><img src="${ctx}/static/images/must_icon.png" class="must_blue" />岗位</label>
                      <input name="job" type="text" class="right_input" placeholder="岗位名称">
                    </div>
                    <div class="input_line">
                      <label for="position"><span class="not_must">地点</span></label>
                      <input name="position" type="text" class="right_input" placeholder="面试地点">
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
                      <label for="feedback"><img src="${ctx}/static/images/must_icon.png" class="must_blue" />反馈表</label>
                      <select class="right_selecter" name="feedback">
                        <option value ="volvo">IT基本知识</option>
                        <option value ="saab">.NET开发</option>
                        <option value="opel">Java</option>
                        <option value="audi">JS</option>
                      </select>
                    </div>
                    <div class="input_line">
                      <label for="feedback"><span class="not_must">备注</span></label>
                      <textarea class="backup_textarea">
                      </textarea>
                    </div>
                    <div class="submit_area">
                          <div class="mes_btn" onclick="submitInterview()">
                            <input class="submit mes_btn_submit" type="submit">
                          </div>
                          <div class="cancel_btn" onclick="cancelInterview()"></div>
                    </div>
                  </fieldset>
                </form>
            </div>

            <div class="remark_dialog">
                  <form class="cmxform" id="remarkForm" method="get" action="">
                  <fieldset>
                    <div class="input_line">
                      <label for="feedback"><span class="not_must">备注</span></label>
                      <textarea class="backup_textarea">
                      </textarea>
                    </div>
                    <div class="remark_submit_area">
                          <div class="mes_btn"></div>
                          <div class="cancel_btn" onclick="cancelRemark()"></div>
                    </div>
                  </fieldset>
                </form>
            </div>

      <div class="single_resume">
            <div class="mes_line_one">
                  <img src="${ctx}/static/images/system_img.png" class="user_img"/>
                  <span class="mes_line_one_account">系统账号</span>
                  <img src="${ctx}/static/images/user_line.png" class="user_line"/>
                  <span class="mes_line_one_type">档案更新</span>
                  <span class="mes_date">2014.10.12 12.12</span>
            </div>
            <div class="clear"></div>
            <div class="mes_line_two" style="margin-bottom:0px;">
                  <p>公司信息“XXXXXX”录入完毕。</p>
            </div>
      </div>

      <div class="single_resume">
            <div class="mes_line_one">
                  <img src="${ctx}/static/images/system_img.png" class="user_img"/>
                  <span class="mes_line_one_account">系统账号</span>
                  <img src="${ctx}/static/images/user_line.png" class="user_line"/>
                  <span class="mes_line_one_type">面试追踪</span>
                  <img src="${ctx}/static/images/user_line.png" class="user_line"/>
                  <span class="mes_line_one_serial">IG20150128(2)</span>
                  <span class="mes_date">2014.10.12 12.12</span>
            </div>
            <div class="clear"></div>
            <div class="mes_line_two">
                  <p>Microsoft&nbsp;|&nbsp;.NET Engineer&nbsp;|&nbsp;张三/CCD&nbsp;|&nbsp;李四/HM&nbsp;|赵武/HM&nbsp;|&nbsp;2015/1/19 14:00（还有1小时）</p>
            </div>
            <div class="mes_line_three">
                  <span class="start_interview">添加反馈</span>
                  <span class="remark_span">备注</span>
                  <span class="cancel_interview">取消面试</span>
            </div>
      </div>

      <div class="single_resume">
            <div class="mes_line_one">
                  <img src="${ctx}/static/images/system_img.png" class="user_img"/>
                  <span class="mes_line_one_account">系统账号</span>
                  <img src="${ctx}/static/images/user_line.png" class="user_line"/>
                  <span class="mes_line_one_type">面试追踪</span>
                  <img src="${ctx}/static/images/user_line.png" class="user_line"/>
                  <span class="mes_line_one_serial">IG20150128(2)</span>
                  <span class="mes_date">2014.10.12 12.12</span>
            </div>
            <div class="clear"></div>
            <div class="mes_line_two">
                  <p>Microsoft&nbsp;|&nbsp;.NET Engineer&nbsp;|&nbsp;张三/CCD&nbsp;|&nbsp;李四/HM&nbsp;|赵武/HM&nbsp;|&nbsp;设计经验比较多，代码风骚，不出bug，工作太度认真，能加班加点，愿意出差，精通各种php、Object C，服从公司安排，设计体验设计体验看见阿里斯顿会计法离开家阿拉斯加地方，阿斯顿发徕卡就是到付款链接就阿里斯柯达解放路看见爱死了都快放假垃圾死地方离开家。&nbsp;|&nbsp;2015/1/19 14:00（还有1小时）</p>
            </div>
            <div class="mes_line_three">
                  <span class="start_interview">添加反馈</span>
                  <span class="remark_span">备注</span>
                  <span class="cancel_interview">取消面试</span>
            </div>
      </div>

      <div class="single_resume">
            <div class="mes_line_one">
                  <img src="${ctx}/static/images/system_img.png" class="user_img"/>
                  <span class="mes_line_one_account">系统账号</span>
                  <img src="${ctx}/static/images/user_line.png" class="user_line"/>
                  <span class="mes_line_one_type">面试追踪</span>
                  <img src="${ctx}/static/images/user_line.png" class="user_line"/>
                  <span class="mes_line_one_serial">IG20150128(2)</span>
                  <span class="mes_date">2014.10.12 12.12</span>
            </div>
            <div class="clear"></div>
            <div class="mes_line_two">
                  <p>Microsoft&nbsp;|&nbsp;.NET Engineer&nbsp;|&nbsp;张三/CCD&nbsp;|&nbsp;李四/HM&nbsp;|赵武/HM&nbsp;|&nbsp;设计经验比较多，代码风骚，不出bug，工作太度认真，能加班加点，愿意出差，精通各种php、Object C，服从公司安排，设计体验设计体验看见阿里斯顿会计法离开家阿拉斯加地方，阿斯顿发徕卡就是到付款链接就阿里斯柯达解放路看见爱死了都快放假垃圾死地方离开家。&nbsp;|&nbsp;2015/1/19 14:00（还有1小时）</p>
            </div>
            <div class="mes_line_three">
                  <span class="start_interview">添加反馈</span>
                  <span class="remark_span">备注</span>
                  <span class="cancel_interview">取消面试</span>
            </div>
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
<script type="text/javascript" src="${ctx}/static/js/unit.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.validate.js"></script>
<script type="text/javascript">
  //start validate user's input
$.validator.setDefaults({
    submitHandler: function() {
      alert("submitted!");
    }
  });

  function submitInterview(){
    // validate signup form on keyup and submit
    $("#interviewForm").validate({
      rules: {
        startTime: "required",
        endTime: "required",
        companyName: "required",
        job: "required",
        candidateEmail: "required",
        interviewerEmail: "required"
      },
      messages: {
        startTime: "请输入开始时间",
        endTime: "请输入结束时间",
        companyName: "请输入公司名称",
        job: "请输入岗位名称",
        feedback: "请选择反馈表",
        candidateEmail: "请完善候选人信息",
        interviewerEmail: "请完善面试官信息",
      }
    });
}

</script>
</body>
