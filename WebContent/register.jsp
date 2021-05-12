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
<s:form action="registPerson" method="post">
    <s:text name="Username:" />
    <s:textfield name="username" /><br>
    <s:text name="Password:" />
    <s:textfield name="password" /><br>
    <s:text name="ccNumber:" />
    <s:textfield name="ccnumber" /><br>
    <s:text name="Telemovel:" />
    <s:textfield name="phone" /><br>
    <s:text name="Validade do CC:" />
    <s:textfield name="CCval" /><br>
    <s:text name="Morada:" />
    <s:textfield name="address" /><br>
    <s:text name="Função: " />
    <select name="work">
        <option>ESTUDANTE</option>
        <option>DOCENTE</option>
        <option>FUNCIONARIO</option>
    </select>
    <br>
    <s:text name="Departamento" />
    <s:textfield name="department" /><br>
    <select name="typePerson">
        <option>ADMIN</option>
        <option>USER</option>
    </select>
    <br>
    <s:submit />
</s:form>
</body>
</html>