<%-- 
    Document   : sidemenu
    Created on : Sep 21, 2015, 3:26:02 AM
    Author     : Pinkesh
--%>

<div>
    <ul class="side_menu_items">
        <%-- TO DO --%>
        <li><a href="">Coins (${User.coins})</a></li>
        <li><a href="">Participants (${User.participants})</a></li>
        <li><a href="">Participation (${User.participation})</a></li>
        <li>.</li>
        <li><a href="<%=request.getContextPath() + "/controller?action=home"%>">Home</a></li>
        <li><a href="<%=request.getContextPath() + "/studycontroller?action=participate_pg"%>">Participate</a></li>
        <li><a href="<%=request.getContextPath() + "/studycontroller?action=studies"%>">My Studies</a></li>
        <li><a href="<%=request.getContextPath() + "/studycontroller?action=recommend"%>">Recommend</a></li>
        <li><a href="<%=request.getContextPath() + "/studycontroller?action=contact"%>">Contact</a></li>
        <li><a href="<%=request.getContextPath() + "/controller?action=logout"%>">Logout</a></li>
    </ul>
</div>
