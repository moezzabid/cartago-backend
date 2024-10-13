package org.art.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtworkDto {

  private int id;
  private String title;
  private Boolean is_boosted;
  private String api_model;
  private ThumbnailDto thumbnail;
}
