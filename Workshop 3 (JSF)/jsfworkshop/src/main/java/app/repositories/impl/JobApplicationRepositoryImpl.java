package app.repositories.impl;

import app.domains.entities.JobApplication;
import app.repositories.JobApplicationRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobApplicationRepositoryImpl implements JobApplicationRepository {

    private final EntityManager entityManager;

    @Inject
    public JobApplicationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(JobApplication jobApplication) {
        entityManager.getTransaction().begin();
        entityManager.persist(jobApplication);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<JobApplication> findAll() {

        try {
            return entityManager.createQuery("SELECT j FROM JobApplication j", JobApplication.class).getResultList();
        } catch (Exception e) {
            return null;
        }


    }

    @Override
    public JobApplication findJobApplicationById(int id) {
        try {
            return entityManager.createQuery("SELECT j FROM JobApplication j WHERE j.id = :id", JobApplication.class)
                .setParameter("id", id)
                .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM JobApplication j WHERE j.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
}
