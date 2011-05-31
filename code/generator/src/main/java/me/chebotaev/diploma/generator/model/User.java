package me.chebotaev.diploma.generator.model;

import java.util.HashMap;
import java.util.Map;

public class User {

    static Map<Long, Tag> all = new HashMap<Long, Tag>();
    static long newId = 1;

    long id;

    Map<Movie, Rating> movies = new HashMap<Movie, Rating>();

    public User(long id) {
        this.id = id;
    }

    public void addMovieRating(Movie movie, int moodId, int value) {
        Rating rating = movies.get(movie);
        if (rating == null) {
            rating = new Rating();
            movies.put(movie, rating);
        }
        rating.add(moodId, value);
    }

    public Map<Movie, Rating> getMovies() {
        return movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
