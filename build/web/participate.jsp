<%@page import="java.util.List"%>
<%@page import="com.java.model.Study"%>
<%-- 
    Document   : participate
    Created on : Sep 25, 2015, 1:58:43 AM
    Author     : Dimple
--%>

<jsp:include page="header.jsp" />
<div class="side_left">
    <jsp:include page="sidemenu.jsp" />
</div>
<div class="side_right">
    <div class="inline">
        <div class="top_page bck_grey">
            <span> Studies </span>
        </div>
    </div>
    <div>
        <form method="POST" id="parcipateForm" action="<%=request.getContextPath() + "/studycontroller?action=participate"%>">
            <input name="studyCode" id="studyCode" type="hidden" value="" />
            <table class="table_1">
                <thead><tr><th>Study Name</th><th>Image</th><th>Question</th><th>Action</th></tr></thead>
                <tfoot>
                    <% List<Study> studies = (List) session.getAttribute("StudiesParticipate");
                        if (studies != null && studies.size() > 0) {
                            for (Study study : studies) {
                                if (study.getStatus().equalsIgnoreCase("Start")) {

                    %>

                    <tr>
                        <td><%=study.getName()%></td>
                        <td><div><img alt="Study Image" class="div_image1" src="<%=request.getContextPath() + "/uploadImage?filename=" + study.getImageURL()%>"></div></td>
                        <td><%=study.getQuestion()%></td>
                        <td><button onclick="submitForm('<%=study.getStudyCode()%>')" type="button">Participate</button></td>  
                    </tr>
                    <%
                            }
                        }
                    } else {
                    %>

                    <tr>
                        <td colspan="4">No records to display.</td>
                    </tr>

                    <%
                        }
                    %>
                </tfoot>
            </table>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp" />
