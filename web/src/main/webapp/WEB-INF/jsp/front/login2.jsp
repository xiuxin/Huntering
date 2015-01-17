<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>

<html>
Test Logind
	<form action="login" method="post">
	  <p>Email: <input type="text" name="email" /></p>
	  <p>Password: <input type="password" name="password" /></p>
	  <input type="submit" value="Submit" />
	</form>


Test Registration
	<form action="register" method="post">
	  <p>Email: <input type="text" name="email" /></p>
	  <p>Password: <input type="password" name="password" /></p>
	  <p>Invitation Code: <input type="text" name="invitationCode" /></p>
	  <input type="submit" value="Submit" />
	</form>
</html>