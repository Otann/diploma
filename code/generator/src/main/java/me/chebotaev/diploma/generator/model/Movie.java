package me.chebotaev.diploma.generator.model;

import java.util.HashMap;
import java.util.Map;

public class Movie {

    long id;
    Map<Tag, Double> tags = new HashMap<Tag, Double>();

    public Movie(long id) {
        this.id = id;
    }

    public void addTag(Tag tag, Double weight) {
        tags.put(tag, weight);
    }

    @Override
    public String toString() {
        return "Movie #" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
