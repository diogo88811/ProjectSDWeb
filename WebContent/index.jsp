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
		<div1 style="margin: 0 auto; text-align: center">
			<br>
			<p style="font-size:50px; font-family: 'Courier New'">
				<s:text name="Votação Online" />
			</p>

		</div1>

		<div style="margin: 0 auto; text-align: center">
			<s:form action="login" method="post">
				<br>
				<p style="font-size:30px; font-family: 'Courier New'">
					<s:text name="Username:" />
					<s:textfield name="username" /><br>
				</p>
				<p style="font-size:30px; font-family: 'Courier New'">
					<s:text name="Password:" />
					<s:textfield name="password" /><br>
				</p>
				<p style="font-size:30px; font-family: 'Courier New'">
					<s:text name="Numero CC:" />
					<s:textfield name="ccnumber" /><br>
				</p>
				<br>
				<s:submit />
			</s:form>
		</div>
	</body>
</html>