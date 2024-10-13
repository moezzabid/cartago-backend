package org.art.service;

import org.art.exception.ResourceNotFoundException;
import org.art.model.Artwork;
import org.art.response.ApiResponse;
import org.art.response.ApiResponseGetId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ArtworkService {

  @Value("${API_URL}")
  private  String API_URL;
  @Value("${API_URL_GET}")
  private  String API_URL_GET;
  @Value("${API_URL_BY_ID}")
  private  String API_URL_BY_ID;

  //service for search artworks
  public List<Artwork> searchArtworks(String query) {
    //rest template for calling the API
    RestTemplate restTemplate = new RestTemplate();
    String url = API_URL + query;

    try {
      ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);
      if (response != null && response.getData() != null) {
        return response.getData();
      }
    } catch (HttpClientErrorException e) {
      System.err.println("Erreur lors de l'appel à l'API : " + e.getMessage());
    }
    return new ArrayList<>(); // return when list is empty from api
  }

  //service for get All Artworks
  public List<Artwork> getAllArtworks(int page, int limit) {
    RestTemplate restTemplate = new RestTemplate();
    String urlWithParams = API_URL + "?page=" + page + "&limit=" + limit;
    ApiResponse response = restTemplate.getForObject(urlWithParams, ApiResponse.class);
    if (response != null && response.getData() != null) {
      return response.getData();
    }
    return Collections.emptyList();
  }

  public Artwork getArtworkById(int id) {
    RestTemplate restTemplate = new RestTemplate();
    String url = String.format(API_URL_BY_ID, id);

    try {
      ApiResponseGetId response = restTemplate.getForObject(url, ApiResponseGetId.class);
      if (response == null || response.getData() == null) {
        throw new ResourceNotFoundException("Artwork not found for ID: " + id);
      }
      return response.getData();
    } catch (HttpClientErrorException e) {
      if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
        throw new ResourceNotFoundException("Artwork not found for ID: " + id);
      }
      throw e; // rethrow other HttpClientErrorExceptions
    }
  }


}
