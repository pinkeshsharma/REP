<%-- 
    Document   : confirmr
    Created on : Sep 26, 2015, 1:11:57 AM
    Author     : Dimple
--%>

<jsp:include page="header.jsp" />
<div class="inline">
    <div class="top_breadcrum">
        <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
    </div>
</div>
    <div class="centered">
        <b>${msg} ...</b>
    </div>
<jsp:include page="footer.jsp" />
