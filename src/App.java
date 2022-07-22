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

        //fazer uma conexão http e buscar os top 250 filmes.
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //extrair somente os dados que interesam (titulo, imagem, classificação).
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        System.out.println(listaDeFilmes.size());

        //exibir e manipular os dados.
        var geradora = new GeradoraDeFigurinhas();

        for (Map<String,String> filme : listaDeFilmes) {

            String urlImage = filme.get("image");
            String title = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            var nomeArquivo = title + ".png";

            geradora.cria(inputStream, nomeArquivo);


            System.out.println(title);
            System.out.println();
        }
    }
}
