import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradoraDeFigurinhas {

    public void criar(InputStream inputStream, String nomeArquivo) throws Exception {
        //leitura da imagem
        //InputStream inputStream = new URL("").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        //modificar imagem
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = (altura+200);
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        //salvar nova imagem memoria
        Graphics2D graphics = novaImagem.createGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);
        //escrever na imagem
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(fonte);
        graphics.setColor(Color.DARK_GRAY);
        graphics.drawString("#Alura Sticker", novaImagem.getWidth()/3, (novaAltura-75));
        //salvar imagem em arquivo
        ImageIO.write(novaImagem, "png", new File("saida/"+nomeArquivo));
    }
}
