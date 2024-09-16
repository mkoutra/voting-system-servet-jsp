package personal.votingsystem.service;

import org.mindrot.jbcrypt.BCrypt;
import personal.votingsystem.dao.CandidateDAOImpl;
import personal.votingsystem.dao.ICandidateDAO;
import personal.votingsystem.dao.IUserDAO;
import personal.votingsystem.dao.exceptions.CandidateDAOException;
import personal.votingsystem.dao.exceptions.UserDAOException;
import personal.votingsystem.dto.old.CandidateReadOnlyDTO;
import personal.votingsystem.dto.old.ChangePasswordDTO;
import personal.votingsystem.dto.UserInsertDTO;
import personal.votingsystem.dto.UserReadOnlyDTO;
import personal.votingsystem.model.User;
import personal.votingsystem.service.exceptions.CandidateNotFoundException;
import personal.votingsystem.service.exceptions.UserNotFoundException;
import personal.votingsystem.service.exceptions.WrongPasswordException;
import personal.votingsystem.service.util.ConfigFileUtil;

import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author Michail E. Koutrakis
 */
public class UserServiceImpl implements IUserService {
    private final IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User insertUser(UserInsertDTO dto) throws UserDAOException {
        User user = mapUserInsertDTOToUser(dto);
        userDAO.insert(user);
        return user;
    }

    @Override
    public boolean userExistsByUsername(String username) throws UserDAOException {
        return userDAO.usernameExists(username);
    }

    @Override
    public boolean userExistsByEmail(String email) throws UserDAOException {
        return userDAO.emailExists(email);
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException, UserDAOException {
        try {
            if (!userDAO.usernameExists(username)) {
                throw new UserNotFoundException("Username: " + username + " does not exist.");
            }
            return userDAO.getByUsername(username);
        } catch (UserDAOException e1) {
            throw new UserDAOException("SQL error in get user by username: " + username);
        }
    }

    @Override
    public boolean authenticateUser(String username, String plainPassword)
            throws UserNotFoundException, UserDAOException {
        if (!userDAO.usernameExists(username)) {
            throw new UserNotFoundException("Username: " + username + " does not exist.");
        }
        User user = userDAO.getByUsername(username);
        return BCrypt.checkpw(plainPassword, user.getPassword());
    }

    @Override
    public void voteACandidate(UserReadOnlyDTO userReadOnlyDTO, CandidateReadOnlyDTO candidateReadOnlyDTO)
            throws CandidateNotFoundException, UserNotFoundException, UserDAOException, CandidateDAOException {
        ICandidateDAO candidateDAO = new CandidateDAOImpl();
        try {
            if (!userDAO.usernameExists(userReadOnlyDTO.getUsername())) {
                throw new UserNotFoundException("User: " + userReadOnlyDTO.getUsername() + " does not exist.");
            }
            if (!candidateDAO.cidExists(candidateReadOnlyDTO.getCid())) {
                throw new CandidateNotFoundException("Candidate with id: " + candidateReadOnlyDTO.getCid() + " does not exist.");
            }

            User voter = userDAO.getByUsername(userReadOnlyDTO.getUsername());
            voter.setHasVoted(1);
            voter.setVotedCid(candidateReadOnlyDTO.getCid());
            userDAO.update(voter);
        } catch (CandidateDAOException | UserDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean checkIfUserHasVoted(String username) throws UserNotFoundException, UserDAOException {
        if (!userDAO.usernameExists(username)) {
            throw new UserNotFoundException("Username: " + username + " does not exist.");
        }
        User user = userDAO.getByUsername(username);
        return user.getHasVoted() == 1;
    }

    private static User mapUserInsertDTOToUser(UserInsertDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setDob(dto.getDateOfBirth());
        user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12)));
        user.setHasVoted(0);
        return user;
    }

    /**
     * Returns empty list if there are no users
     *
     * @return
     * @throws UserDAOException
     */
    @Override
    public List<User> getAllUsers() throws UserDAOException {
        return userDAO.getAllUsers();
    }

    /**
     * Returns empty list if there are no users
     *
     * @param votedCid
     * @return
     * @throws UserDAOException
     */
    @Override
    public List<User> getAllUsersByVotedCid(Integer votedCid)
            throws UserDAOException, CandidateDAOException, CandidateNotFoundException {
        ICandidateDAO candidateDAO = new CandidateDAOImpl();

        if (!candidateDAO.cidExists(votedCid)) {
            throw new CandidateNotFoundException("Candidate with id: " + votedCid + " was not found.");
        }

        return userDAO.getUsersByVotedCid(votedCid);
    }

    @Override
    public void removeAllVotesOfSpecificCid(Integer votedCid)
            throws UserDAOException, CandidateDAOException, CandidateNotFoundException {
        ICandidateDAO candidateDAO = new CandidateDAOImpl();
        if (!candidateDAO.cidExists(votedCid)) {
            throw new CandidateNotFoundException("Candidate with id: " + votedCid + " was not found.");
        }
        userDAO.removeAllVotesOfSpecificCid(votedCid);
    }

    @Override
    public User changePassword(User user, ChangePasswordDTO dto)
            throws UserNotFoundException, WrongPasswordException, UserDAOException {
        if (!userDAO.usernameExists(user.getUsername())) {
            throw new UserNotFoundException("User: " + user.getUsername() + " was not found.");
        }

        if (!authenticateUser(user.getUsername(), dto.getCurrentPassword())) {
            throw new WrongPasswordException("Wrong password given by the user");
        }

        user.setPassword(BCrypt.hashpw(dto.getNewPassword(), BCrypt.gensalt(12)));

        try {
            userDAO.update(user);
            return user;
        } catch (UserDAOException e) {
            user.setPassword(BCrypt.hashpw(dto.getCurrentPassword(), BCrypt.gensalt(12)));
            throw new UserDAOException("SQL error in change Password");
        }
    }

    @Override
    public void createAdminAccount() throws UserDAOException {
        UserInsertDTO insertDTO = createAdminInsertDTO();
        insertUser(insertDTO);
    }

    /**
     * Reads the admin information from config.properties file
     * and returns an UserInsertDTO object.
     *
     * @return The UserInsertDTO with the admin information
     */
    private UserInsertDTO createAdminInsertDTO() {
        UserInsertDTO insertDTO = new UserInsertDTO();
        Properties props = ConfigFileUtil.getPropertiesInstance();

        insertDTO.setUsername("admin");
        insertDTO.setEmail(props.getProperty("admin.email"));
        insertDTO.setFirstname(props.getProperty("admin.firstname"));
        insertDTO.setLastname(props.getProperty("admin.lastname"));
        insertDTO.setDateOfBirth(new Date(System.currentTimeMillis()));
        insertDTO.setPassword(props.getProperty("admin.password"));
        insertDTO.setReEnteredPassword(props.getProperty("admin.initial_password"));

        return insertDTO;
    }
}