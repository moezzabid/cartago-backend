package org.art.mapper;


import org.art.dto.ArtworkDto;
import org.art.model.Artwork;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ThumbnailMapper.class})

public interface ArtworkMapper {

  ArtworkDto toDto(Artwork artwork);

  Artwork toArtwork(ArtworkDto artworkDto);

  default List<ArtworkDto> toDtos(List<Artwork> artworks) {
    if (artworks == null || artworks.isEmpty()) {
      return List.of();
    }
    return artworks.stream().map(this::toDto
    ).collect(Collectors.toList());
  }

}
