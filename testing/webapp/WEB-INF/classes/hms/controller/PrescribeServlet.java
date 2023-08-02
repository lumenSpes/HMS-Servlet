package hms.controller;

import hms.repository.UserRepository;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrescribeServlet extends HttpServlet {
    private static final Logger logger =Logger.getLogger(RegisterServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title","Prescribe");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/prescribe.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String username = req.getParameter("username");
        String med1 = req.getParameter("med1");
        String med2 = req.getParameter("med2");
        String med3 = req.getParameter("med3");
        String med4 = req.getParameter("med4");
        String med5 = req.getParameter("med5");
        boolean flag = true;
        boolean isUser = true;

        if (firstname == null || firstname.isEmpty()) {
            req.setAttribute("firstnameErrMsg", "Please fill up the Medicine name");
            flag = false;
        }

        if (lastname == null || lastname.isEmpty()) {
            req.setAttribute("lastnameErrMsg", "Please fill up the Medicine name");
            flag = false;
        }

        if (username == null || username.isEmpty()) {
            req.setAttribute("usernameErrMsg", "Please fill up the Medicine name");
            flag = false;
        }

        if (med1 == null || med1.isEmpty()) {
            req.setAttribute("med1ErrMsg", "Please fill up the Medicine name");
            flag = false;
        }

        if (med2 == null || med2.isEmpty()) {
            req.setAttribute("med2ErrMsg", "Please fill up the Medicine name");
            flag = false;
        }

        if (med3 == null || med3.isEmpty()) {
            req.setAttribute("med3ErrMsg", "Please fill up the Medicine name");
            flag = false;
        }

        if (med4 == null || med4.isEmpty()) {
            req.setAttribute("med4ErrMsg", "Please fill up the Medicine name");
            flag = false;
        }

        if (med5 == null || med5.isEmpty()) {
            req.setAttribute("med5ErrMsg", "Please fill up the Medicine name");
            flag = false;
        }



        if (flag) {
            try {
                UserRepository userRepository = new UserRepository();
                boolean isValid = userRepository.prescribeMedicine(firstname, lastname, username, med1, med2, med3, med4, med5);
                if (isValid) {
                    HttpSession httpSession = req.getSession();

                    resp.sendRedirect(req.getContextPath() + "/home");
//                    HttpSession httpSession = req.getSession();
                    httpSession.setAttribute("successMsg", "successMsg");
                    req.setAttribute("successMsg", "Prescription Added...");
                    return;
                }
                isUser = true;
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("successMsg", "successMsg");
                req.setAttribute("successMsg", "Prescription Added...");
            } catch (NamingException var9) {
                logger.log(Level.SEVERE, "Naming exception occurred while accessing UserRepository");
                logger.log(Level.SEVERE, var9.getMessage());
            } catch (SQLException var10) {
                logger.log(Level.SEVERE, "SQL exception occurred while accessing UserRepository");
                logger.log(Level.SEVERE, var10.getMessage());
            }
        }

        req.setAttribute("firstname", firstname);
        req.setAttribute("lastname", lastname);
        req.setAttribute("username", username);
        req.setAttribute("med1", med1);
        req.setAttribute("med2", med2);
        req.setAttribute("med3", med3);
        req.setAttribute("med4", med4);
        req.setAttribute("med5", med5);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/prescribe.jsp");
        requestDispatcher.forward(req, resp);
    }
}
