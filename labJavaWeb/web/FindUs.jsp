<%-- 
    Document   : FindUs
    Created on : Mar 17, 2020, 9:12:40 AM
    Author     : ANH DUC
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:useBean id="FindBean" class="Controller.FindBean" scope="request"/>
    <jsp:setProperty name="FindBean" property="page" param="page"/>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <div class="size-content">
            <jsp:include page="Header.jsp"/>
            <div class="content-left">
                <c:forEach var="ad" items="${FindBean.address}"> 
                    <div class="section">
                        <div class="menu-price">Find us</div>
                        <div class="address-contact">
                            <div class="ad">Address and contact:</div> 
                            <div class="ad-name">${ad.name}</div>
                            <div>${ad.address}</div>
                            <div class="tel-find">Tel:   ${ad.tel}</div>
                            <div>Email: ${ad.email}</div>
                        </div>
                        <div class="opening-hour">
                            <div class="op">Opening hours:</div>
                            <c:forEach var="o" items="${ad.openings}">
                                <div class="opening">${o.day} ${o.opening}</div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="map-content">
                        <iframe src="${ad.mapAddress}" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0">
                        </iframe>
                    </div>
                </c:forEach>
                <div class="page" id="page">
                    <p><c:forEach var="i" begin="1" end="${FindBean.totalPages}">
                            <c:url var="u" value="FindUs.jsp">
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
