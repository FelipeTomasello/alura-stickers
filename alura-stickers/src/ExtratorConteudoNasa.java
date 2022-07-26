import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa implements ExtratorConteudo {
    
    public List<Conteudo> extraiConteudos (String json) {
         //extrair somente os dados que interesam (titulo, imagem, classificação).
         var parser = new JsonParser();
         List<Map<String, String>> listaDeatributos = parser.parse(json);
         List<Conteudo> conteudos = new ArrayList<>();
         //popular a lista de conteudos
         for (Map<String, String> atributos: listaDeatributos) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("url");

            var conteudo = new Conteudo(titulo, urlImagem);
            conteudos.add(conteudo);
         }
         return conteudos;
    }
}
