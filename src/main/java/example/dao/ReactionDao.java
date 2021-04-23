package example.dao;

import example.models.Reaction;

import java.util.List;

public interface ReactionDao {

    void createReaction(Reaction reaction);

    List<Reaction> findAllReactions();

    Reaction findReaction(int reactionID);

    List<Reaction> findPostReactions(int postID);
}
