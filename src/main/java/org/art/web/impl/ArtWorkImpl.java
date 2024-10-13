package org.art.web.impl;

import org.art.dto.ArtworkDto;
import org.art.mapper.ArtworkMapper;
import org.art.model.Artwork;
import org.art.service.ArtworkService;
import org.art.web.api.IArtWorkApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin("*")
public class ArtWorkImpl implements IArtWorkApi {

  @Autowired
  private ArtworkService artworkService;

  @Autowired
  private ArtworkMapper artworkMapper;

  private HttpStatus httpStatus;

  @Override
  public ResponseEntity<?> searchArtworks(String query) {
    try {
      List<Artwork> artworks = artworkService.searchArtworks(query);
      if (artworks.isEmpty()) {
        return new ResponseEntity<>("No artworks found", HttpStatus.NO_CONTENT);
      }
      List<ArtworkDto> artworkDtos = artworkMapper.toDtos(artworks);
      return new ResponseEntity<>(artworkDtos, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public ResponseEntity<?> getAllArtworks(int page, int limit) {
    try {
      List<Artwork> artworks = artworkService.getAllArtworks(page, limit);
      List<ArtworkDto> artworkDtos = artworkMapper.toDtos(artworks);
      return new ResponseEntity<>(artworkDtos, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public ResponseEntity<?> getArtworkById(int id) {
    try {
      Artwork artwork = artworkService.getArtworkById(id);
      ArtworkDto artworkDto = artworkMapper.toDto(artwork);
      if (artwork != null) {
        return new ResponseEntity<>(artworkDto, HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Artwork not found", HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
