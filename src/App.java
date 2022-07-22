import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //fazer uma conexão http e buscar os top 250 filmes.
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        //extrair somente os dados que interesam (titulo, imagem, classificação).
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(json);
        System.out.println(listaDeFilmes.size());

        //exibir e manipular os dados.
        var geradora = new GeradoraDeFigurinhas();

        for (Map<String,String> filme : listaDeFilmes) {

            String urlImage = filme.get("image");
            String title = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            var nomeArquivo = "saida/" + title + ".png";

            geradora.cria(inputStream, nomeArquivo);


            System.out.println(title);
            System.out.println();
        }
    }
}
