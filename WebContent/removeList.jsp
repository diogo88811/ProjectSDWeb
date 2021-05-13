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
<s:form action="removeList" method="post">

  <s:text name="Listas: " />
  <p>
    <c:forEach items="${heyBean.lists}" var="value">
      <input type="radio" id="${value}" name="listSelected" value="${value}">
      <label for="${value}">${value}</label><br>
    </c:forEach>
  </p>
  <br>
  <s:submit />
</s:form>
</body>
</html>