<%-- 
    Document   : signup
    Created on : Sep 19, 2015, 1:10:19 AM
    Author     : Pinkesh
--%>

<jsp:include page="header.jsp" />

<form action="controller?action=create" method="POST" id="signupForm" class="signup_center">
    <div class="pad_3 top_Box">${msg}</div>
    <ul>
        <li class="inline"><label >Name*</label></li>
        <li class="inline"><input class="login_input" placeholder="Name" type="text" size="40" name="name" value="${param.name}" required /><br></li>
    </ul>
    <ul>
        <li class="inline"><label >Email*</label></li>
        <li class="inline"><input class="login_input" placeholder="Email" type="email" size="40" name="email" value="${param.email}" required /><br></li>
    </ul>
    <ul>
        <li class="inline"><label>Password*</label></li>
        <li class="inline"><input id="password" class="login_input" placeholder="Password" type="password" size="40" name="password" required /><br></li>
    </ul>
    <ul>
        <li class="inline"><label>Confirm Password*</label></li>
        <li class="inline"><input id="cnfpassword" class="login_input" placeholder="Confirm Password" type="password" size="40" name="confirm_password" required /><br></li>
    </ul>
    <ul>
        <li class="inline "></li>
        <li class="inline"> <input type="submit" class="button" onclick="submitSignupForm('signupForm');return false;" value="Create Account" name="signup" /></li>
    </ul>
</form>
<jsp:include page="footer.jsp" />