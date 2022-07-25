import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
//        String url = ("http://imdb-api.com/en/API/Top250Movies/k_gioc78zk");
//        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        //String url = ("https://api.nasa.gov/planetary/apod?api_key=RhL2omY8EO2BPnSU8UmSCOfLvzYhIBVjEoEvFKbU&start_date=2022-06-12&end_date=2022-06-14");
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        String url = ("http://localhost:8080/linguagens");
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < 3; i++){
            Conteudo conteudo = conteudos.get(i);
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo()+".png";
            geradora.criar(inputStream, nomeArquivo);
            System.out.println("\u001b[38;5;15mTitulo:\u001b[0m " + "\u001b[38;5;2m" + conteudo.getTitulo());
        }
    }
}