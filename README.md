First, I started by reviewing the API documentation and identifying the methods I wanted to consume, as well as the attributes needed.

Then, I created two classes: ApiResponse and ApiResponseGetId to handle the API responses—one for the getAllArtworks and search functionality, and the other for the getById operation.

I created two entities, Artwork and Thumbnail, using Lombok to automatically generate constructors, getters, and setters.

Following that, I developed a service class, ArtworkService, which contains all the necessary services for interacting with the API.

I proceeded to write unit tests for each API to cover all possible test cases.

Afterward, I created two DTO classes to define the attributes I wanted to display in the application.

I also created two mapping interfaces, ArtworkMapper and ThumbnailMapper, to map the Artwork entity to its corresponding DTO.

Next, I created an interface that defines the methods I want to consume, including the necessary Swagger parameters.

In addition, I developed a Swagger configuration class.

Finally, I implemented the API logic by creating a class, ArtworkImpl, which implements the interface and contains the API method implementations.
