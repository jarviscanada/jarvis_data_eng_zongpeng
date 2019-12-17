package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TwitterService implements Service {

  private CrdDao dao;
  private final String[] availableFields = {"created_at", "id", "id_str", "text", "source",
      "coordinates", "entities", "retweet_count", "favoriated_count", "favoriated", "retweeted"};

  public TwitterService(CrdDao dao){
    this.dao = dao;
  }

  @Override
  public Tweet postTweet(Tweet tweet) {
    validatePostTweet(tweet);
    return (Tweet) dao.create(tweet);
  }

  private void validatePostTweet(Tweet tweet) {
    if (tweet.getText().length() >140){
      throw new RuntimeException("The length of your tweet exceeds 140 character. "
          + "Please post a shorter tweet...");
    }
    if (tweet.getCoordinates().getCoordinatesTweet().get(0)>180 ||
        tweet.getCoordinates().getCoordinatesTweet().get(0)<-180){
      throw new RuntimeException("The longitude you entered is not correct. "
          + "Please enter a longitude from -180 to 180 degree.");
    }
    if (tweet.getCoordinates().getCoordinatesTweet().get(1)>90 ||
        tweet.getCoordinates().getCoordinatesTweet().get(1)<-90){
      throw new RuntimeException("The latitude you entered is not correct. "
          + "Please enter a latitude from -90 to 90 degree.");
    }
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    validateTweetId(id);
    for (String field : fields){
      validateField(field);
    }
    Tweet tweet = (Tweet) dao.findById(id);
    return tweet;
  }

  private void validateField(String field) {
    if (!Arrays.asList(availableFields).contains(field)) {
      throw new RuntimeException("Some of the fields you entered is not correct. "
          + "Please ensure all fields entered are in the following list. " + availableFields.toString());
    }
  }

  private void validateTweetId(String id) {
    if (id.length()!=19){
      throw new RuntimeException("The id is not in the correct format. "
          + "It should consist of 19 digits.");
    }
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    for (String id : ids){
      validateTweetId(id);
    }
    List <Tweet> tweets = new ArrayList<>();
    for (String id : ids){
      tweets.add((Tweet) dao.deleteById(id));
    }
    return tweets;
  }
}
