package personal.votingsystem.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import personal.votingsystem.dao.IUserDAO;
import personal.votingsystem.dao.UserDAOImpl;
import personal.votingsystem.dao.exceptions.UserDAOException;
import personal.votingsystem.dto.UserLoginDTO;
import personal.votingsystem.service.IUserService;
import personal.votingsystem.service.UserServiceImpl;
import personal.votingsystem.service.exceptions.UserNotFoundException;
import personal.votingsystem.validator.Validator;

import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserLoginDTO userLoginDTO = createUserLoginDTO(request);
        Map<String, String> errors;

        // Validation
        errors = Validator.validate(userLoginDTO);
        if (!errors.isEmpty()) {
            sendErrorMessage(request, response, userLoginDTO);
            return;
        }

        try {
            // Authentication
            boolean isAuthenticated = false;
            isAuthenticated = userService.authenticateUser(userLoginDTO.getUsername(), userLoginDTO.getPassword());
            if (isAuthenticated) {
                // Write to session object
                HttpSession session = request.getSession(false);
                session.setAttribute("username", userLoginDTO.getUsername());

                response.sendRedirect(request.getContextPath() + "/voting");
            } else {
                sendErrorMessage(request, response, userLoginDTO);
            }
        } catch (UserNotFoundException | UserDAOException e) {
            sendErrorMessage(request, response, userLoginDTO);
        }
    }

    private void sendErrorMessage(HttpServletRequest request, HttpServletResponse response, UserLoginDTO userLoginDTO)
            throws ServletException, IOException {
        String loginErrorMessage = "Invalid Credentials.";

        request.setAttribute("loginDTO", userLoginDTO);
        request.setAttribute("loginErrorMessage", loginErrorMessage);
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    private UserLoginDTO createUserLoginDTO(HttpServletRequest request) {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        return new UserLoginDTO(username, password);
    }
}
