package app.repository.impl;

        import app.domain.entity.Hero;
        import app.repository.HeroRepository;

        import javax.inject.Inject;
        import javax.persistence.EntityManager;
        import java.util.List;

public class HeroRepositoryImpl implements HeroRepository {

    private final EntityManager manager;

    @Inject
    public HeroRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(Hero hero) {
        manager.getTransaction().begin();
        manager.persist(hero);
        manager.getTransaction().commit();
    }

    @Override
    public Hero findById(String id) {
        return manager.createQuery("SELECT h FROM Hero h WHERE h.id = :id", Hero.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Hero> findAll() {
        return manager.createQuery("SELECT h FROM Hero h", Hero.class)
                .getResultList();
    }

    @Override
    public void delete(String id) {
        manager.getTransaction().begin();
        manager.createQuery("DELETE FROM Hero h WHERE h.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        manager.getTransaction().commit();
    }
}

