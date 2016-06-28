<%@page import="java.util.List"%>
<%@page import="com.java.model.Study"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : studies
    Created on : Sep 26, 2015, 1:23:15 AM
    Author     : Pinkesh
--%>

<jsp:include page="header.jsp" />
<div id="fb-root"></div>
<script>(function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id))
            return;
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));</script>
<div class="inline">
    <div class="top_page">
        <span class="bck_grey"> My Studies </span>
    </div>
    <div class="top_breadcrum">
        <div class="width_100"><a href="<%=request.getContextPath() + "/studycontroller?action=newstudy_pg"%>" class="text_150">Add a new study</a></div>
        <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
    </div>
</div>
<div class="centered_1">
    <div class="msg_div">${message}</div>
    <form method="POST" id="editStudyForm" action="<%=request.getContextPath() + "/studycontroller?action=editstudy_pg"%>">
        <input id="studyCode" name="studyCode" type="hidden"/>
        <table class="display table">
            <thead><tr><th>Study Name</th><th>Requested Participants</th><th>Participations</th><th>Status</th><th>Action</th></tr></thead>
            <tfoot>
                <%
                    List<Study> studies = (List) session.getAttribute("Studies");
                    if (studies != null && studies.size() > 0) {
                        for (Study study : studies) {
                %>

                <tr>
                    <td><%=study.getName()%></td>
                    <td><%=study.getRequestedparticipants()%></td>
                    <td><%=study.getNumOfParitipants()%></td>
                    <td>
                        <%
                            if ("Start".equalsIgnoreCase(study.getStatus())) {
                        %>
                        <button onclick="updateStatus('<%=study.getStudyCode()%>', '<%=request.getContextPath() + "/studycontroller?action=stop"%>')" class="button_study" id="st_button1" type="button">Stop</button>
                        <%
                        } else {
                        %>
                        <button onclick="updateStatus('<%=study.getStudyCode()%>', '<%=request.getContextPath() + "/studycontroller?action=start"%>')" class="button_study" id="st_button1" type="button">Start</button>
                        <%
                            }
                        %>
                    </td>
                    <td><button onclick="editStudy('<%=study.getStudyCode()%>', '<%=request.getContextPath() + "/studycontroller?action=editstudy_pg"%>')" class="button_study" type="button">Edit</button></td>
                    <td bgcolor="white">
                        <div class="fb-share-button" data-href="https://openshiftdemo-pinkeshsharma.rhcloud.com/REP/studycontroller?action=participatedStudy&studyCode=<%=study.getStudyCode()%>" data-layout="button"></div>    
                    </td>
                </tr>

                <%
                    }
                } else {
                %>

                <tr>
                    <td colspan="5">No records to display.</td>
                </tr>
                <tr>
                    <td colspan="5"></td>
                </tr>
                <%
                    }
                %>
            </tfoot>
        </table>
    </form>
</div>
<jsp:include page="footer.jsp" />
