package personal.votingsystem.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import personal.votingsystem.dao.CandidateDAOImpl;
import personal.votingsystem.dao.ICandidateDAO;
import personal.votingsystem.dao.exceptions.CandidateDAOException;
import personal.votingsystem.dto.CandidateReadOnlyDTO;
import personal.votingsystem.dto.old.CandidateUpdateDTO;
import personal.votingsystem.model.Candidate;
import personal.votingsystem.service.CandidateServiceImpl;
import personal.votingsystem.service.ICandidateService;
import personal.votingsystem.service.exceptions.CandidateNotFoundException;
import personal.votingsystem.validator.Validator;

import java.io.IOException;
import java.util.Map;

@WebServlet("/candidate/edit")
public class CandidateEditController extends HttpServlet {
    private final ICandidateDAO candidateDAO = new CandidateDAOImpl();
    private final ICandidateService candidateService = new CandidateServiceImpl(candidateDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CandidateUpdateDTO candidateUpdateDTO = createCandidateUpdateDTO(request);

        request.setAttribute("candidateUpdateDTO", candidateUpdateDTO);
        request.getRequestDispatcher("/WEB-INF/jsp/edit-candidate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CandidateUpdateDTO candidateUpdateDTO = createCandidateUpdateDTO(request);
        Map<String, String> errors;

        // Validation
        errors = Validator.validate(candidateUpdateDTO);
        if (!errors.isEmpty()) {
            String firstnameErrorMessage = errors.getOrDefault("firstname", "");
            String lastnameErrorMessage = errors.getOrDefault("lastname", "");

            request.setAttribute("candidateUpdateDTO", candidateUpdateDTO);
            request.setAttribute("firstnameErrorMessage", firstnameErrorMessage);
            request.setAttribute("lastnameErrorMessage", lastnameErrorMessage);
            request.getRequestDispatcher("/WEB-INF/jsp/edit-candidate.jsp").forward(request, response);
            return;
        }
        try {
            Candidate updatedCandidate = candidateService.updateCandidate(candidateUpdateDTO);
            CandidateReadOnlyDTO candidateReadOnlyDTO = new CandidateReadOnlyDTO(updatedCandidate.getCid(), updatedCandidate.getFirstname(), updatedCandidate.getLastname());
            request.setAttribute("candidateReadOnlyDTO", candidateReadOnlyDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/edit-candidate-success.jsp").forward(request, response);
        } catch (CandidateDAOException | CandidateNotFoundException e) {
            e.printStackTrace();
            String errorMessage = e.getMessage();
            request.setAttribute("candidateUpdateDTO", candidateUpdateDTO);
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/jsp/edit-candidate.jsp").forward(request,response);
        }
    }

    private CandidateUpdateDTO createCandidateUpdateDTO(HttpServletRequest request) {
        Integer cid = Integer.parseInt(request.getParameter("cid").trim());
        String firstname = request.getParameter("firstname").trim();
        String lastname = request.getParameter("lastname").trim();

        return new CandidateUpdateDTO(cid, firstname, lastname);
    }
}
