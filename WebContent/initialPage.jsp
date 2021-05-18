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
            <div1 style="margin: 0 auto; text-align: center">
                <br>
                <p style="font-size:50px; font-family: 'Courier New'">
                    <a>Bem Vindo!</a>
                </p>
            </div1>
            <br>
            <p style="font-size:30px; font-family: 'Courier New'">
                <a href="<s:url action="registerPersonAction" />">Registar um utilizador</a>
            </p>
            <p style="font-size:30px ;  font-family: 'Courier New'">
                <a href="<s:url action="createElectionAction" />">Criar Eleicao</a>
            </p>
            <p style="font-size:30px ; font-family: 'Courier New'">
                <a href="<s:url action="selectElectionAction" />">Gerir uma Eleicao</a>
            </p>
            <p style="font-size:30px ; font-family: 'Courier New'">
                <a href="<s:url action="selectionElectionToResultAction" />">Consultar Resultados das eleicoes Passadas</a>
            </p>
            <p style="font-size:30px ; font-family: 'Courier New'">
                <a href="<s:url action="selectElectionViewAction" />">Listas Dados de uma Eleicão</a>
            </p>
            <p style="font-size:30px ; font-family: 'Courier New'">
                <a href="<s:url action="chatAction" />">Listar utilizadores online</a>
            </p>
            <p style="font-size:30px ;  font-family: 'Courier New'">
                <a href="<s:url action="index" />">Sair de Sessão</a>
            </p>
        </div>
    </body>
</html>
