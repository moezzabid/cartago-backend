package org.art.model;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artwork {
  private int id;
  private String title;
  private String api_link;
  private Boolean is_boosted;
  private String api_model;
  private Float _score;
  private String timestamp;
  private Thumbnail thumbnail;

}
