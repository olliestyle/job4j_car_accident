package ru.job4j.caraccident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Rule;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;

import java.util.List;
import java.util.function.Function;

/*
@Repository
*/
public class AccidentHibernate {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private  <T> T op(Function<Session, T> function) {
        Session session = sf.openSession();
        session.beginTransaction();
        try {
            T toReturn = function.apply(session);
            session.getTransaction().commit();
            return toReturn;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Accident save(Accident accident) {
        op(session -> session.save(accident));
        return accident;
    }

    public List<Accident> findAllAccidents() {
        return op(session -> session
                .createQuery("select distinct a from Accident a"
                        + " join fetch a.accidentType"
                        + " join fetch a.rules", Accident.class)
                .list());
    }

    public List<Rule> findAllRules() {
        return op(session -> session
                .createQuery("from Rule", Rule.class)
                .list());
    }

    public List<AccidentType> findAllTypes() {
        return op(session -> session
                .createQuery("select distinct at from AccidentType at"
                        + " join fetch at.accidents", AccidentType.class)
                .list());
    }

    public Accident findAccidentById(int id) {
        return op(session -> session
                .createQuery("select distinct a from Accident a "
                        + " join fetch a.accidentType"
                        + " join fetch a.rules"
                        + " where a.id = :aid", Accident.class)
                .setParameter("aid", id)
                .uniqueResult());
    }

    public void edit(Accident accident) {
        op(session -> {
            Accident toUpdate = session.get(Accident.class, accident.getId());
            toUpdate.setName(accident.getName());
            toUpdate.setText(accident.getText());
            toUpdate.setAddress(accident.getAddress());
            AccidentType ac = session.get(AccidentType.class, accident.getAccidentType().getId());
            toUpdate.setAccidentType(ac);
            return session.merge(toUpdate);
        });
    }

    public void update(Accident accident) {
        op(session -> {
            Accident toUpdate = session.get(Accident.class, accident.getId());
            toUpdate.setName(accident.getName());
            return session.merge(toUpdate);
        });
    }
}
