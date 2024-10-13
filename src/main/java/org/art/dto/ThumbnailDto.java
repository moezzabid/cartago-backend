package org.art.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThumbnailDto {

  private String alt_text;
  private String lqip;
  private Integer width;
  private Integer height;
}
