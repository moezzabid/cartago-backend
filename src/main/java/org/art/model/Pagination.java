package org.art.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {

  private int total;
  private int limit;
  private int offset;
  private int totalPages;
  private int currentPage;
}
