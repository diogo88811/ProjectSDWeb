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
    <p><a href="<s:url action="registerPersonAction" />">Registar um utilizador</a></p>
    <p><a href="<s:url action="createElectionAction" />">Criar Eleicao</a></p>
    <p><a href="<s:url action="selectElectionAction" />">Adicionar Lista a uma Eleicao</a></p>
    <p><a href="<s:url action="changeElectionPropertiesAction" />">Modificar uma Eleicao</a></p>
    <p><a href="<s:url action="index" />">Sair de Sessão</a></p>
</body>
</html>
