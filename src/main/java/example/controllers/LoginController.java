package example.controllers;

import example.helpers.PasswordAuthentication;
import example.models.UserAccount;
import example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

//TODO: use password encryption after basic functionality is done
@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@SessionAttributes("currentUser")
public class LoginController {

    UserService userService;

    /**
     * Checks incoming user and if it exists, we set the session attribute to that user.
     * @param session current session
     * @param currentUser user object sent through the http request
     * @return Either a String if the login was unsuccessful, or the User object that was logged in
     */
    @PostMapping(value = "/login")
    public Object login(HttpSession session, @RequestBody UserAccount currentUser) {

        System.out.println("login detected");

        PasswordAuthentication authorizer = new PasswordAuthentication();
        UserAccount loginUser = null;

        try {

            loginUser = userService.getUserByUsername(currentUser.getUsername());
        }
        catch (NoResultException exception) {

            return "user does not exist";
        }

        boolean onHashPassword = authorizer.authenticate(currentUser.getPassword().toCharArray(), loginUser.getPassword());

        if (onHashPassword) {

            session.setAttribute("currentUser", loginUser);
            return loginUser;
        }

        else {

            return "username or password is incorrect";
        }
    }

    /**
     * Nulls the user attribute of the session
     * @param session current session
     * @return String that denotes the status of the logout
     */
    @GetMapping(value = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "logout successful";
    }

    public LoginController() {
    }

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
