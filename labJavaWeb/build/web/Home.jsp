<%-- 
    Document   : Home
    Created on : Mar 17, 2020, 9:13:43 AM
    Author     : ANH DUC
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:useBean id="HomeBean" class="Controller.HomeBean" scope="request"/>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8;">
        <title>Home</title>
    </head>
    <body>
        <div class="size-content">
            <jsp:include page="Header.jsp"/>
            <div class="content-left">
                <div class="section">                          
                    <c:forEach var="introduction" items="${HomeBean.imageIntro}">
                        <div class="image-intro"><img src="${introduction.image}" ></div>
                        </c:forEach>
                </div>
                <c:forEach var="intro" items="${HomeBean.intro}">
                    <div class="title-intro"> ${intro.title} </div>
                    <div class="image-menu"><img src="${intro.image}"></div>
                    <div class="content-home">${intro.content}</div>
                </c:forEach>
            </div>
            <jsp:include page="ShareBox.jsp"/>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
