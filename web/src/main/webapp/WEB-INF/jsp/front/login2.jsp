<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>招聘</title>
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
               <form id="loginForm" method="post" role="form">
               <div class="email_input_div">
                 <input type="email" class="email_input" name="email" placeholder="邮箱" id="emailHeader" />
               </div>
               <div class="pwd_input_div">
                 <input type="password" class="pwd_input" name="password" placeholder="密码" id="pwdHeader" />
                 <div class="get_pwd" onclick="getPwd()">忘记密码？</div>
               </div>
               <div class="login_btn_div" onclick="loginHeader()">
               </div>
               </form>
        </div>
</div>
<!--top bar end-->

<!--login middle start-->
<div class="outer_login_middle">
<div class="container login_middle">
    <div class="slogan_black">
          让天下没有难做的招聘！
    </div> 
    <div class="login_whole_div">
          <div class="login_left_div">
                <div class="outer_hunter_img">
                    <img src="${ctx}/static/images/huntering_peo.png" />
                </div>
                <div class="under_hunter_img">
                    "Huntering！让天下没有难做的招聘，他们都做了<br>选择，你还在等什么？"
                </div>
                <div class="short_text">
                  马云，阿里巴巴CEO
                </div>
          </div>

          <div class="login_right_div">
                <div class="join_now">
                  立即免费加入<span style="font-family: Arial;">Huntering</span>
                </div>
                <div class="regist_info">
                  只需几步，就能轻松注册
                </div>
                <form id="signupForm" action="register" method="post" role="form">
                <div class="name_inputt">
                    <img src="${ctx}/static/images/must_icon.png" class="must_icon name_icon_show"/>
                    <img src="${ctx}/static/images/must_icon_hide.png" class="must_icon name_icon_hide"/>
                    <input type="email" placeholder="称呼" class="name_input" name="presentName" id="nameVal" onBlur="nameBlur()" />
                    <input type="email" placeholder="称呼" class="name_input" id="nameVal2" onBlur="nameBlur2()" />
                </div>
                <div class="name_alert">
                    <div class="alert_hide alert_hide_name">请输入您的称呼!</div>
                </div>
                <div class="email_inputt">
                    <img src="${ctx}/static/images/must_icon.png" class="must_icon email_icon_show"/>
                    <img src="${ctx}/static/images/must_icon_hide.png" class="must_icon email_icon_hide"/>
                    <input type="email" placeholder="邮箱" class="name_input" name="email" id="emailVal" onBlur="emailBlur()" />
                    <input type="email" placeholder="邮箱" class="name_input" id="emailVal2" onBlur="emailBlur2()" />
                </div>
                <div class="name_alert">
                    <div class="alert_hide alert_hide_email">请输入正确的邮箱格式!</div>
                </div>
                <div class="email_inputt">
                    <img src="${ctx}/static/images/must_icon.png" class="must_icon pwd_icon_show"/>
                    <img src="${ctx}/static/images/must_icon_hide.png" class="must_icon pwd_icon_hide"/>
                    <input type="password" placeholder="密码" class="name_input" name="password" id="pwdlVal" onBlur="pwdBlur()" />
                    <input type="password" placeholder="密码" class="name_input" id="pwdlVal2" onBlur="pwdBlur2()" />
                </div>
                <div class="name_alert">
                    <div class="alert_hide alert_hide_pwd">请输入您的密码!</div>
                </div>
                <div class="invitation_input">
                    <input type="text" placeholder="邀请码" class="name_input" name="invitationcode" id="invitationcode" onBlur="pwdBlur()" />
                </div>
                <div class="agree_info">
                    <div>
                      点击"申请"即代表您同意Huntering的<span class="font_dark">用户协议、隐私权</span>
                    </div>
                    <div>
                      <span class="font_dark">政策</span>及<span class="font_dark">CooKie政策。</span>
                    </div>
                </div>
                <div class="apply_btn" onclick="applyClick()">
                </div>
                </form>
          </div>

          <div class="login_right_div apply_result">
                <div class="join_now_result">
                  感谢您的申请！
                </div>
                <div class="regist_info_result">
                  系统正在审核，请耐心等待。
                </div>
          </div>

          <div class="login_right_div right_get_pwd">
                <div class="join_now_result">
                  更改密码！
                </div>
                <div class="regist_info_result">
                  想想您之前注册的邮箱
                </div>
                <div class="outer_emailValGet">
                  <input type="email" placeholder="邮箱" class="name_input" name="" id="emailValGet" onBlur="emailBlurGet()" />
                  <input type="email" placeholder="邮箱" class="name_input" id="emailValGet2" onBlur="emailBlurGet2()" />
                </div>
                <div class="name_alert">
                    <div class="alert_hide alert_hide_emaill">请输入正确的邮箱格式!</div>
                </div>
                <div class="goon_div" onclick="confirmGetPwd()">
                </div>
          </div>
          
    </div>       
</div>
</div>
<!--login middle end-->


 <!--footer start-->
    <div class="above_footer_line"></div>
    <div class="container footer">
      <footer>
            <div class="copy_right_first">
                <a href="#" style="color: #0073c6;">关于我们</a><img src="${ctx}/static/images/footer_line.png" class="footer_line" />
                <a href="#" style="color: #0073c6;">加入我们</a><img src="${ctx}/static/images/footer_line.png" class="footer_line" />
                <a href="#" style="color: #0073c6;">联系我们</a><img
					src="${ctx}/static/images/footer_line.png" class="footer_line" /> <a href="#" style="color: #0073c6;">邀请我们</a>
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
</body>
