package me.chebotaev.diploma.common;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertyManager {

    public static enum Property {

        MOVIES_COUNT("generator.movies.count"),
        USERS_COUNT("generator.users.count"),
        USR_RATED_MOVIES("generator.users.rated-movies"),

        TAGS_PER_MOVIE_MIN("generator.tags-per-movie.min"),
        TAGS_PER_MOVIE_MAX("generator.tags-per-movie.max"),

        MOVIES_PER_TAG_PERCENTAGE("generator.movies-per-tag.percentage"),

        MOODS_COUNT("generator.movies-per-tag.percentage"),

        RATING_MIN("generator.rating.min"),
        RATING_MAX("generator.rating.max");

        String value;

        Property(String value) {
            this.value = value;
        }
    }

    private static final Properties properties = new Properties();
    private static final String PROPERTIES_BUNDLE_NAME = "generator";

    static {
        ResourceBundle resources = ResourceBundle.getBundle(PROPERTIES_BUNDLE_NAME, Locale.getDefault());
        for (String key : resources.keySet()) {
            properties.put(key, resources.getString(key));
        }
    }

    public static String get(final Property key) {
        Object value = properties.get(key.value);
        return value != null ? value.toString() : null;
    }

    public static int getInt(final Property key) {
        return Integer.parseInt(get(key));
    }

    public static void put(Property key, Object value) {
        properties.put(key.value, value);
    }
}