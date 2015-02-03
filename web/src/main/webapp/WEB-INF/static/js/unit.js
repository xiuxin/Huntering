function applyInvCode() {
	hideAllLoginRight();
	$(".right_apply_invcode").show();
}
function confirmApplyInvcode(){
	if( $("#emailValGet_invcode").val() == "" || isEmail($("#emailValGet_invcode").val()) == false ){
		$(".alert_hide_apply_invcode").hide();
		$(".alert_hide_emaill_invcode").show();
		$("#emailValGet_invcode").addClass("red_border");
	} else {
		var email = $("#emailValGet_invcode").val();
		$.ajax({
			dataType : "json",
			type: "POST",
			url : "/public/applyInvCode?time=" + new Date().getTime(),
			data : {email: email},
			success : function(data) {
				if(data.success) {
					$(".alert_apply_invcode_su")[0].innerText=data.result;
					$(".right_apply_invcode").hide();
					$(".apply_invcode_success").show();
				} else {
					$("#emailValGet_invcode").addClass("red_border");
					$(".alert_hide_emaill_invcode").hide();
					$(".alert_hide_apply_invcode")[0].innerText=data.result;
					$(".alert_hide_apply_invcode").show();
				}
			}
		});
	}

	if( $("#emailValGet2").val() != "" && isEmail($("#emailValGet2").val()) == true ){
		alert("success");
	}
}

function applyClick(){
	if( $("#nameVal").val() == "" && $("#nameVal2").val() == "" ){
		$(".alert_hide_name").show();
		$(".name_icon_show").hide();
		$(".name_icon_hide").show();
		$("#nameVal").hide();
		$("#nameVal2").show();
	}else{
		$(".alert_hide_name").hide();
		$(".name_icon_show").show();
		$(".name_icon_hide").hide();
		$("#nameVal").show();
		$("#nameVal2").hide();
	}

	if( $("#emailVal").val() == "" && $("#emailVal2").val() == "" ){
		$(".alert_hide_email").show();
		$(".email_icon_show").hide();
		$(".email_icon_hide").show();
		$("#emailVal").hide();
		$("#emailVal2").show();
	}else{
		$(".alert_hide_email").hide();
		$(".email_icon_show").show();
		$(".email_icon_hide").hide();
		$("#emailVal").show();
		$("#emailVal2").hide();
	}

	if( $("#pwdlVal").val() == "" && $("#pwdlVal2").val() == "" ){
		$(".alert_hide_pwd").show();
		$(".pwd_icon_show").hide();
		$(".pwd_icon_hide").show();
		$("#pwdlVal").hide();
		$("#pwdlVal2").show();
	}else{
		$(".alert_hide_pwd").hide();
		$(".pwd_icon_show").show();
		$(".pwd_icon_hide").hide();
		$("#pwdlVal").show();
		$("#pwdlVal2").hide();
	}

	if( $("#nameVal").val() != "" && $("#emailVal").val() != "" && $("#pwdlVal").val() != "" ){
		if( isEmail($("#emailVal").val()) == false ){
			$("#emailVal2").val( $("#emailVal").val() );
			$(".alert_hide_email").show();
			$(".email_icon_show").hide();
			$(".email_icon_hide").show();
			$("#emailVal").hide();
			$("#emailVal2").show();
		}else{
			$("#signupForm").submit();
		}
	}
}

function nameBlur(){
	if( $("#nameVal").val() == "" && $("#nameVal2").val() == "" ){
		$(".alert_hide_name").show();
		$(".name_icon_show").hide();
		$(".name_icon_hide").show();
		$("#nameVal").hide();
		$("#nameVal2").show();
	}else{
		$(".alert_hide_name").hide();
		$(".name_icon_show").show();
		$(".name_icon_hide").hide();
		$("#nameVal").show();
		$("#nameVal2").hide();
	}
}

function nameBlur2(){
	if( $("#nameVal").val() == "" && $("#nameVal2").val() == "" ){
		$(".alert_hide_name").show();
		$(".name_icon_show").hide();
		$(".name_icon_hide").show();
		$("#nameVal").hide();
		$("#nameVal2").show();
	}else{
		$("#nameVal").val( $("#nameVal2").val() );
		$(".alert_hide_name").hide();
		$(".name_icon_show").show();
		$(".name_icon_hide").hide();
		$("#nameVal").show();
		$("#nameVal2").hide();
	}
}

function emailBlur(){
	if( $("#emailVal").val() == "" && $("#emailVal2").val() == "" ){
		$(".alert_hide_email").show();
		$(".email_icon_show").hide();
		$(".email_icon_hide").show();
		$("#emailVal").hide();
		$("#emailVal2").show();
	}else{
		$(".alert_hide_email").hide();
		$(".email_icon_show").show();
		$(".email_icon_hide").hide();
		$("#emailVal").show();
		$("#emailVal2").hide();
	}
}

