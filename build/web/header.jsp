<%-- 
    Document   : home
    Created on : Sep 18, 2015, 5:26:43 PM
    Author     : Pinkesh
--%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Researchers Exchange Participations</title>
        <link rel="stylesheet" href="styles/common.css">
        <link rel="stylesheet" href="styles/datatables.min.css">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <script src="script/jquery-1.11.3.min.js"></script>
        <script src="script/jquery.dataTables.min.js"></script>
        <script src="script/common_script.js"></script>


        <!-- Attributes for Facebook -->
        <meta property="og:url"           content="https://openshiftdemo-pinkeshsharma.rhcloud.com/REP" />
        <meta property="og:type"          content="website" />
        <meta property="og:title"         content="Researchers Exchange Participations" />
        <meta property="og:description"   content="Researchers Exchange Participations is a platform for researchers to exchange participation." />
        <meta property="og:image"         content="https://openshiftdemo-pinkeshsharma.rhcloud.com/REP/images/default_user_female.png" />

    </head>
    <body>
        <div class="top">
            <div>
                <span><a href="<%=request.getContextPath() + "/controller?action=home"%>">Researchers Exchange Participations</a></span>
                <div class="top_box">
                    <a href="<%=request.getContextPath() + "/controller?action=about"%>">About us</a>   
                </div>
                <div class="top_box">
                    <a href="<%=request.getContextPath() + "/controller?action=how"%>">How it works</a>
                </div>
                <div class="top_box">
                    <c:if test="${User != null}">
                        <div>Hello, ${User.name}</div>
                    </c:if> 
                    <c:if test="${User == null}">
                        <a href="<%=request.getContextPath() + "/controller?action=login_pg"%>">Log in</a>
                    </c:if>

                </div>
            </div>
        </div>