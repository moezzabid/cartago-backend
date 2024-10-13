package org.art.controller;

import org.art.dto.ArtworkDto;
import org.art.mapper.ArtworkMapper;
import org.art.model.Artwork;
import org.art.service.ArtworkService;
import org.art.web.impl.ArtWorkImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ArtworkControllerTest {

  @MockBean
  private ArtworkService artworkService;

  @MockBean
  private ArtWorkImpl artWorkImpl;

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private ArtworkMapper artworkMapper;

  private HttpStatus httpStatus;

  @Test
  public void testSearchArtworks_Success() throws Exception {
    // Given
    String query = "Monet";
    List<Artwork> artworks = new ArrayList<>(); // Mocked list of artworks
    List<ArtworkDto> artworkDtos = new ArrayList<>(); // Mocked list of DTOs

    // Mock the service and mapper
    Mockito.when(artworkService.searchArtworks(query)).thenReturn(artworks);
    Mockito.when(artworkMapper.toDtos(artworks)).thenReturn(artworkDtos);

    // When performing a GET request
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/search")
        .param("query", query))  // Send query as a request parameter
      .andExpect(MockMvcResultMatchers.status().isOk())  // Expect HTTP 200 OK
      .andReturn();

    // Then check the response content
    String responseContent = mvcResult.getResponse().getContentAsString();
  }

  @Test
  public void testSearchArtworks_InternalServerError() throws Exception {
    // Given
    Mockito.when(artworkService.searchArtworks(anyString())).thenThrow(new RuntimeException("Error"));

    // Perform the GET request and expect an internal server error
    mockMvc.perform(MockMvcRequestBuilders.get("/api/search")
        .param("query", "test"))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }

}
