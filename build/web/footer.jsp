<%-- 
    Document   : home
    Created on : Sep 18, 2015, 5:27:34 PM
    Author     : Pinkesh
--%>

<div class="bottom">

    <span>© Researchers Exchange Participations   <% if(session.getAttribute("Remote_port") != null){%>  Port : ${Remote_port}<%} if(session.getAttribute("Remote_host") != null){%> Host : ${Remote_host} <%}%></span>
</div>
</body>
</html>
