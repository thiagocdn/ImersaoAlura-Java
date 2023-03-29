import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP para buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String responseBody = response.body();

        // Extrair somente os dados de interesse (Título, Poster, Avaliação) 
        var parser = new JsonParser();
        List<Map<String, String>> filmsList = parser.parse(responseBody);

        // Exibir e manipular os dados
        for (Map<String,String> film : filmsList) {
            String urlImage = film.get("image");
            String title = film.get("title") + ".png";
            InputStream inputStream = new URL(urlImage).openStream();

            var generator = new GeradorDeFigurinhas();
            generator.cria(inputStream, title);
        }
    }
}
