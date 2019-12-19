package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.JsonParser;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterCLIApp {

  private Controller controller;

  @Autowired
  public TwitterCLIApp(Controller controller){
    this.controller = controller;
  }

  public static void main(String[] args) {
    String  consumerKey= System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    // setting up the controller
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
    TwitterDao dao = new TwitterDao(httpHelper);
    TwitterService twitterService = new TwitterService(dao);
    Controller controller = new TwitterController(twitterService);
    TwitterCLIApp CLIApp = new TwitterCLIApp(controller);

    CLIApp.run(args);
  }

  public void run(String[] args) {
    if (args.length == 0){
      throw new IllegalArgumentException("Error: You didn't enter any input.");
    }
    String option = args[0];
    Tweet tweet;
    switch (option.toLowerCase()){
      case "post":
        tweet = controller.postTweet(args);
        print(tweet);
        break;
      case "show":
        tweet = controller.showTweet(args);
        print(tweet);
        break;
      case "delete":
        List<Tweet> tweets = controller.deleteTweet(args);
        tweets.forEach(this::print);
        break;
      default:
        throw new IllegalArgumentException("Error: the action you enter is not valid. "
            + "Please use one of \"post\", \"show\", or \"delete\".\n"
            + "Format: TwitterCLIApp \"post/show/delete\" [option].");
    }
  }

  private void print(Tweet tweet){
    try{
      System.out.println(JsonParser.toJson(tweet,true, false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error: Failed to convert from tweet object to Json. ",  e);
    }
  }

}
