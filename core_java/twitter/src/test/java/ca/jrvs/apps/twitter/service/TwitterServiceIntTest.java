package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.TweetConstructor;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {

  private TwitterService twitterService;
  private Tweet tweet;
  private Tweet postTweet;
  private Tweet tweetSecond;

  @Before
  public void setUpAndPostTweet() {
    String  consumerKey= System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
    TwitterDao dao = new TwitterDao(httpHelper);
    this.twitterService = new TwitterService(dao);

    // Create a tweet and save it to postTweet which is the tweet that is going to post
    String hashtag = "abc";
    String text1 = "what is happening " + hashtag + " " + System.currentTimeMillis();
    String text2 = "what is happeningg " + hashtag + " " + System.currentTimeMillis();
    Double lat = 34d;
    Double lon = -118d;
    postTweet = TweetConstructor.tweetBuild(text1, lon, lat);
    Tweet postTweetSecond = TweetConstructor.tweetBuild(text2, lon, lat);

    // Posting tweet
    tweet = twitterService.postTweet(postTweet);
    tweetSecond = twitterService.postTweet(postTweetSecond);
    // Only the first one is checked here
    tester(tweet);
  }

  @Test
  public void showTweet() {
    String[] fields = {"created_at", "id_str"};
    Tweet tweetShow = twitterService.showTweet(tweet.getId_str(),fields);
    tester(tweetShow);
  }

  @After
  public void deleteTweet(){
    // Since there are two same tweet id in the following String array
    // It is expected that a runtime exception will be thrown
    String[] idArray = {tweet.getId_str(), tweetSecond.getId_str()};
    List<Tweet> tweetDelete = twitterService.deleteTweets(idArray);
    // Only the first one is checked here
    tester(tweetDelete.get(0));
  }

  public void tester(Tweet tweet){
    assertEquals(postTweet.getText(), tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2,tweet.getCoordinates().getCoordinatesTweet().size());
    assertEquals(postTweet.getCoordinates().getCoordinatesTweet().get(0),
        tweet.getCoordinates().getCoordinatesTweet().get(0));
    assertEquals(postTweet.getCoordinates().getCoordinatesTweet().get(1),
        tweet.getCoordinates().getCoordinatesTweet().get(1));
  }

}