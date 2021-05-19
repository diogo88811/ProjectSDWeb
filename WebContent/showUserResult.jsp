<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Hey!</title>
</head>
<body>
<div style="margin: 0 auto; text-align: center">
    <p style="font-size:30px; font-family: 'Courier New'">
        <br>
        <c:forEach items="${heyBean.userinfo}" var="value">
            <c:out value="${value}"/><br><br>
        </c:forEach>
    </p>
    <br>
    <p style="font-size:30px; font-family: 'Courier New'">
        <a href="<s:url action="initial" />">Voltar</a>
    </p>
</div>
</body>
</html>