package com.javarush.rodionov.hibernate.util;

public class QueryUtil {

    private QueryUtil() {}

    public static final String RENT_FILM_QUERY = """
                    SELECT i FROM Inventory i
                    WHERE i.store.id = :storeId
                    AND i.film.id = :filmId
                    AND NOT EXISTS (
                        SELECT r FROM Rental r
                        WHERE r.inventory = i
                        AND r.returnDate IS NULL
                    )
            """;
}
