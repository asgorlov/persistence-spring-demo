package com.novaworld.persistence.dao;

import com.novaworld.persistence.entity.Officer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author ango1019
 * Date: 02.07.2020
 * Time: 23:30
 */
@SuppressWarnings({"JqpaQlInspection"})
@Repository
public class JpaOfficerDao implements OfficerDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Officer save(Officer officer) {
        entityManager.persist(officer);
        return officer;
    }

    @Override
    public Optional<Officer> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Officer.class, id));
    }

    @Override
    public List<Officer> findAll() {
        return entityManager.createQuery("SELECT o FROM Officer o", Officer.class)
                .getResultList();
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(o.id) FROM Officer o", Long.class)
                .getSingleResult();
    }

    @Override
    public void delete(Officer officer) {
        entityManager.remove(officer);
    }

    @Override
    public boolean existById(Integer id) {
        Long count = entityManager
                .createQuery("SELECT COUNT(o.id) FROM Officer o WHERE o.id=:id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }
}