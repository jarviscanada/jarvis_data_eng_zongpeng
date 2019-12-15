package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.TweetConstructor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {

  private TwitterDao dao;

  @Before
  public void setUp() {
    String  consumerKey= System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
    this.dao = new TwitterDao(httpHelper);
  }

  @Test
  public void create() throws Exception{
    String hashtag = "abc";
    String text = "what is happening " + hashtag + " " + System.currentTimeMillis();
    Double lat = 34d;
    Double lon = -118d;
    Tweet posttweet = TweetConstructor.tweetBuild(text, lon, lat);

    Tweet tweet = dao.create(posttweet);
    tester(tweet,text,lon,lat);

    Tweet tweetShow = dao.findById(tweet.getId_str());
    tester(tweetShow, text, lon, lat);

    Tweet tweetDelete = dao.deleteById(tweet.getId_str());
    tester(tweetDelete, text, lon, lat);
  }

  public void tester(Tweet tweet, String text, Double lon, Double lat){

    assertEquals(text, tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2,tweet.getCoordinates().getCoordinatesTweet().size());
    assertEquals(lon, tweet.getCoordinates().getCoordinatesTweet().get(0));
    assertEquals(lat, tweet.getCoordinates().getCoordinatesTweet().get(1));
  }
}
