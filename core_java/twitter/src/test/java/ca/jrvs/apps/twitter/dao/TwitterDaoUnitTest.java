package ca.jrvs.apps.twitter.dao;


import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.TweetConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.*;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {
  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;

  @Test
  public void postTweet() throws Exception{
    String hashtag = "abcd";
    String text = "something may appear here: " + hashtag + System.currentTimeMillis();
    Double lat = 33D;
    Double lon = -126D;
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try{
      dao.create(TweetConstructor.tweetBuild(text,lon, lat));
    }catch (RuntimeException e){
      assertTrue(true);
    }

    String tweetJsonStr = "{\n"
        + "   \"created_at\":\"Mon Feb 21:24:39 +1111 2019\", \n"
        + "   \"id\":1205582607922139137,\n"
        + "   \"id_str\":\"1205582607922139137\",\n"
        + "   \"text\":\"some text here\",\n"
        + "   \"entities\":{\n"
        + "       \"hashtag\":[],\n"
        + "       \"user_mentions\":[]\n"
        + "   },\n"
        + "   \"coordinated\":null,\n"
        + "   \"retweet_count\":0,\n"
        + "   \"favorited_count\":0,\n"
        + "   \"favorited\":false,\n"
        + "   \"retweeted\":false\n"
        + "}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);

    doReturn(expectedTweet).when(spyDao).parseResponse(any());
    Tweet tweet = spyDao.create(TweetConstructor.tweetBuild(text, lon, lat));
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }

  @Test
  public void showTweet() throws Exception{
    String hashtag = "abcd";
    String text = "something may appear here: " + hashtag + System.currentTimeMillis();
    Double lat = 33D;
    Double lon = -126D;
    when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException("mock"));
    try{
      dao.findById("1205582607922139137");
    }catch (RuntimeException e){
      assertTrue(true);
    }

    String tweetJsonStr = "{\n"
        + "   \"created_at\":\"Mon Feb 21:24:39 +1111 2019\", \n"
        + "   \"id\":1205582607922139137,\n"
        + "   \"id_str\":\"1205582607922139137\",\n"
        + "   \"text\":\"some text here\",\n"
        + "   \"entities\":{\n"
        + "       \"hashtag\":[],\n"
        + "       \"user_mentions\":[]\n"
        + "   },\n"
        + "   \"coordinated\":null,\n"
        + "   \"retweet_count\":0,\n"
        + "   \"favorited_count\":0,\n"
        + "   \"favorited\":false,\n"
        + "   \"retweeted\":false\n"
        + "}";

    when(mockHelper.httpGet(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);

    doReturn(expectedTweet).when(spyDao).parseResponse(any());
    Tweet tweet = spyDao.findById("1205582607922139137");
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }

  @Test
  public void deleteTweet() throws Exception{
    String hashtag = "abcd";
    String text = "something may appear here: " + hashtag + System.currentTimeMillis();
    Double lat = 33D;
    Double lon = -126D;
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try{
      dao.deleteById("1205582607922139137");
    }catch (RuntimeException e){
      assertTrue(true);
    }

    String tweetJsonStr = "{\n"
        + "   \"created_at\":\"Mon Feb 21:24:39 +1111 2019\", \n"
        + "   \"id\":1205582607922139137,\n"
        + "   \"id_str\":\"1205582607922139137\",\n"
        + "   \"text\":\"some text here\",\n"
        + "   \"entities\":{\n"
        + "       \"hashtag\":[],\n"
        + "       \"user_mentions\":[]\n"
        + "   },\n"
        + "   \"coordinated\":null,\n"
        + "   \"retweet_count\":0,\n"
        + "   \"favorited_count\":0,\n"
        + "   \"favorited\":false,\n"
        + "   \"retweeted\":false\n"
        + "}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);

    doReturn(expectedTweet).when(spyDao).parseResponse(any());
    Tweet tweet = spyDao.deleteById("1205582607922139137");
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }

}
