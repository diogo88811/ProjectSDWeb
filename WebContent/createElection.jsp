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
<s:form action="createElection" method="post">

    <s:text name="Nome Eleicao: " />
    <s:textfield name="nameElection" /><br>
    <s:text name="Data Inicio (aaaa-mm-dd hh:mm:ss): " />
    <s:textfield name="initDate" /><br>
    <s:text name="Data Fim (aaaa-mm-dd hh:mm:ss): " />
    <s:textfield name="endDate" /><br>
    <s:text name="Publico Alvo:" />
    <s:textfield name="publicTarget" /><br>

    <select name="" >
        <c:forEach items="${heyBean.users}" var="value">
            <option> ${value} </option>
            <br>
        </c:forEach>
    </select>
    <br>
    <s:submit />
</s:form>
</body>
</html>