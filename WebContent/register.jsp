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
<div style="margin: 0 auto; text-align: center">
    <br>
    <p style="font-size:20px; font-family: 'Courier New'">
    <s:text name="Username:" />
    <s:textfield name="username" /><br>
    </p>
    <p style="font-size:20px; font-family: 'Courier New'">
    <s:text name="Password:" />
    <s:textfield name="password" /><br>
    </p>
    <p style="font-size:20px; font-family: 'Courier New'">
    <s:text name="ccNumber:" />
    <s:textfield name="ccnumber" /><br>
    </p>
    <p style="font-size:20px; font-family: 'Courier New'">
    <s:text name="Telemovel:" />
    <s:textfield name="phone" /><br>
    </p>
    <p style="font-size:20px; font-family: 'Courier New'">

    <s:text name="Validade do CC:" />
    <s:textfield name="CCval" /><br>
    </p>
    <p style="font-size:20px; font-family: 'Courier New'">

    <s:text name="Morada:" />
    <s:textfield name="address" /><br>
    </p>
    <p style="font-size:20px; font-family: 'Courier New'">

    <s:text name="Função: " />
    <select name="work">
        <option>ESTUDANTE</option>
        <option>DOCENTE</option>
        <option>FUNCIONARIO</option>
    </select>
    </p>
    <p style="font-size:20px; font-family: 'Courier New'">
    <s:text name="Departamento" />
    <s:textfield name="department" /><br>
    </p>
    <p style="font-size:20px; font-family: 'Courier New'">
    <s:text name="Tipo de Conta: " />
    <select name="typePerson">
        <option>ADMIN</option>
        <option>USER</option>
    </select>
    </p>
    <br>
    <br>
    <s:submit />
</s:form>
</body>
</html>