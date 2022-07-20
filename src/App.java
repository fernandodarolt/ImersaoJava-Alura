import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        String key = "9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        String url = ("https://mocki.io/v1/"+key);
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        var parser = new JsonParser();
        //conexão Http
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        //pegar os dados (titulo, poster, nota)
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String, String> filme : listaDeFilmes) {
            var image = filme.get("image");
            image = image.substring(0,image.length()-32);
            String titulo = filme.get("title");
            String nomeArquivo = titulo+".png";
            InputStream inputStream = new URL(image).openStream();
            geradora.criar(inputStream, nomeArquivo);
            //imprime o nome, classificação e estrelas do filme
            System.out.println("\u001b[38;5;15mTitulo:\u001b[0m " + "\u001b[38;5;2m" + titulo);
            //System.out.println("\u001b[38;5;13mImagem:\u001b[0m " + filme.get("image"));
            //System.out.println("\u001b[38;5;15m\u001b[48;5;17mClassificação: " + filme.get("imDbRating") + "\u001b[m");
//            for (int x = 0; x < Double.valueOf(filme.get("imDbRating")).intValue(); x++) {
//                System.out.print("\u2B50");
//            }
            System.out.println();
            System.out.println();
        }
    }
}