function emailBlur2(){
	if( $("#emailVal").val() == "" && $("#emailVal2").val() == "" ){
		$(".alert_hide_email").show();
		$(".email_icon_show").hide();
		$(".email_icon_hide").show();
		$("#emailVal").hide();
		$("#emailVal2").show();
	}else{
		$("#emailVal").val( $("#emailVal2").val() );
		$(".alert_hide_email").hide();
		$(".email_icon_show").show();
		$(".email_icon_hide").hide();
		$("#emailVal").show();
		$("#emailVal2").hide();
	}
}

function pwdBlur(){
	if( $("#pwdlVal").val() == "" && $("#pwdlVal2").val() == "" ){
		$(".alert_hide_pwd").show();
		$(".pwd_icon_show").hide();
		$(".pwd_icon_hide").show();
		$("#pwdlVal").hide();
		$("#pwdlVal2").show();
	}else{
		$(".alert_hide_pwd").hide();
		$(".pwd_icon_show").show();
		$(".pwd_icon_hide").hide();
		$("#pwdlVal").show();
		$("#pwdlVal2").hide();
	}
}

function pwdBlur2(){
	if( $("#pwdlVal").val() == "" && $("#pwdlVal2").val() == "" ){
		$(".alert_hide_pwd").show();
		$(".pwd_icon_show").hide();
		$(".pwd_icon_hide").show();
		$("#pwdlVal").hide();
		$("#pwdlVal2").show();
	}else{
		$("#pwdlVal").val( $("#pwdlVal2").val() );
		$(".alert_hide_pwd").hide();
		$(".pwd_icon_show").show();
		$(".pwd_icon_hide").hide();
		$("#pwdlVal").show();
		$("#pwdlVal2").hide();
	}
}

function invitationBlur(){
	if( $("#invitationcode").val() == ""){
		$(".inv_icon_show").hide();
		$(".inv_icon_hide").show();
		$(".alert_hide_invcode").show();
		$("#invitationcode").addClass("red_border");
	}else{
		$(".alert_hide_invcode").hide();
		$(".inv_icon_hide").hide();
		$(".inv_icon_show").show();
		$("#invitationcode").removeClass("red_border");
	}
}

function getPwd(){
	hideAllLoginRight();
	$(".right_get_pwd").show();
}

function confirmGetPwd(){
	if( $("#emailValGet").val() == "" || isEmail($("#emailValGet").val()) == false ){
		$(".alert_hide_recoverpwd").hide();
		$(".alert_hide_emaill").show();
		$("#emailValGet").addClass("red_border");
	} else {
		var email = $("#emailValGet").val();
		$.ajax({
			dataType : "json",
			type: "POST",
			url : "/public/recoverpwd?time=" + new Date().getTime(),
			data : {email: $("#emailValGet").val()},
			success : function(data) {
				if(data.success) {
					$(".alert_recoverpwd_su")[0].innerText=data.result;
					$(".right_get_pwd").hide();
					$(".get_pwd_success").show();
				} else {
					$("#emailValGet").addClass("red_border");
					$(".alert_hide_emaill").hide();
					$(".alert_hide_recoverpwd")[0].innerText=data.result;
					$(".alert_hide_recoverpwd").show();
				}
			}
		});
	}

	if( $("#emailValGet2").val() != "" && isEmail($("#emailValGet2").val()) == true ){
		alert("success");
	}
}

function emailBlurGet(){
	if( $("#emailValGet").val() != "" ){
		$("#emailValGet").removeClass("red_border");
		$(".alert_hide_emaill").hide();
	}
}

function emailBlurGet2(){
	if( $("#emailValGet").val() == "" ){
		$("#emailValGet").val( $("#emailValGet2").val() );
		$("#emailValGet2").hide();
		$("#emailValGet").show();
		$(".alert_hide_emaill").hide();
	}
}

function isEmail(str){ 
	var reg = /^([a-zA-Z])([a-zA-Z0-9._-])*@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
	return reg.test(str); 
} 

function loginHeader(){
	if( $("#emailHeader").val() == ""){
		alert("请输入您的邮箱!");
	}

	if( $("#pwdHeader").val() == ""){
		alert("请输入您的密码!");
	}

	/*if( $("#emailHeader").val() != "" && $("#pwdHeader").val() != "" ){
		alert("success");
	}*/
	
	if( isEmail($("#emailHeader").val()) == false ){
		alert("请输入正确的邮箱!");
	}else{
		$("#loginForm").submit();
	}

}

function hideAllLoginRight() {
	$(".login_right_div").hide();
}

$(document).ready(function(){
	if(typeof registerSuccess != 'undefined' && registerSuccess) {
		$(".login_right_div").hide();
		$(".register_success").show();
	}
	
	if(typeof registerFail != 'undefined' && registerFail) {
		$(".login_right_div").hide();
		$(".register_fail").show();
	}
	
	$(".back_signup").on("click", function(){
		$(".login_right_div").hide();
		$(".signup_main").show();
	});
});