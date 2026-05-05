package com.javarush.rodionov.hibernate.converter;

import com.javarush.rodionov.hibernate.entity.Rating;
import jakarta.persistence.AttributeConverter;

public class RatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating rating) {
        if (rating == null) return null;

        return switch (rating) {
            case PG_13 -> "PG-13";
            case NC_17 -> "NC-17";
            default -> rating.name();
        };
    }

    @Override
    public Rating convertToEntityAttribute(String value) {
        if (value == null) return null;

        return switch (value) {
            case "PG-13" -> Rating.PG_13;
            case "NC-17" -> Rating.NC_17;
            default -> Rating.valueOf(value);
        };
    }
}
