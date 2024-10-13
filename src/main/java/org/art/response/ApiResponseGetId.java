package org.art.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.art.model.Artwork;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseGetId {

  private Artwork data;

}
