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
<s:form action="listChanged" method="post">

    <s:text name="Nome: " />
    <s:textfield name="newListName" /><br>
    <br>
    <s:text name="Candidato Principal: "/>
    <p>
        <c:forEach items="${heyBean.people}" var="value">
            <input type="radio" id="${value}" name="changePrincipalCandidate" value="${value}">
            <label for="${value}">${value}</label><br>
        </c:forEach>
    </p>
    <br>
    <s:text name="Adicionar Pessoas: "/>
    <p>
        <c:forEach items="${heyBean.addpersontochangelist}" var="value">
            <input type="checkbox" id="${value}" name="addPeopleToList" value="${value}">
            <label for="${value}">${value}</label><br>
        </c:forEach>
    </p>
    <br>
    <s:text name="Remover Pessoas: "/>
    <p>
        <c:forEach items="${heyBean.deletepersonfromlist}" var="value">
            <input type="checkbox" id="${value}" name="deletePeopleFromList" value="${value}">
            <label for="${value}">${value}</label><br>
        </c:forEach>
    </p>
    <br>
    <s:submit />
</s:form>
</body>
</html>