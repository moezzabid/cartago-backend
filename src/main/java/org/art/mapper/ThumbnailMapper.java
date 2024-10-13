package org.art.mapper;


import org.art.dto.ArtworkDto;
import org.art.dto.ThumbnailDto;
import org.art.model.Artwork;
import org.art.model.Thumbnail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})

public interface ThumbnailMapper {

  ThumbnailDto toDto(Thumbnail thumbnail);

  Thumbnail toThumbnail(ThumbnailDto thumbnailDto);
}
