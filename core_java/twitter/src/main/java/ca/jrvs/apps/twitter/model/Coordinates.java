package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import java.util.function.DoubleToLongFunction;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coordinates {

  @JsonProperty("coordinates")
  private List<Double> coordinatesTweet;

  @JsonProperty("type")
  private String type;
//
//  public Coordinates() {
//    this.coordinatesTweet.add(0.0);
//    this.coordinatesTweet.add(0.0);
//    this.type = "";
//  }
//
//  public Coordinates(Double lon, Double lat, String type) {
//    this.coordinatesTweet.add(lon);
//    this.coordinatesTweet.add(lat);
//    this.type = type;
//  }
  public List<Double> getCoordinatesTweet() {
    return coordinatesTweet;
  }

  public void setCoordinatesTweet(List<Double> coordinatesTweet) {
    this.coordinatesTweet = coordinatesTweet;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
