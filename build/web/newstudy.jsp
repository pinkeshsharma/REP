<%-- 
    Document   : newstudy
    Created on : Sep 26, 2015, 1:37:59 AM
    Author     : Pinkesh
--%>

<jsp:include page="header.jsp" />
<div class="inline">
    <div class="top_page">
        <span class="bck_grey"> Adding a study </span>
    </div>
    <div class="top_breadcrum">
        <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
    </div>
</div>
        <form action="studycontroller?action=newstudy" method="POST" class="signup_center">
            <ul>
                <li class="inline"><label >Study Name*</label></li>
                <li class="inline"><input class="login_input" placeholder="Study Name" type="text" size="40" name="studyname" required /><br></li>
            </ul>
            <ul>
                <li class="inline"><label >Question Text*</label></li>
                <li class="inline"><input class="login_input" placeholder="Question Text" type="text" size="40" name="questiontext" required /><br></li>
            </ul>
            <ul>
                <li class="inline"><label >Image*</label></li>
                <li class="inline"><input type="button" name="image_upload" value="Choose File" onclick="window.open('<%=request.getContextPath() + "/studycontroller?action=open"%>',null,'height=200,width=300,status=yes,toolbar=no,menubar=no,location=no')" /><br></li>
            </ul> 
            <ul>
                <li class="inline"><label ># Participants*</label></li>
                <li class="inline"><input class="login_input" type="number" name="participants" required /><br></li>
            </ul> 
            <ul>
                <li class="inline"><label>Description*</label></li>
                <li class="inline"><textarea name="description" rows="4" cols="45" required></textarea><br></li>
            </ul>
            <ul>
                <li class="inline "></li>
                <li class="inline"> <input type="submit" class="button" value="Submit" name="Submit" /></li>
            </ul>
        </form>
<jsp:include page="footer.jsp" />
