package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.TweetConstructor;
import ca.jrvs.apps.twitter.service.Service;
import java.util.List;
import org.springframework.util.StringUtils;

public class TwitterController implements Controller{

  private static String COORD_SEP = ":";
  private static String COMMA = ",";

  private Service service;

  public TwitterController(Service service){
    this.service = service;
  }

  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 3){
      throw new IllegalArgumentException("Error: Number of arguments is not correct. "
          + "To post a tweet- USAGE: TwitterCLIApp post \"text to post\", \"longitude:latitude\".");
    }
    String text = args[1];
    if (StringUtils.isEmpty(text)){
      throw new IllegalArgumentException("Error: The tweet you are trying to post is empty.")
    }
    String coordinatesRaw = args[2];
    String[] coordinates = coordinatesRaw.split(COORD_SEP);
    if (coordinates.length != 2){
      throw new IllegalArgumentException("Error: The format of Coordinates is not correct. \n"
          + "Fomate: longitude:latitude.");
    }
    Double lon;
    Double lat;
    try{
      lon = Double.parseDouble(coordinates[0]);
      lat = Double.parseDouble(coordinates[1]);
    }catch (NumberFormatException e){
      throw new IllegalArgumentException("Error: The longitude and latitude you entered contains "
          + "characters.");
    }
    Tweet tweet = new Tweet();
    tweet = TweetConstructor.tweetBuild(text, lon, lat);
    return service.postTweet(tweet);
  }

  @Override
  public Tweet showTweet(String[] args) {
    return null;
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    return null;
  }
}
