<%@page import="com.java.model.Study"%>
<%-- 
    Document   : editstudy
    Created on : Sep 26, 2015, 1:54:41 AM
    Author     : Pinkesh
--%>


<jsp:include page="header.jsp" />
<div class="inline">
    <div class="top_page">
        <span class="bck_grey"> Editing a study </span>
    </div>
    <div class="top_breadcrum">
        <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
    </div>
</div>
<% Study study = new Study();
    study = (Study) session.getAttribute("Study");
    if (session.getAttribute("Study") != null) {
%>

<form action="studycontroller?action=editstudy" method="POST" class="signup_center">
    <ul>
        <li class="inline"><label >Study Name*</label></li>
        <li class="inline"><input class="pad_3" placeholder="Study Name" type="text" size="40" name="studyname" value="${Study.name}" required><br></li>
    </ul>
    <ul>
        <li class="inline"><label >Question Text*</label></li>
        <li class="inline"><input class="pad_3" placeholder="Question Text" type="text" size="40" name="questiontext" value="${Study.question}" required /><br></li>
    </ul>
    <ul style="height:120px">

        <li class="inline"><label >Image*</label></li>
        <li class="inline"> <img class="img_editstudy pad_3" alt="Default Image" src="<%=request.getContextPath() + "/uploadImage?filename=" + study.getImageURL()%>"></li>
        <li class="inline"><input type="button" name="image_upload" value="Choose File" onclick="window.open('<%=request.getContextPath() + "/studycontroller?action=open"%>', null, 'height=200,width=350,status=yes,toolbar=no,menubar=no,location=no')" /><br><br></li>
    </ul> 
    <ul>
        <li class="inline"><label ># Participants*</label></li>
        <li class="inline"><input class="pad_3" type="number"  name="participants" value=${Study.requestedparticipants}  required /><br></li>
    </ul> 
    <ul>
        <li class="inline"><label>Description*</label></li>
        <li class="inline"><textarea name="description" rows="4" cols="45" required>${Study.description}</textarea><br></li>
    </ul>
    <ul>
        <li class="inline "></li>
        <li class="inline"> <input type="submit" class="button" value="Update" name="Submit" /></li>
    </ul>
    <input type="hidden" name="studyCode" value="${Study.studyCode}"/>
</form>
<% } else {
%>
<div class="inline">
    <div class="top_page">
        <span class="bck_grey"> No data received. </span>
    </div>
</div>

<%}%>
<jsp:include page="footer.jsp" />
