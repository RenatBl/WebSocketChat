package ru.itis.chat.repositories.impls;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.models.Chat;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.ChatsRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ChatsRepositoryImpl implements ChatsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Chat> find(Long id) {
        return Optional.ofNullable(entityManager.find(Chat.class, id));
    }

    @Override
    public List<Chat> findAll() {
        return null;
    }

    @Override
    public void save(Chat entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Long id) {
        Chat chat = entityManager.find(Chat.class, id);
        entityManager.remove(chat);
    }

    @Override
    public void update(Chat entity) {

    }

    @Override
    public List<Chat> findAllByUser(User user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chat> criteriaQuery = criteriaBuilder.createQuery(Chat.class);
        Root<Chat> rootEntry = criteriaQuery.from(Chat.class);
        CriteriaQuery<Chat> all = criteriaQuery.select(rootEntry);

        ParameterExpression<User> params = criteriaBuilder.parameter(User.class);
        criteriaQuery.where(criteriaBuilder.equal(rootEntry.get("owner"), params));

        TypedQuery<Chat> allQuery = entityManager.createQuery(all);
        allQuery.setParameter(params, user);

        return allQuery.getResultList();
    }
}
