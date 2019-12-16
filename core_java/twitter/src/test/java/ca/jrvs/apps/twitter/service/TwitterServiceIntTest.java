package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.TweetConstructor;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {

  private TwitterService twitterService;

  @Before
  public void setUp() {
    String  consumerKey= System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
    TwitterDao dao = new TwitterDao(httpHelper);
    this.twitterService = new TwitterService(dao);
  }

  @Test
  public void postTweet() {
    String hashtag = "abc";
    String text = "what is happening " + hashtag + " " + System.currentTimeMillis();
    Double lat = 34d;
    Double lon = -118d;
    Tweet postTweet = TweetConstructor.tweetBuild(text, lon, lat);
    String[] fields = {"created_at", "id"};

    Tweet tweet = twitterService.postTweet(postTweet);
    tester(tweet,text,lon,lat);

    Tweet tweetShow = twitterService.showTweet(tweet.getId_str(),fields);
    tester(tweetShow, text, lon, lat);

    // Since there are two same tweet id in the following String array
    // It is expected that a runtime exception will be thrown
    String[] idArray = {tweet.getId_str(), tweet.getId_str()};
    try {
      Tweet tweetDelete = (Tweet) twitterService.deleteTweets(idArray);
      tester(tweetDelete, text, lon, lat);
    }catch (RuntimeException e){
      System.out.println("The following error message is expected: " + e);
      assertTrue(true);
    }
  }

  public void tester(Tweet tweet, String text, Double lon, Double lat){

    assertEquals(text, tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2,tweet.getCoordinates().getCoordinatesTweet().size());
    assertEquals(lon, tweet.getCoordinates().getCoordinatesTweet().get(0));
    assertEquals(lat, tweet.getCoordinates().getCoordinatesTweet().get(1));
  }

}