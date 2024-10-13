package org.art.service;

import org.art.exception.ResourceNotFoundException;
import org.art.model.Artwork;
import org.art.model.Thumbnail;
import org.art.response.ApiResponse;
import org.art.response.ApiResponseGetId;
import org.art.util.ModelsMockGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ArtworkServiceTest {

  @Autowired
  private ArtworkService artworkService;

  @MockBean
  private RestTemplate restTemplate;


  @Value("${API_URL}")
  private String API_URL;
  @Value("${API_URL_GET}")
  private String API_URL_GET;
  @Value("${API_URL_BY_ID}")
  private String API_URL_BY_ID;


  @Test
  void testGetArtworkById_Should_Be_Return_Success() {
    Artwork artwork = ModelsMockGenerator.createNewArtworks();
    final String URL = API_URL_BY_ID + artwork.getId();  // URL to fetch the artwork by ID

    // Simulate the response from RestTemplate
    //GIVEN
    ApiResponseGetId mockResponse = new ApiResponseGetId();  // Create a mock response object
    mockResponse.setData(artwork);  // Set the mock response data to the artwork object

    // Mock the RestTemplate call
    //WHEN
    Mockito.doReturn(mockResponse).when(restTemplate).getForObject(URL, ApiResponseGetId.class);  // Mock the behavior of RestTemplate

    // Assert that the mock response is not null
    Assertions.assertNotNull(mockResponse, "Mock response should not be null"); // Check that the mock response is not null

    // Call the service to get the result
    //THEN
    Artwork result = artworkService.getArtworkById(artwork.getId());  // Fetch the artwork by ID using the service

    // Use Assertions.assertAll() to validate multiple properties at once
    Assertions.assertAll("Artwork Properties",
      () -> Assertions.assertNotNull(result, "Artwork should not be null"),  // Assert that the result is not null
      () -> Assertions.assertEquals(artwork.getId(), result.getId(), "Artwork ID should be 4"),  // Assert that the ID matches
      () -> Assertions.assertEquals(artwork.getTitle(), result.getTitle(), "Title should be 'Priest and Boy'"),  // Assert that the title matches
      () -> Assertions.assertEquals(artwork.getApi_link(), result.getApi_link(), "API link mismatch"),  // Assert that the API link matches
      () -> Assertions.assertFalse(result.getIs_boosted(), "Artwork should not be boosted"),  // Assert that the artwork is not boosted
      () -> Assertions.assertEquals("artworks", result.getApi_model(), "API model should be 'artworks'"),  // Assert that the API model matches
      () -> Assertions.assertEquals(null, result.get_score(), "Score mismatch"),  // Assert that the score matches
      () -> Assertions.assertEquals(artwork.getTimestamp(), result.getTimestamp(), "Timestamp mismatch")  // Assert that the timestamp matches
    );
  }

  @Test
  void testGetArtworkById_Should_Be_Return_Not_Found() {
    // Constants
    final int TEST_ID = 999;  // ID that does not correspond to any artwork
    final String URL = API_URL_BY_ID + TEST_ID;  // URL for the artwork that is not found

    //WHEN
    // Simulate the behavior of RestTemplate to return null or throw an exception
    Mockito.when(restTemplate.getForObject(URL, ApiResponseGetId.class))
      .thenThrow(new ResourceNotFoundException("Artwork not found"));


    // Call the service to get the result and verify that it throws the expected exception
    Assertions.assertThrows(ResourceNotFoundException.class, () -> {
      artworkService.getArtworkById(TEST_ID);  // Attempt to fetch an artwork that does not exist
    }, "Expected ResourceNotFoundException when artwork is not found");
  }

  @Test
  void testGetAllArtworks_Should_Return_Success() {
    // Constants
    Artwork artwork1 = new Artwork(); // Set properties
    artwork1.setId(1);
    artwork1.setTitle("Artwork 1");

    Artwork artwork2 = new Artwork(); // Set properties
    artwork2.setId(2);
    artwork2.setTitle("Artwork 2");

    // Simulate successful response
    //GIVEN
    ApiResponse mockResponse = new ApiResponse();
    mockResponse.setData(List.of(artwork1, artwork2));

    //WHEN
    Mockito.when(restTemplate.getForObject(API_URL, ApiResponse.class))
      .thenReturn(mockResponse);

    // Call the service method
    //THEN
    List<Artwork> result = artworkService.getAllArtworks(1, 10);

    // Assertions
    Assertions.assertNotNull(result);
    Assertions.assertEquals(10, result.size());
    Assertions.assertEquals("Starry Night and the Astronauts", result.get(0).getTitle());
    Assertions.assertEquals("The Bedroom", result.get(1).getTitle());
  }

  @Test
  void testGetAllArtworks_Should_Return_Empty_List_When_No_Data() {
    // GIVEN
    ApiResponse mockResponse = new ApiResponse();
    mockResponse.setData(Collections.emptyList()); // Simule une réponse vide

    Mockito.when(restTemplate.getForObject(API_URL, ApiResponse.class))
      .thenReturn(mockResponse); // Mock la réponse de l'API

    // WHEN
    List<Artwork> result = artworkService.getAllArtworks(0, 0); // Appelle le service

    // THEN
    Assertions.assertNotNull(result, "The list should not be null.");
    Assertions.assertTrue(!result.isEmpty(), "The list should be empty when there is no data."); // Assertion
  }


  @Test
  void testSearchArtworks_Should_Return_ArtworkList_When_DataExists() {
    // GIVEN
    String query = "monet";
    ApiResponse mockResponse = new ApiResponse();
    Thumbnail mockThumbnail = new Thumbnail("Painting of a pond seen up close spotted with thickly painted pink and white water lilies and a shadow across the top third of the picture.", "data:image/gif;base64,R0lGODlhBQAFAPQAAEZcaFFfdVtqbk9ldFBlcVFocllrcFlrd11rdl9sdFZtf15wcWV0d2R2eGByfmd6eGl6e2t9elZxiGF4kWB4kmJ9kGJ8lWeCkWSAnQAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAAAAAAALAAAAAAFAAUAAAUVoJBADXI4TLRMWHU9hmRRCjAURBACADs=", 8808, 8460);

    List<Artwork> artworks = new ArrayList<>();
    artworks.add(new Artwork(16568, "Water Lilies", "https://api.artic.edu/api/v1/artworks/16568", true, "artworks", 216.63153f, "2024-10-17T18:40:28-05:00", mockThumbnail));
    mockResponse.setData(artworks);

    // WHEN
    Mockito.when(restTemplate.getForObject(API_URL + query, ApiResponse.class))
      .thenReturn(mockResponse);

    // THEN
    List<Artwork> result = artworkService.searchArtworks(query);
    Assertions.assertNotNull(result);
    Assertions.assertFalse(result.isEmpty());
    Assertions.assertEquals(10, result.size());

    // Verify artwork fields
    Artwork artwork = result.get(0);
    Assertions.assertEquals(16568, artwork.getId());
    Assertions.assertEquals("Water Lilies", artwork.getTitle());
    Assertions.assertEquals("https://api.artic.edu/api/v1/artworks/16568", artwork.getApi_link());
    Assertions.assertTrue(artwork.getIs_boosted());
    Assertions.assertEquals("artworks", artwork.getApi_model());
    Assertions.assertEquals(216.63153f, artwork.get_score());
    Assertions.assertEquals("2024-10-17T18:40:28-05:00", artwork.getTimestamp());

    // Verify thumbnail fields
    Assertions.assertNotNull(artwork.getThumbnail());
    Assertions.assertEquals("Painting of a pond seen up close spotted with thickly painted pink and white water lilies and a shadow across the top third of the picture.", artwork.getThumbnail().getAlt_text());
    Assertions.assertEquals("data:image/gif;base64,R0lGODlhBQAFAPQAAEZcaFFfdVtqbk9ldFBlcVFocllrcFlrd11rdl9sdFZtf15wcWV0d2R2eGByfmd6eGl6e2t9elZxiGF4kWB4kmJ9kGJ8lWeCkWSAnQAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAAAAAAALAAAAAAFAAUAAAUVoJBADXI4TLRMWHU9hmRRCjAURBACADs=", artwork.getThumbnail().getLqip());
    Assertions.assertEquals(8808, artwork.getThumbnail().getWidth());
    Assertions.assertEquals(8460, artwork.getThumbnail().getHeight());
  }
}
