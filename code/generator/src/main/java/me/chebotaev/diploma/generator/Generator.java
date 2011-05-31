package me.chebotaev.diploma.generator;

import me.chebotaev.diploma.common.PropertyManager;
import me.chebotaev.diploma.common.PropertyManager.Property;
import me.chebotaev.diploma.common.RandomGenerator;
import me.chebotaev.diploma.generator.model.Movie;
import me.chebotaev.diploma.generator.model.Tag;
import me.chebotaev.diploma.generator.model.User;

import java.util.*;

public class Generator {

    Map<Integer, Movie> movies = new HashMap<Integer, Movie>();
    Map<Integer, User> users = new HashMap<Integer, User>();
    Map<Integer, Tag> tags = new HashMap<Integer, Tag>();

    int moviesCount; // Movies count
    int tagsCount; // Tags count

    int tagsPerMovieMin; // tags per movie min
    int tagsPerMovieMax; // tags per movie max
    int moviesPerTag;    // movies per tag

    int usersCount;    // Users count
    int moviesPerUser; // Movies per user

    int moodsCount;     // Number of moods

    int ratingMin;
    int ratingMax;

    public Generator() {
        readProperties();
        initData();

        fillMoviesWithTags();
        generateRatings();
    }

    public Collection<Movie> getMovies() {
        return movies.values();
    }

    public Collection<User> getUsers() {
        return users.values();
    }

    public Collection<Tag> getTags() {
        return tags.values();
    }

    /*

        Private methods for object construction

     */

    private void readProperties() {
        moviesCount = PropertyManager.getInt(Property.MOVIES_COUNT);

        tagsPerMovieMax = PropertyManager.getInt(Property.TAGS_PER_MOVIE_MAX);
        tagsPerMovieMin = PropertyManager.getInt(Property.TAGS_PER_MOVIE_MIN);

        int tpPercentage = PropertyManager.getInt(Property.MOVIES_PER_TAG_PERCENTAGE);
        moviesPerTag = moviesCount * tpPercentage / 100;

        tagsCount = moviesCount * tagsPerMovieMax / moviesPerTag + 1;

        usersCount = PropertyManager.getInt(Property.USERS_COUNT);
        moviesPerUser = PropertyManager.getInt(Property.USR_RATED_MOVIES);

        moodsCount = PropertyManager.getInt(Property.MOODS_COUNT);

        ratingMin = PropertyManager.getInt(Property.RATING_MIN);
        ratingMax = PropertyManager.getInt(Property.RATING_MAX);
    }

    private void initData() {
        for (int i = 0; i < moviesCount; i++)
            movies.put(i, new Movie(i));

        for (int i = 0; i < tagsCount; i++)
            tags.put(i, new Tag(i));

        for (int i = 0; i < usersCount; i++){
            users.put(i, new User(i));
        }
    }

    private void fillMoviesWithTags() {
        Map<Integer, Tag> unusedTags = new HashMap<Integer, Tag>();
        unusedTags.putAll(tags);

        Map<Tag, Integer> unusedTagsValues = new HashMap<Tag, Integer>();
        for (Tag tag : unusedTags.values()) {
            unusedTagsValues.put(tag, moviesPerTag);
        }


        int tmDelta = tagsPerMovieMax - tagsPerMovieMin;
        for (Movie movie : movies.values()) {
            int tagsCount = tagsPerMovieMin + RandomGenerator.get(tmDelta);
            for (int i = 0; i < tagsCount; i++) {

                // find new unused tag
                boolean found = false;
                Tag tag = null;
                while (!found) {
                    int tagId = RandomGenerator.get(this.tagsCount);
                    if (unusedTags.containsKey(tagId)) {
                        tag = unusedTags.get(tagId);
                        found = true;
                    }
                }

                // remove tag if unused count is over
                int unusedValue = unusedTagsValues.get(tag);
                if (unusedValue == 1) {
                    unusedTags.remove(tag.getId());
                    unusedTagsValues.remove(tag);
                } else {
                    unusedTagsValues.put(tag, unusedValue - 1);
                }

                movie.addTag(tag, 1);
            }
        }
    }

    private void generateRatings() {

        // for all users
        for (User user : users.values()) {
            for (int m = 0; m < moviesPerUser; m++) {
                // find a movie
                Movie movie = null;
                boolean found = false;
                while (!found) {
                    int movieId = RandomGenerator.get(moviesCount);
                    movie = movies.get(movieId);
                    if (!user.getMovies().containsKey(movie))
                        found = true;
                }

                // generate preferred id
                int moodId = RandomGenerator.get(moodsCount);
                // generate rating
                int rating = RandomGenerator.get(ratingMin, ratingMax);

                // for other movies rating will be randomly less
                for (int i = 0; i < moodsCount; i++) {
                    if (i == moodId) {
                        user.addMovieRating(movie, i, rating);
                    } else {
                        user.addMovieRating(movie, i, RandomGenerator.get(ratingMin, rating));
                    }
                }
            }
        }

    }

}

