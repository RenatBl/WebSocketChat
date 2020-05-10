package ru.itis.chat.repositories.impls;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.models.Chat;
import ru.itis.chat.models.Message;
import ru.itis.chat.repositories.MessagesRepository;

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
public class MessagesRepositoryImpl implements MessagesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Message> find(Long id) {
        return Optional.ofNullable(entityManager.find(Message.class, id));
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public void save(Message entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(Message entity) {

    }

    @Override
    public List<Message> getAllByChat(Chat chat) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> rootEntry = criteriaQuery.from(Message.class);
        CriteriaQuery<Message> all = criteriaQuery.select(rootEntry);

        ParameterExpression<Chat> params = criteriaBuilder.parameter(Chat.class);
        criteriaQuery.where(criteriaBuilder.equal(rootEntry.get("chat"), params));

        TypedQuery<Message> allQuery = entityManager.createQuery(all);
        allQuery.setParameter(params, chat);

        return allQuery.getResultList();
    }
}
