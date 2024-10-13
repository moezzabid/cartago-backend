package org.art.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.art.model.Artwork;
import org.art.model.Pagination;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

  private Pagination pagination;
  private List<Artwork> data;
}
