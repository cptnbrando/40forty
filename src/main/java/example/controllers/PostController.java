package example.controllers;

import example.models.Post;
import example.models.UserAccount;
import example.services.PostService;
import example.services.PostServiceImpl;
import example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Controller for the Posts model. This class defines the
 * endpoints for a webapp to get information from the database.
 * in this case we will use the post controller for when a user
 * wants to create, update and/or delete a posts. Also we will be
 * getting all of the posts to fill out the main feed.
 * TODO: create, get, getall, update and delete
 */
@RestController
@RequestMapping("/posts")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
//@SessionAttributes("loggedInUser")
public class PostController {

    private PostService postService;
    private UserService userService;

    /**
     * Create new post. This endpoint will receive a post
     * from a user.
     *
     * @param incomingPost json request
     */
    @PostMapping(value = "/create-post")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createNewPost(@RequestBody Post incomingPost, HttpSession session) {

        System.out.println("creating a new post");
        UserAccount author = (UserAccount) session.getAttribute("loggedInUser");
        incomingPost.setAuthor(author);
        postService.addPost(incomingPost);
    }

    /**
     * Return all of the posts for every registered user
     * that has posted something. will be used in the
     * regular feed where a user can see other users
     * posts
     *
     * @return list of all posts
     */
    @GetMapping(value = "/getAllPosts")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Post> getAllPosts() {

        System.out.println("getting all posts");

        return postService.getAllPosts();
    }

    /**
     * Return all of the posts related to a specific user posts
     * by using their id. This will be used to populate a user
     * profile feed where they can see all of their posts.
     *
     * @param id userID
     * @return list of post
     */
    @GetMapping(value = "/getAllUserPost")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Post> getAllUsersPosts(@RequestParam("id") int id) {

        return postService.getAllUserPosts(id);
    }

    /**
     * Delete a post. TODO: flesh out more
     * @param deletePost
     */
    @DeleteMapping(value = "/deletePost")
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePost(@RequestBody Post deletePost) {

        postService.deletePosts(deletePost);
    }

    /**
     * No args constructors
     */
    public PostController() {

    }

    /**
     * Initialize a post service implementation
     *
     * @param postService PostsServiceImpl
     */
    public PostController(PostServiceImpl postService) {

        this.postService = postService;
    }

    /**
     * Get post service impl
     *
     * @return PostServiceImpl
     */
    public PostService getPostService() {
        return postService;
    }

    /**
     * Set post service implementation
     *
     * @param postService PostServiceImpl
     */
    @Autowired
    public void setPostService(PostService postService) {

        this.postService = postService;
    }

    /**
     * Get user service
     *
     * @return UserService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Set user service
     *
     * @param userService UserService
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
