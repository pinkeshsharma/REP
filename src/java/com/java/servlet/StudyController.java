/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.servlet;

import com.java.dao.StudyDB;
import com.java.dao.UserDB;
import com.java.model.Study;
import com.java.model.User;
import com.java.util.EmailUtil;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Pinkesh
 */
@WebServlet("/studycontroller")
public class StudyController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = (String) request.getParameter("action");
        User user = (User) session.getAttribute("User");
        StudyDB studyDB = new StudyDB();
        UserDB userDB = new UserDB();

        String url;
        if (action == null) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "main.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("participate_pg".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "participate.jsp";
                session.setAttribute("StudiesParticipate", studyDB.getStudiesParticipate(user.getEmail()));
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("participate".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "question.jsp";
            }
            String studyCode = request.getParameter("studyCode");
            Study study = studyDB.getStudy(studyCode);
            session.setAttribute("Study", study);

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("participatedStudy".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            url = "questionDetail.jsp";
            String studyCode = request.getParameter("studyCode");
            Study study = studyDB.getStudyDetail(studyCode);
            session.setAttribute("Study", study);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("studies".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "studies.jsp";
                session.setAttribute("Studies", studyDB.getStudies(user.getEmail()));
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("newstudy_pg".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "newstudy.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("newstudy".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyyyhhmmss");
                url = "studycontroller?action=studies";
                String studyname = request.getParameter("studyname");
                String questiontext = request.getParameter("questiontext");
                String image_upload = String.valueOf(session.getAttribute("newImageUploadFilePath"));
                String participants = request.getParameter("participants");
                String description = request.getParameter("description");

                Study study = new Study(questiontext, "studyname" + sdf.format(new Date()), studyname, new Date(), user.getEmail(), image_upload, participants, "0", description, "Stop", null);
                int status = studyDB.addStudy(study);
                if (status == 1) {
                    session.setAttribute("Studies", studyDB.getStudies(user.getEmail()));
                    request.setAttribute("message", "New study added successfully");
                } else {
                    request.setAttribute("message", "Exception in adding a new study. Please contact administrator.");
                }
            }
            session.setAttribute("newImageUploadFilePath", null);
            response.sendRedirect(url);
            /**
             * RequestDispatcher rd = request.getRequestDispatcher(url);
             * rd.sendRedirect(request, response);*
             */
        } else if ("editstudy_pg".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "editstudy.jsp";

                session.setAttribute("Study", studyDB.getStudy(request.getParameter("studyCode")));
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("editstudy".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "studycontroller?action=studies";
                Study study = new Study();
                study.setStudyCode(request.getParameter("studyCode"));
                study.setName(request.getParameter("studyname"));
                study.setQuestion(request.getParameter("questiontext"));
                if (session.getAttribute("newImageUploadFilePath") != null) {
                    study.setImageURL(String.valueOf(session.getAttribute("newImageUploadFilePath")));
                }
                study.setRequestedparticipants(request.getParameter("participants"));
                study.setDescription(request.getParameter("description"));

                int status = studyDB.editStudy(study);
                request.setAttribute("message", "Study updated successfully");
            }
            session.setAttribute("newImageUploadFilePath", null);
            response.sendRedirect(url);
            /**
             * RequestDispatcher rd = request.getRequestDispatcher(url);
             * rd.forward(request, response);*
             */
        } else if ("stop".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "studycontroller?action=studies";
                String studyCode = request.getParameter("studyCode");
                Study study = studyDB.getStudy(studyCode);
                study.setStatus("Stop");
                int status = studyDB.editStatus(study);
                session.setAttribute("Studies", studyDB.getStudies(user.getEmail()));
            }
            response.sendRedirect(url);
            /**
             * RequestDispatcher rd = request.getRequestDispatcher(url);
             * rd.forward(request, response);*
             */
        } else if ("start".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "studycontroller?action=studies";
                String studyCode = request.getParameter("studyCode");
                Study study = studyDB.getStudy(studyCode);
                study.setStatus("Start");
                int status = studyDB.editStatus(study);
                session.setAttribute("Studies", studyDB.getStudies(user.getEmail()));
            }
            response.sendRedirect(url);
            /**
             * RequestDispatcher rd = request.getRequestDispatcher(url);
             * rd.forward(request, response);*
             */
        } else if ("answer".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "participate.jsp";

                String studyCode = request.getParameter("studyCode");
                String answer = request.getParameter("answer");
                String email = user.getEmail();

                int status = studyDB.submitAnswer(studyCode, answer, email);
                if (status == 1) {
                    studyDB.updateParticipation(email);
                    session.setAttribute("User", userDB.getUser(email));
                    session.setAttribute("StudiesParticipate", studyDB.getStudiesParticipate(user.getEmail()));
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("recommend".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "recommend.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("confirmr".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "confirmr.jsp";
                try {
                    String name = request.getParameter("name");
                    String email = request.getParameter("friends_email");
                    String message = request.getParameter("message");
                    String signupURL = "  http://openshiftdemo-pinkeshsharma.rhcloud.com/REP/controller?action=signup&refferalCode=" + user.getReferralCode();
                    EmailUtil.sendEmail(email, "Recommendation to use REP from: " + name, message + signupURL);
                    request.setAttribute("msg", "Recommendation sent successfully");
                } catch (Exception ex) {
                    request.setAttribute("msg", "Unable to send recommendation. Please try again after some time");
                    Logger.getLogger(StudyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("contact".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "contact.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("confirmc".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "confirmc.jsp";
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String message = request.getParameter("message");
                try {
                    EmailUtil.sendEmail(email, "Message from: " + name + ". Sent using REP", message);
                    request.setAttribute("msg", "Message sent successfully");
                } catch (Exception ex) {
                    request.setAttribute("msg", "Unable to send message. Please try again after some time");
                    Logger.getLogger(StudyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("open".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "image.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "main.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
