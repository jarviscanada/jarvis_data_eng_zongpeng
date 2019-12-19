package ca.jrvs.apps.twitter.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TweetConstructor {

  public static Tweet tweetBuild(String text, Double longitude, Double latitude){
    Tweet tweet = new Tweet();
    tweet.setText(text);
    Coordinates coordinates = new Coordinates();
    coordinates.setType("point");
    List<Double> coor = new ArrayList<>();
    coor.add(longitude);
    coor.add(latitude);
    coordinates.setCoordinatesTweet(coor);
    tweet.setCoordinates(coordinates);
    return tweet;
  }

  public static Tweet tweetModifier(Tweet tweetOrg, String[] fields){
    String[] availableFields = {"created_at", "id", "id_str", "text", "source",
        "coordinates", "entities", "retweet_count", "favoriated_count", "favoriated", "retweeted"};
    Tweet tweet = new Tweet();
    /*for (String field : fields){
      switch (field) {
        case "created_at":
          tweet.setCreatedTime(tweetOrg.getCreatedTime());
          break;
        case "id":
          tweet.setId(tweetOrg.getId());
          break;
        case "id_str":
          tweet.setId_str(tweetOrg.getId_str());
          break;
        case "text":
          tweet.setText(tweetOrg.getText());
          break;
        case "source":
          tweet.setSource(tweetOrg.getSource());
          break;
        case "coordinates":
          tweet.setCoordinates(tweetOrg.getCoordinates());
          break;
        case

      }
    }*/
    return tweet;
  }
}
