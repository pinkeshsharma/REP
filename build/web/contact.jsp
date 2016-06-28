<%-- 
    Document   : contact
    Created on : Sep 26, 2015, 9:06:16 PM
    Author     : Dimple
--%>

<jsp:include page="header.jsp" />
<div class="inline">
    <div class="top_page">
        <span class="bck_grey"> Contact </span>
    </div>
    <div class="top_breadcrum">
        <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
    </div>
</div>
        <form action="<%=request.getContextPath() + "/studycontroller?action=confirmc"%>" method="POST" class="signup_center">
            <ul>
                <li class="inline"><label >Name*</label></li>
                <li class="inline"><input class="login_input" placeholder="Name" type="text" size="40" name="name" required /><br></li>
            </ul>
            <ul>
                <li class="inline"><label >Email*</label></li>
                <li class="inline"><input class="login_input" placeholder="Email" type="email" size="40" name="email" required /><br></li>
            </ul>
            <ul>
                <li class="inline"><label>Message*</label></li>
                <li class="inline"><textarea name="message" rows="4" cols="45" required></textarea><br></li>
            </ul>
            <ul>
                <li class="inline "></li>
                <li class="inline"> <input type="submit" class="button" value="Submit" name="Submit" /></li>
            </ul>
        </form>
<jsp:include page="footer.jsp" />
