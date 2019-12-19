package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.TweetConstructor;
import ca.jrvs.apps.twitter.service.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller{

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  @Autowired
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
      throw new IllegalArgumentException("Error: The tweet you are trying to post is empty.");
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
    Tweet tweet = TweetConstructor.tweetBuild(text, lon, lat);
    return service.postTweet(tweet);
  }

  @Override
  public Tweet showTweet(String[] args) {
    if (args.length != 3){
      throw new IllegalArgumentException("Error: Number of arguments is not correct. "
          + "To post a tweet- USAGE: TwitterCLIApp show \"tweet id\", \"field,field,...\"");
    }
    String id = args[1];
    idChecker(id);
    String[] fields = args[2].split(COMMA);
    if (fields.length > 11){
      throw new IllegalArgumentException("Error: The fields is too many. "
          + "Please enter up to 11 fields.\n"
          + "Format: field,field,...");
    }
    return service.showTweet(id, fields);
  }

  private void idChecker(String id) {
    if (!id.matches("[0-9]+")){
      throw new IllegalArgumentException("Error: id contains character or space in it. "
          + "Please make sure the id you entered is correct.");
    }
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length != 2){
      throw new IllegalArgumentException("Error: Number of arguments is not correct. "
          + "To post a tweet- USAGE: TwitterCLIApp delete \"tweet_id,tweet_id,...\"");
    }
    String[] idArray = args[1].split(COMMA);
    for (String id : idArray){
      idChecker(id);
    }
    return service.deleteTweets(idArray);
  }
}
