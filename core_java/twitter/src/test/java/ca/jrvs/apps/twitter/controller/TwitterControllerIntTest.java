package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.TweetConstructor;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {

  private Controller controller;
  private Tweet tweet;
  private Tweet postTweet;

  @Before
  public void setupAndPostTweet(){
    String  consumerKey= System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    // setting up the controller
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
    TwitterDao dao = new TwitterDao(httpHelper);
    TwitterService twitterService = new TwitterService(dao);
    this.controller = new TwitterController(twitterService);

    // save the post tweet into class variable
    String hashtag = "abc";
    String text = "what is happening " + hashtag + " " + System.currentTimeMillis();
    Double lat = 34d;
    Double lon = -118d;
    postTweet = TweetConstructor.tweetBuild(text, lon, lat);

    // post a tweet for test and future uses
    String[] input = {"post", postTweet.getText(), "-118:34"};
    tweet = controller.postTweet(input);
    tester(tweet);
  }

  @Test
  public void showTweet(){
    String[] input = {"show", tweet.getId_str(), "created_at,id_str"};
    tweet = controller.showTweet(input);
    tester(tweet);
  }

  @After
  public void deleteTweet(){
    String idArray = tweet.getId_str() + ",1234567890123456789";
    String[] input = {"delete", idArray};
    try {
      List<Tweet> tweetsDelete = controller.deleteTweet(input);
      for (Tweet tw : tweetsDelete){
        tester(tw);
      }
    }catch (RuntimeException e){
      System.out.println("The following error message is expected: " + e);
      assertTrue(true);
    }
  }

  private void tester(Tweet tweet) {
    assertEquals(postTweet.getText(), tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2,tweet.getCoordinates().getCoordinatesTweet().size());
    assertEquals(postTweet.getCoordinates().getCoordinatesTweet().get(0),
        tweet.getCoordinates().getCoordinatesTweet().get(0));
    assertEquals(postTweet.getCoordinates().getCoordinatesTweet().get(1),
        tweet.getCoordinates().getCoordinatesTweet().get(1));
  }
}
