package com.javarush.rodionov.hibernate.dao;

import com.javarush.rodionov.hibernate.entity.*;
import com.javarush.rodionov.hibernate.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FilmDao {

    private final SessionFactory sessionFactory;

    public FilmDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Film releaseFilm(Integer languageId,
                            List<Integer> actorIdList,
                            List<Integer> categoryIdList,
                            List<Integer> storeIdList,
                            int copiesPerStore) {

        return sessionFactory.fromTransaction(session -> {

            Language language = session.getReference(Language.class, languageId);

            Set<Actor> actors = actorIdList.stream()
                    .map(id -> session.getReference(Actor.class, id))
                    .collect(Collectors.toSet());

            Set<Category> categories = categoryIdList.stream()
                    .map(id -> session.getReference(Category.class, id))
                    .collect(Collectors.toSet());

            Film film = new Film();
            film.setTitle("New test film");
            film.setDescription("test film description");
            film.setReleaseYear(2026);
            film.setLanguage(language);
            film.setOriginalLanguage(null);
            film.setRentalDuration(7);
            film.setRentalRate(new BigDecimal("3.99"));
            film.setLength((short) 120);
            film.setReplacementCost(new BigDecimal("19.99"));
            film.setRating(Rating.PG_13);
            film.setSpecialFeatures("Trailers,Deleted Scenes");
            film.setActors(actors);
            film.setCategories(categories);

            session.persist(film);

            for (Integer storeId : storeIdList) {
                Store store = session.getReference(Store.class, storeId);

                for (int copy = 0; copy < copiesPerStore; copy++) {
                    Inventory inventory = new Inventory();
                    inventory.setFilm(film);
                    inventory.setStore(store);
                    session.persist(inventory);
                }
            }

            return film;
        });
    }
}
