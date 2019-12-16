package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.TweetConstructor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {

  private TwitterDao dao;
  private Tweet postTweet;
  private Tweet tweet;

  @Before
  public void setUp() {
    String  consumerKey= System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
    this.dao = new TwitterDao(httpHelper);

    // Setting up the postTweet which is going to post
    String hashtag = "abc";
    String text = "what is happening " + hashtag + " " + System.currentTimeMillis();
    Double lat = 34d;
    Double lon = -118d;
    postTweet = TweetConstructor.tweetBuild(text, lon, lat);

    // Posting tweet
    tweet = dao.create(postTweet);
    tester(tweet);
  }

  @Test
  public void show() throws Exception{
    Tweet tweetShow = dao.findById(tweet.getId_str());
    tester(tweetShow);
  }

  @After
  public void delete(){
    Tweet tweetDelete = dao.deleteById(tweet.getId_str());
    tester(tweetDelete);
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
