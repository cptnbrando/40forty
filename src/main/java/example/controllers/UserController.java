package example.controllers;

import example.models.UserAccount;
import example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: NOTE: these are temporary paths and mappings, so they can be changed
//      make sure to do the server-config part of this when we switch from using a main to a server

@RestController
@RequestMapping("/users")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class UserController {

    private UserService userService;

    /**
     * Server endpoint handler
     * gets all users from the Service.
     *
     * @return List of all Users
     */
    @GetMapping(value = "/getAllUsers")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<UserAccount> getAllUsers(){
        System.out.println("in getAllUsers");

        return userService.getAllUsers();
    }


    /**
     * Server endpoint handler
     * gets a user by it's id
     * @param id id to search for
     * @return user object that represents the user with that id
     */
    @GetMapping(value = "/getUserById")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    UserAccount getUserById(@RequestParam("id")int id){

        return userService.getUserByID(id);
    }

    /**
     * Server endpoint handler
     * gets a user by it's username
     * @param username username to search for
     * @return user object that represents the user with that username
     */
    @GetMapping(value = "/getUserByUsername")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    UserAccount getUserByUsername(@RequestParam("username")String username){

        return userService.getUserByUsername(username);
    }

    /**
     * Server endpoint handler
     * gets a user by it's email
     * @param email email to search for
     * @return user object that represents the user with that email
     */
    @GetMapping(value = "/getUserByEmail")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    UserAccount getUserByEmail(@RequestParam("email")String email){
        return userService.getUserByUsername(email);
    }

    //probably going into the registration controllers
    /**
     * creates a new user in the database
     * @param incomingUser user object to add to the db
     */
    @PostMapping(value = "/createUser")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createUser(@RequestBody UserAccount incomingUser){
        userService.addUser(incomingUser);
    }

    /**
     * updates a user with the information present in the param
     * @param updatedUser user object that contains the updated information about the user
     */
    @PutMapping(value = "/updateUser")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateUser(@RequestBody UserAccount updatedUser){
        userService.updateUser(updatedUser);
    }

    //only for admin and maybe extra functionality later
    @DeleteMapping(value = "/deleteUser")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@RequestBody UserAccount toBeDeleted){
        userService.deleteUser(toBeDeleted.getUserID());
    }


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserController() {
    }

    public UserService getUserService() {
        userService.insertInitialValues();
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
        userService.insertInitialValues();
    }
}
