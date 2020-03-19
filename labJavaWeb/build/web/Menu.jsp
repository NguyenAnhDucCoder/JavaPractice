<%-- 
    Document   : Menu
    Created on : Mar 17, 2020, 9:14:03 AM
    Author     : ANH DUC
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:useBean id="MenuBean" class="Controller.MenuBean" scope="request"/>
    <jsp:setProperty name="MenuBean" property="page" param="page"/>
    <jsp:setProperty name="MenuBean" property="pageSize" param="pageSize"/>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/script.js"></script>
        <title>Menu and Price list</title>                   
    </head>
    <body>
        <div class="size-content">
            <jsp:include page="Header.jsp"/>
            <div class="content-left">
                <div class="menu-price">Menu and Price list</div>

                <c:forEach var="m" items="${MenuBean.allMenu}">  
                    <div class="section">
                        <div class="menu-head">
                            <div class="menu-title">Menu ${m.id}</div>
                            <div class="price-title">Price</div>
                        </div>                   
                        <div class="menu-name">${m.name}</div>
                        <div class="price">€${m.price}</div>
                        <div class="menu-content">${m.content}</div>  
                    </div>
                </c:forEach> 

                <div class="page" id="page">
                    <p><c:forEach var="i" begin="1" end="${MenuBean.totalPages}">
                            <c:url var="u" value="Menu.jsp">
                                <c:param name="page" value="${i}"/>
                            </c:url>                           
                        <div class="page1">                           
                            <a href="${u}">${i}</a></div>
                    </c:forEach></p>
                </div>       
            </div>
            <jsp:include page="ShareBox.jsp"/>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
    <script>
        DisabledLink();
    </script>
</html>
