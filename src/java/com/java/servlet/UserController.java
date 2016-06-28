/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.servlet;

import com.java.dao.UserDB;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.java.model.User;
import com.java.util.EmailUtil;
import com.java.util.HashUtil;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

/**
 *
 * @author Pinkesh
 */
@WebServlet("/controller")
public class UserController extends HttpServlet {

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
        UserDB userDB = new UserDB();
        String url;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase("Remote_port")) {
                session.setAttribute("Remote_port", cookie.getValue());
            }
            if (cookie.getName().equalsIgnoreCase("Remote_host")) {
                session.setAttribute("Remote_host", cookie.getValue());
            }
        }

        if (action == null) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "home.jsp";
            } else {
                url = "main.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("login_pg".equalsIgnoreCase(action) && user == null) {
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } else if ("login".equalsIgnoreCase(action)) {
            if (user != null) {
                url = "main.jsp";
            } else {
                String email = String.valueOf(request.getParameter("email"));
                String password = String.valueOf(request.getParameter("password"));               
                User loggedInUser = userDB.getUser(email, password);
                if (loggedInUser != null) {
                    url = "main.jsp";
                    session.setAttribute("User", loggedInUser);
                } else {
                    request.setAttribute("msg", "Invalid username and/or password.");
                    url = "login.jsp";
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("signup".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "signup.jsp";
            } else {
                url = "main.jsp";
            }
            String refferalCode = String.valueOf(request.getParameter("refferalCode"));
            session.setAttribute("refferalCode",refferalCode);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("activate".equalsIgnoreCase(action)) {
           response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
                String token = String.valueOf(request.getParameter("token"));
                if(token != null){
                    int status = userDB.activateUser(token);
                    if(status == 1){
                        request.setAttribute("msg", "Your account has been activated successfully.");
                    } else {
                        request.setAttribute("msg", "Unable to activate your account, please try again later.");
                    }
                }
            } else {
                url = "main.jsp"; 
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("create".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user != null) {
                url = "main.jsp";
            } else {
                String name = String.valueOf(request.getParameter("name"));
                String email = String.valueOf(request.getParameter("email"));
                String password = String.valueOf(request.getParameter("password"));
                String refferalCode = String.valueOf(session.getAttribute("refferalCode"));
                session.removeAttribute("refferalCode");
                String activationURL = "";
                if (userDB.emailExists(email)) {
                    url = "signup.jsp";
                    request.setAttribute("msg", "Email already exists!");
                } else {
                    int status = 0;
                    try {
                        String salt = HashUtil.getSalt(128);
                        String passwordHex = HashUtil.getHash(password, salt);
                        String refferalCodeUser = HashUtil.getHash(email);
                        String actToken = HashUtil.getRandomString(50);
                        activationURL = "http://openshiftdemo-pinkeshsharma.rhcloud.com/REP/controller?action=activate&token=" + actToken;
                        status = userDB.createUser(name, email, passwordHex, salt,refferalCodeUser, actToken);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    
                    if (status == 1) {
                        url = "signup.jsp";
                        status = userDB.updateReferral(refferalCode);
                        request.setAttribute("msg", "An email has been sent to your email id, Please activate your account to login.");
                        EmailUtil.sendEmail(email, name + ", Please activate your Research Partcipate Exchange Account"
                                , "Hi, Please use the URL to activate your account : " + activationURL);
                    } else {
                        url = "signup.jsp";
                        request.setAttribute("msg", "Unable to create account, Please try again after sometime.");
                    }
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("how".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "how.jsp";
            } else {
                url = "main.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("about".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "about.jsp";
            } else {
                url = "aboutl.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("home".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "home.jsp";
            } else {
                url = "main.jsp";
            }

            String remoteHost = request.getRemoteHost();
            int remotePort = request.getRemotePort();
            Cookie cookie1 = new Cookie("Remote_host", remoteHost);
            cookie1.setMaxAge(1209600);
            response.addCookie(cookie1);
            Cookie cookie2 = new Cookie("Remote_port", String.valueOf(remotePort));
            cookie2.setMaxAge(1209600);
            response.addCookie(cookie2);

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("main".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "main.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("logout".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "home.jsp";
            }
            session.invalidate();
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "home.jsp";
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
