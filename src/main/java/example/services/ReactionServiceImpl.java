package example.services;

import example.dao.ReactionDao;
import example.models.Post;
import example.models.Reaction;
import example.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Reaction Service, reactionDao = setter autowired
 */
@Service
public class ReactionServiceImpl implements ReactionService {

    private ReactionDao reactionDao;

    public ReactionServiceImpl() {

    }

    public ReactionServiceImpl(ReactionDao reactionDao) {
        this.reactionDao = reactionDao;
    }

    /**
     * Adds a Reaction to the DB
     *
     * @param reaction the reaction
     */
    @Override
    public void addReaction(Reaction reaction) {

    }

    /**
     * Creates a new positive (1) Reaction from a User to a Post
     *
     * @param user the user
     * @param post the post
     */
    @Override
    public void addPositiveReaction(UserAccount user, Post post) {

    }

    /**
     * Creates a new negative (0) Reaction from a User to a Post
     *
     * @param user the user
     * @param post the post
     */
    @Override
    public void addNegativeReaction(UserAccount user, Post post) {

    }

    /**
     * Get all the Reactions in the DB
     *
     * @return List of all Reactions
     */
    @Override
    public List<Reaction> getAllReactions() {
        return null;
    }

    /**
     * Get all Reactions for a Post in the DB
     *
     * @param post the post
     * @return List of all Reactions for given Post
     */
    @Override
    public List<Reaction> getAllPostReactions(Post post) {
        return null;
    }

    /**
     * Get all Reactions that a User has made
     *
     * @param user the user
     * @return List of all User Reactions
     */
    @Override
    public List<Reaction> getAllUserReactions(UserAccount user) {
        return null;
    }

    /**
     * Get a Reaction with the ID
     *
     * @param reactionID the ID of the Reaction
     * @return the Reaction object
     */
    @Override
    public Reaction getReaction(int reactionID) {
        return null;
    }

    /**
     * Remove a Reaction from the DB
     *
     * @param reaction the reaction (object contains user/post information)
     */
    @Override
    public void deleteReaction(Reaction reaction) {

    }

    public ReactionDao getReactionDao() {
        return reactionDao;
    }

    @Autowired
    public void setReactionDao(ReactionDao reactionDao) {
        this.reactionDao = reactionDao;
    }
}
