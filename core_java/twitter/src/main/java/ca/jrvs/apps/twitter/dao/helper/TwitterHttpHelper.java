package ca.jrvs.apps.twitter.dao.helper;

import java.io.IOException;
import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class TwitterHttpHelper implements HttpHelper {

  private OAuthConsumer consumer;
  private HttpClient httpClient;

  // Following defaul constructor is used in spring-boot
  public TwitterHttpHelper(){
    String  consumerKey= System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    httpClient = HttpClientBuilder.create().build();
  }

  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken,
      String tokenSecret){
    consumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
    consumer.setTokenWithSecret(accessToken,tokenSecret);

    httpClient = HttpClientBuilder.create().build();
  }

  @Override
  public HttpResponse httpPost(URI uri) {
    try {
      return executeHttpRequest(HttpMethod.POST, uri, null);
    }catch (OAuthException | IOException e){
      throw new RuntimeException("Failed to execute: ", e);
    }
  }

  @Override
  public HttpResponse httpGet(URI uri) {
    try {
      return executeHttpRequest(HttpMethod.GET, uri, null);
    }catch (OAuthException | IOException e){
      throw new RuntimeException("Failed to execute: ", e);
    }
  }

  public HttpResponse executeHttpRequest(HttpMethod method, URI uri, StringEntity stringEntity) throws IOException, OAuthException {
    if (method == HttpMethod.POST){
      HttpPost request = new HttpPost(uri);
      if (stringEntity != null){
        request.setEntity(stringEntity);
      }
      return sendRequest(request);
    }
    else if (method == HttpMethod.GET){
      HttpGet request = new HttpGet(uri);
      return sendRequest(request);
    }
    else {
      throw new IllegalArgumentException("Unknown HTTP method: " + method.name());
    }
  }
    public HttpResponse sendRequest (HttpRequestBase request) throws OAuthException, IOException{
      consumer.sign(request);
      return httpClient.execute(request);
    }
}
