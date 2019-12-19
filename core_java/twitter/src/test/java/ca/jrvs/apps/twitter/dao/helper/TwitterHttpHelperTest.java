package ca.jrvs.apps.twitter.dao.helper;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TwitterHttpHelperTest {

  @Test
  public void httpPost() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    TwitterHttpHelper twitterHttpHelper =
        new TwitterHttpHelper(consumerKey,consumerSecret,accessToken,tokenSecret);

    String www = "https://api.twitter.com/1.1/statuses/update.json?status=okokok";
    URI uri = new URI(www);

    try {
      HttpResponse response = twitterHttpHelper.httpPost(uri);
      System.out.println("Post complete!");
      System.out.println(EntityUtils.toString(response.getEntity()));
    } catch(IOException e){
      throw new RuntimeException("Failed to execute: " + e);
    }

  }
}