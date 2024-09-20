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
import personal.votingsystem.dto.ChangePasswordDTO;
import personal.votingsystem.dto.ChangePasswordErrorsDTO;
import personal.votingsystem.model.User;
import personal.votingsystem.service.IUserService;
import personal.votingsystem.service.UserServiceImpl;
import personal.votingsystem.service.exceptions.UserNotFoundException;
import personal.votingsystem.service.exceptions.WrongPasswordException;
import personal.votingsystem.validator.Validator;

import java.io.IOException;
import java.util.Map;

@WebServlet("/user/change-password")
public class ChangePasswordController extends HttpServlet {
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/change-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) return;
        String username = (String) session.getAttribute("username");

        ChangePasswordDTO changePasswordDTO = createChangePasswordDTO(request);

        ChangePasswordErrorsDTO changePasswordErrorsDTO = new ChangePasswordErrorsDTO();
        Map<String, String> errors;

        //Validation
        errors = Validator.validate(changePasswordDTO);
        if (!errors.isEmpty()) {
            String newPasswordError = errors.getOrDefault("password", "");
            String reEnteredPasswordError = errors.getOrDefault("reEnteredPassword", "");
            changePasswordErrorsDTO.setNewPasswordError(newPasswordError);
            changePasswordErrorsDTO.setReEnteredPasswordError(reEnteredPasswordError);
            sendResponse(request, response, changePasswordDTO, changePasswordErrorsDTO);
            return;
        }

        try {
            User user = mapToUser(username);
            userService.changePassword(user, changePasswordDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/change-password-success.jsp").forward(request, response);
        } catch (UserNotFoundException | UserDAOException | WrongPasswordException e) {
            changePasswordErrorsDTO.setCurrentPasswordError(e.getMessage());
            sendResponse(request, response, changePasswordDTO, changePasswordErrorsDTO);
        }
    }

    private User mapToUser(String username) {
        User user = null;
        try {
            user = userService.getUserByUsername(username);
        } catch (UserDAOException | UserNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    private void sendResponse(HttpServletRequest request, HttpServletResponse response,
                              ChangePasswordDTO changePasswordDTO, ChangePasswordErrorsDTO changePasswordErrorsDTO)
            throws ServletException, IOException {
        request.setAttribute("changePasswordDTO", changePasswordDTO);
        request.setAttribute("errorsDTO", changePasswordErrorsDTO);
        request.getRequestDispatcher("/WEB-INF/jsp/change-password.jsp").forward(request, response);
    }

    private ChangePasswordDTO createChangePasswordDTO(HttpServletRequest request) {
        String currentPassword = request.getParameter("currentPassword").trim();
        String newPassword = request.getParameter("newPassword").trim();
        String reEnteredPassword = request.getParameter("reEnteredPassword").trim();

        return new ChangePasswordDTO(currentPassword, newPassword, reEnteredPassword);
    }
}
