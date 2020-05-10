package ru.itis.chat.repositories.impls;

import org.springframework.stereotype.Repository;
import ru.itis.chat.models.CookieDto;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.CookiesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CookiesRepositoryImpl implements CookiesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<CookieDto> findByParameter(String parameter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CookieDto> criteriaQuery = criteriaBuilder.createQuery(CookieDto.class);
        Root<CookieDto> root = criteriaQuery.from(CookieDto.class);
        criteriaQuery.select(root);
        //TODO Fix this
        ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("parameter"), params));

        TypedQuery<CookieDto> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(params, parameter);
        List<CookieDto> queryResult = query.getResultList();
        CookieDto returnObject = null;

        if (!queryResult.isEmpty()) {
            returnObject = queryResult.get(0);
        }

        return Optional.ofNullable(returnObject);
    }

    @Override
    public void deleteByUser(User user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CookieDto> criteriaQuery = criteriaBuilder.createQuery(CookieDto.class);
        Root<CookieDto> root = criteriaQuery.from(CookieDto.class);
        criteriaQuery.select(root);

        ParameterExpression<User> params = criteriaBuilder.parameter(User.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("user"), params));

        TypedQuery<CookieDto> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(params, user);
        List<CookieDto> queryResult = query.getResultList();
        CookieDto deleteObject = null;

        if (!queryResult.isEmpty()) {
            deleteObject = queryResult.get(0);
        }

        entityManager.remove(deleteObject);
    }

    @Override
    public Optional<CookieDto> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<CookieDto> findAll() {
        return null;
    }

    @Override
    public void save(CookieDto entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(CookieDto entity) {

    }
}
