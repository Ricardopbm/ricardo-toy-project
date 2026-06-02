package com.example.demo.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.AuthToken;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class AuthTokenRepository {

    private final EntityManager em;

    public AuthTokenRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<AuthToken> findByToken(String token) {
        return em.createQuery("FROM AuthToken a JOIN FETCH a.user WHERE a.token = :token", AuthToken.class)
                .setParameter("token", token)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Transactional
    public void save(AuthToken authToken) {
        if (authToken.getId() == null) {
            em.persist(authToken);
        } else {
            em.merge(authToken);
        }
    }
}
