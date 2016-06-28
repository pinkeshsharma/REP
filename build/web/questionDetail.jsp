<%@page import="com.java.model.Answer"%>
<%@page import="java.util.List"%>
<%@page import="com.java.model.Study"%>
<%-- 
    Document   : question
    Created on : Sep 26, 2015, 2:07:15 AM
    Author     : Dimple
--%>

<jsp:include page="header.jsp" />
<div class="side_right">
    <% Study study = new Study();
        study = (Study) session.getAttribute("Study");
        if (session.getAttribute("Study") != null) {
    %>
   
        <div class="inline">
            <div class="top_page">
                <span class="bck_grey"> Question </span>
            </div>
            <div><img alt="Question Image" class="div_image3 pad_3" src="<%=request.getContextPath() + "/uploadImage?filename="  + study.getImageURL()%>"></div>
        </div>
        <div class="inline">
            <div class="top_page">
                <p>Q : <%=study.getQuestion()%></p>
            </div>
            <div class="centered_2 pad_5">
                <span>Answers: </span></br>
                <%List<String> answers = (List) study.getAnswers();
                for(String answer : answers){
                %>
                <span>&#8227;<%=answer%></span></br>
                <%}%>
            </div>
            
        </div>
    <% } else {
    %>
    <div class="inline">
        <div class="top_page">
            <span class="bck_grey"> No data received. </span>
        </div>
    </div>

    <%}%>
</div>
<jsp:include page="footer.jsp" />