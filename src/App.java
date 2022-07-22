import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // make an HTTP connection and search for the TOP 250 movies
        String url = "https://alura-imdb-api.herokuapp.com/movies";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // extract only the data we need (title, poster and classification
        JsonParser parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);

        // show and manipulate data
        for (Map<String, String> movie : moviesList) {
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));
        }
    }
}
