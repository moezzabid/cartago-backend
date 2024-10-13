package org.art.web.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.art.dto.ArtworkDto;
import org.art.model.Artwork;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "/api")
public interface IArtWorkApi {

  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "API_SEARCH_SUCCESS",
      content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = ArtworkDto.class)) }),
    @ApiResponse(responseCode = "500", description = "Internal server error",
      content = @Content) })
  @Operation(summary = "Get  artworks", description = "Fetch  artworks with query", tags = { "Artwork" })
  @GetMapping("/search")
  ResponseEntity<?> searchArtworks(@RequestParam String query);

  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "API_GET_ALL_ARTWORKS_SUCCESS",
      content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = ArtworkDto.class)) }),
    @ApiResponse(responseCode = "500", description = "Internal server error",
      content = @Content) })
  @Operation(summary = "Get all artworks", description = "Fetch all artworks with pagination", tags = { "Artwork" })
  @GetMapping("/artworks")
  ResponseEntity<?> getAllArtworks(@RequestParam("page") int page,
                                   @RequestParam("limit") int limit);

  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "API_GET_ARTWORK_BY_ID_SUCCESS",
      content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ArtworkDto.class)) }),
    @ApiResponse(responseCode = "404", description = "Artwork not found", content = @Content),
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @Operation(summary = "Get artwork by ID", description = "Fetch artwork details by its ID", tags = { "Artwork" })
  @GetMapping("/artwork/{id}")
  ResponseEntity<?> getArtworkById(@PathVariable int id);
}

