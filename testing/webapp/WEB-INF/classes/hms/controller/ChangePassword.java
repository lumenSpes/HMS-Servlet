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

public class ChangePassword extends HttpServlet {
    private static final Logger logger =Logger.getLogger(ChangePassword.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Change Password");
        RequestDispatcher requestDispatcherLg = req.getRequestDispatcher("views/changepassword.jsp");
        requestDispatcherLg.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentpassword = req.getParameter("currentpassword");
        String username = (String) req.getSession().getAttribute("username");
        String password = req.getParameter("password");
        String confirmpassword = req.getParameter("confirmpassword");
        String age = req.getParameter("age");
        boolean flag = true;





        if (password == null || password.isEmpty()) {
            req.setAttribute("passwordErrMsg", "Please fill up the password");
            flag = false;
        }

        if (confirmpassword == null || confirmpassword.isEmpty()) {
            req.setAttribute("confirmpasswordErrMsg", "Please fill up the confirmpassword");
            flag = false;
        }

        if (currentpassword == null || currentpassword.isEmpty()) {
            req.setAttribute("currentpasswordMsg", "Please fill up the current password");
            flag = false;
        }

        if (!password.equals(confirmpassword)) {
            req.setAttribute("passMismatchErrMsg", "Please retype the password");
            flag = false;
        }

        if (flag) {
            try {
                UserRepository userRepository = new UserRepository();
                boolean isValid = userRepository.login(username, currentpassword, req);
                if (isValid) {
//                    HttpSession httpSession = req.getSession();
//                    httpSession.setAttribute("username", username);
//                    resp.sendRedirect(req.getContextPath() + "/app/home");
////                    HttpSession httpSession = req.getSession();
//                    httpSession.setAttribute("successMsg", "successMsg");
//                    req.setAttribute("successMsg", "User successfully registerd...");
//                    return;
                    System.out.println("after login");
                    boolean isUpdate = userRepository.updatePassword(password,confirmpassword,username);
                    if(isUpdate){
                        System.out.println("after update");
                        req.setAttribute("successMsg", "Password successfully updated...");
                    }
                }

                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("successMsg", "successMsg");
                req.setAttribute("successMsg", "Password successfully updated...");
            } catch (NamingException var9) {
                logger.log(Level.SEVERE, "Naming exception occurred while accessing UserRepository");
                logger.log(Level.SEVERE, var9.getMessage());
            } catch (SQLException var10) {
                logger.log(Level.SEVERE, "SQL exception occurred while accessing UserRepository");
                logger.log(Level.SEVERE, var10.getMessage());
            }
        }


        req.setAttribute("currentpassword", currentpassword);
        req.setAttribute("password", password);
        req.setAttribute("confirmpassword", confirmpassword);



        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/changepassword.jsp");
        requestDispatcher.forward(req, resp);
    }
}
