<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

姓名：<c:out value="${profile.fullName}"/><br/>
邮箱：<c:out value="${profile.email}"/><br/>
个人简介：<c:out value="${profile.summary}"/><br/>
<br/>
教育经历
<table>
	<tr>
		<td colspan="2">degree</td>
		<td colspan="2">college</td>
		<td colspan="2">startDate</td>
		<td colspan="2">endDate</td>
	</tr>
	<c:if test="${not empty profile.peopleEducation}">
		<c:forEach var="education" items="${profile.peopleEducation}">
			<td colspan="2"><c:out value="${education.degree}"/></td>
			<td colspan="2"><c:out value="${education.college}"/></td>
			<td colspan="2"><c:out value="${education.startDate}"/></td>
			<td colspan="2"><c:out value="${education.endDate}"/></td>
			
		</c:forEach>
	</c:if>
</table>
<br/>

工作经历
<table>
	<tr>
		<td colspan="2">company</td>
		<td colspan="2">title</td>
		<td colspan="2">detail</td>
		<td colspan="2">startDate</td>
		<td colspan="2">endDate</td>
	</tr>
	<c:if test="${not empty profile.peopleCompany}">
		<c:forEach var="company" items="${profile.peopleCompany}">
			<td colspan="2"><c:out value="${company.company.name}"/></td>
			<td colspan="2"><c:out value="${company.title}"/></td>
			<td colspan="2"><c:out value="${company.detail}"/></td>
			<td colspan="2"><c:out value="${company.startDate}"/></td>
			<td colspan="2"><c:out value="${company.endDate}"/></td>
			
		</c:forEach>
	</c:if>
</table>
<br/>