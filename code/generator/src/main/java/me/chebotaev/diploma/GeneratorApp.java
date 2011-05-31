package me.chebotaev.diploma;

import me.chebotaev.diploma.common.PropertyManager;
import me.chebotaev.diploma.common.PropertyManager.Property;
import me.chebotaev.diploma.generator.Generator;
import me.chebotaev.diploma.generator.model.Movie;
import me.chebotaev.diploma.generator.model.Rating;
import me.chebotaev.diploma.generator.model.Tag;
import me.chebotaev.diploma.generator.model.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class GeneratorApp {

    public static void main( String[] args ) throws IOException {
        System.out.println( "Generating started" );

        Generator generator;
        try {
            generator = new Generator();
        } catch (Exception e) {
            System.out.println("Unable to create generator, check your property files");
            e.printStackTrace();
            return;
        }

        writeMovies(generator);

        writeRatings(generator);
    }

    /**
     * Writes data about movies and tags to file named 'movies_with_tags'
     * Each line represents movie in following format:
     * <movie_id> <tag_id> <tag_weight> <tag_id> <tag_weight> ...
     *
     * @param generator object to get movies
     * @throws IOException thrown if unable to write to file
     */
    private static void writeMovies(Generator generator) throws IOException {

        System.out.println("Writing data about movies");

        String filename = PropertyManager.get(Property.MOVIES_FILENAME);
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for(Movie movie : generator.getMovies()) {
            StringBuilder sb = new StringBuilder();

            sb.append(movie.getId());
            sb.append(' ');
            for (Map.Entry<Tag, Integer> entry : movie.getTags().entrySet()) {
                sb.append(entry.getKey().getId());
                sb.append(' ');
                sb.append(entry.getValue());
                sb.append(' ');
            }
            sb.append('\n');

            writer.write(sb.toString());
            writer.flush();
        }
        writer.close();
    }

    /**
     * Writes data about user's rating for each mood to file 'ratings_for_mood_#'
     * Each line represents user in following format:
     * <user_id> <movie_id> <movie_rating> <movie_id> <movie_rating> ...
     *
     * @param generator object to get users and ratings
     * @throws IOException thrown if unable to write to file
     */
    private static void writeRatings(Generator generator) throws IOException {

        System.out.println("Writing data about ratings");

        int moodsCount = PropertyManager.getInt(PropertyManager.Property.MOODS_COUNT);
        for (int moodId = 0; moodId < moodsCount; moodId++) {

            System.out.println("Writing data about ratings for mood #" + moodId);

            String filename = String.format(PropertyManager.get(Property.RATINGS_FILENAME_FORMAT), moodId);
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

            for (User user : generator.getUsers()) {
                StringBuilder sb = new StringBuilder();

                sb.append(user.getId());
                sb.append(' ');

                for (Map.Entry<Movie, Rating> entry : user.getMovies().entrySet()) {
                    sb.append(entry.getKey().getId());
                    sb.append(' ');
                    sb.append(entry.getValue().getValue(moodId));
                    sb.append(' ');
                }
                sb.append('\n');

                writer.write(sb.toString());
                writer.flush();
            }
            writer.close();
        }

    }

}
