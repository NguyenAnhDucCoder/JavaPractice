<%-- 
    Document   : ShareBox
    Created on : Mar 17, 2020, 9:14:22 AM
    Author     : ANH DUC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="ShareBean" class="Controller.ShareBean" scope="request"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <div class="content-right">
            <div class="share-box">
                <div class="share-header">Share this page</div>
                <div class="share-content">
                    <c:forEach var="social" items="${ShareBean.social}">
                        <div><a href=""><img src="${social.icon}"> ${social.link}</a></div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
