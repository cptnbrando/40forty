package example.services;

import example.dao.PostDaoImpl;
import example.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for Posts- uses the PostDao to update the DB
 *
 * - Add a post with text and optionally media, (should also add it to the User object...or maybe that happens automagically?)
 * - Get all posts
 * - Get all user posts
 * - Update the body of a post (and updates the post in the User's post list... or maybe automagic??)
 * - Add media to a post (and updates the post in the User's post list... or maybe automagic??)
 * - Delete a post (and removes the post from the User's post list... or maybe automagic??) (also deletes all Reactions from the reactions table)
 */
@Service
public class PostServiceImpl implements PostService {

    private PostDaoImpl postDao;

    /**
     * Add a new user with Dao layer
     *
     * @param newPost obj
     */
    @Override
    public void addPost(Post newPost) {

        postDao.createPost(newPost);
    }

    /**
     * Get all available post from all users;
     *
     * @return list of posts
     */
    @Override
    public List<Post> getAllPosts() {

        return postDao.findAllPosts();
    }

    /**
     * Get all the posts from a specific user
     *
     * @param userID fk
     * @return list of posts
     */
    @Override
    public List<Post> getAllUserPosts(int userID) {

        return postDao.findUserPosts(userID);
    }

    /**
     * Update a post: text or media only
     *
     * @param updatePost Posts
     */
    @Override
    public void updatePosts(Post updatePost) {

        //TODO: implement update
    }

    /**
     * Delete a post
     *
     * @param post Posts
     */
    @Override
    public void deletePosts(Post post) {

        postDao.deletePost(post);
    }

    //CONSTRUCTORS
    public PostServiceImpl() {

    }

    public PostServiceImpl(PostDaoImpl postDao) {

        this.postDao = postDao;
    }

    //GETTERS AND SETTERS
    public PostDaoImpl getPostDao() {

        return postDao;
    }

    @Autowired
    public void setPostDao(PostDaoImpl postDao) {

        this.postDao = postDao;
    }
}
