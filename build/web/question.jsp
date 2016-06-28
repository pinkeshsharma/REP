<%@page import="com.java.model.Study"%>
<%-- 
    Document   : question
    Created on : Sep 26, 2015, 2:07:15 AM
    Author     : Dimple
--%>

<jsp:include page="header.jsp" />
<div class="side_left2">
    <jsp:include page="sidemenu.jsp" />
</div>
<div class="side_right">
    <% Study study = new Study();
        study = (Study) session.getAttribute("Study");
        if (session.getAttribute("Study") != null) {
    %>
    <form method="POST" id="questionForm" action="<%=request.getContextPath() + "/studycontroller?action=answer"%>">
        <input type="hidden" name="studyCode" value="<%=study.getStudyCode()%>"/>
        <div class="inline">
            <div class="top_page">
                <span class="bck_grey"> Question </span>
            </div>
            <div><img alt="Question Image" class="div_image2 pad_3" src="<%=request.getContextPath() + "/uploadImage?filename="  + study.getImageURL()%>"></div>
        </div>
        <div class="inline">
            <div class="top_page">
                <p>Q : <%=study.getQuestion()%></p>
            </div>
            <div class="centered_2 pad_5">
                <select name="answer">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                </select>
            </div>
            <div class="centered_3"><button onclick="submit('questionForm')" class="button" type="button">Submit</button></div>
        </div>
        

    </form>
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