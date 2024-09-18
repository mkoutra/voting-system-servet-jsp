package personal.votingsystem.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import personal.votingsystem.dao.CandidateDAOImpl;
import personal.votingsystem.dao.ICandidateDAO;
import personal.votingsystem.dao.exceptions.CandidateDAOException;
import personal.votingsystem.dto.CandidateInsertDTO;
import personal.votingsystem.dto.CandidateReadOnlyDTO;
import personal.votingsystem.service.CandidateServiceImpl;
import personal.votingsystem.service.ICandidateService;
import personal.votingsystem.validator.Validator;

import java.io.IOException;
import java.util.Map;

@WebServlet("/voting/candidate/insert")
public class InsertCandidateController extends HttpServlet {
    private final ICandidateDAO candidateDAO = new CandidateDAOImpl();
    private final ICandidateService candidateService = new CandidateServiceImpl(candidateDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/insert-candidate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CandidateInsertDTO candidateInsertDTO = createCandidateInsertDTO(request);
        Map<String, String> errors;

        // Validation
        errors = Validator.validate(candidateInsertDTO);
        if (!errors.isEmpty()) {
            String firstnameErrorMessage = errors.getOrDefault("firstname", "");
            String lastnameErrorMessage = errors.getOrDefault("lastname", "");

            request.setAttribute("firstnameErrorMessage", firstnameErrorMessage);
            request.setAttribute("lastnameErrorMessage", lastnameErrorMessage);
            request.getRequestDispatcher("/WEB-INF/jsp/insert-candidate.jsp").forward(request, response);
            return;
        }

        try {
            candidateService.insertCandidate(candidateInsertDTO);
            CandidateReadOnlyDTO candidateReadOnlyDTO = mapToCandidateReadOnlyDTO(candidateInsertDTO);
            request.setAttribute("candidateReadOnlyDTO", candidateReadOnlyDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/insert-candidate-success.jsp").forward(request, response);
        } catch (CandidateDAOException e) {
            e.printStackTrace();
        }
    }

    private CandidateReadOnlyDTO mapToCandidateReadOnlyDTO(CandidateInsertDTO dto) {
        return new CandidateReadOnlyDTO(null, dto.getFirstname(), dto.getLastname());
    }

    private CandidateInsertDTO createCandidateInsertDTO(HttpServletRequest request) {
        String firstname = request.getParameter("firstname").trim();
        String lastname = request.getParameter("lastname").trim();
        return new CandidateInsertDTO(firstname, lastname);
    }
}
