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
import personal.votingsystem.model.User;
import personal.votingsystem.service.IUserService;
import personal.votingsystem.service.UserServiceImpl;
import personal.votingsystem.service.exceptions.UserNotFoundException;

import java.io.IOException;

@WebServlet("/user/view-vote")
public class ViewVoteController extends HttpServlet {
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = null;
        Boolean hasAlreadyVoted = false;

        if (session != null) {
            username = (String) session.getAttribute("username");
            hasAlreadyVoted = (Boolean) session.getAttribute("hasVoted");
        }

        if (!hasAlreadyVoted) {
            sendNoVoteReply(request, response);
            return;
        }

        try {
            User user = userService.getUserByUsername(username);
            request.setAttribute("votedCid", user.getVotedCid());
            request.getRequestDispatcher("/WEB-INF/jsp/view-vote.jsp").forward(request, response);
        } catch (UserNotFoundException | UserDAOException e) {
            e.printStackTrace();
        }
    }

    private void sendNoVoteReply(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/view-no-vote.jsp").forward(request, response);
    }
}
