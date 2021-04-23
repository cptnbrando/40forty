package example.dao;

import example.models.Reaction;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("reactionDao")
@Transactional
public class ReactionDaoImpl implements ReactionDao {

    private SessionFactory sessionFactory;

    @Override
    public void createReaction(Reaction reaction) {
        sessionFactory.getCurrentSession().save(reaction);
    }

    @Override
    public List<Reaction> findAllReactions() {
        return sessionFactory.getCurrentSession().createQuery("from Reaction", Reaction.class).list();
    }

    @Override
    public Reaction findReaction(int reactionID) {
        return null;
    }

    @Override
    public List<Reaction> findPostReactions(int postID) {
        return null;
    }



    // CONSTRUCTORS
    public ReactionDaoImpl() {

    }

    public ReactionDaoImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }



    // GETTERS & SETTERS
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
