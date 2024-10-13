package org.art.util;

import org.art.model.Artwork;

public class ModelsMockGenerator {

  public static Artwork createNewArtworks() {

    // Constants
    final int TEST_ID = 4;  // The test ID for the artwork
    final String TEST_TITLE = "Priest and Boy";  // Expected title of the artwork
    final String TEST_API_LINK = "https://api.artic.edu/api/v1/artworks/4";  // Expected API link for the artwork
    final String TEST_TIMESTAMP = "2024-10-17T18:41:13-05:00";  // Expected timestamp of the artwork

    // Create an instance of Artwork
    Artwork artwork = new Artwork();  // Create a new Artwork object
    artwork.setId(TEST_ID);  // Set the ID of the artwork
    artwork.setTitle(TEST_TITLE);  // Set the title of the artwork
    artwork.setApi_link(TEST_API_LINK);  // Set the API link of the artwork
    artwork.setIs_boosted(false);  // Set the boosted status of the artwork
    artwork.setApi_model("artworks");  // Set the API model of the artwork
    artwork.set_score(95.5f);  // Set the score of the artwork
    artwork.setTimestamp(TEST_TIMESTAMP);  // Set the timestamp of the artwork

    return artwork;

  }
}
