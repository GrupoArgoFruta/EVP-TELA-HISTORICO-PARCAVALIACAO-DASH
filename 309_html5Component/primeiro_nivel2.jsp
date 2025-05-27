<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"  isELIgnored ="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="snk" uri="/WEB-INF/tld/sankhyaUtil.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="br.com.sankhya.modelcore.auth.AuthenticationInfo" %>
<%
    String idUsuario = ((AuthenticationInfo) session.getAttribute("usuarioLogado")).getUserID().toString();
%>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Primeiro Nivel</title>


    <!-- Bootstrap core CSS -->
    <link href="${BASE_FOLDER}/assets2/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${BASE_FOLDER}/assets2/css/style.css" rel="stylesheet">

    <snk:load/>

</head>


<body>
  
    <snk:query var="AD_PARCEIROAVALIACAO"> 

SELECT  
AD_AVALIACAO,
CODPARC,
AD_OBSAVAL,
NOMEUSU,
DATALTER
FROM 
AD_PARCEIROAVALIACAO
 WHERE TRUNC(DATALTER) BETWEEN :PERIODO.INI AND :PERIODO.FIN
 
    </snk:query>

    
    <main role="main">
        <div class="table-responsive">
            <section class="text-center">
                <div class="container"><br>
                    <h5>Bom</h5>
                    
                        <table class="table table-bordered table-striped-custom table-condensed-main">

                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">Avaliado como:</th>
                            <th scope="col">Parceiro</th>
                            <th scope="col">Motivo</th>
                            <th scope="col">Usuário avaliador</th>
                            <th scope="col">Data da avaliação</th>
                            
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${AD_PARCEIROAVALIACAO.rows}" var="row">
                                <tr>
                                    <td><c:out value="${row.AD_AVALIACAO}" /></td>
                                    <td><c:out value="${row.CODPARC}" /></td>
                                    <td><c:out value="${row.AD_OBSAVAL}" /></td>
                                    <td><c:out value="${row.NOMEUSU}" /></td>
                                    <td><c:out value="${row.DATALTER}" /></td>
                                   
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
            </section>
        </div>
      </main>
</body>
</html>