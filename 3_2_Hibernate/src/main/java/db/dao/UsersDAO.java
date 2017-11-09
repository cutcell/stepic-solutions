package db.dao;

import db.dataset.UsersDataSet;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class UsersDAO {

    private final Session session;
    private final CriteriaBuilder criteriaBuilder;


    public UsersDAO(Session session) {
        this.session = session;
        this.criteriaBuilder = session.getCriteriaBuilder();
    }

    public UsersDataSet get(long id) {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public UsersDataSet getUser(String login) {

        CriteriaQuery<UsersDataSet> criteriaQuery = criteriaBuilder.createQuery(UsersDataSet.class);
        Root<UsersDataSet> root = criteriaQuery.from(UsersDataSet.class);
        ParameterExpression<String> loginParam = criteriaBuilder.parameter(String.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("login"), loginParam));

        TypedQuery<UsersDataSet> query = session.createQuery(criteriaQuery);
        query.setParameter(loginParam, login);
        return query.getSingleResult();

    }

    public long insertUser(String name, String password) {
        return (Long) session.save(new UsersDataSet(name, password));
    }



}
