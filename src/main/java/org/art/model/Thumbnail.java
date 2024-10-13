package org.art.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Thumbnail {

  private String alt_text;
  private String lqip;
  private Integer width;
  private Integer height;

}
