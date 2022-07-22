import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        //leitura Imagem
        //InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        //InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();

        BufferedImage ImageOrigin = ImageIO.read(inputStream);

        //cria nova imagem em memoria com transparencia e comnovo tamanho
        int width = ImageOrigin.getWidth();
        int height = ImageOrigin.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        //copiar a imagem original para nova imagem (memoria)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(ImageOrigin, null, 0, 0);

        //escrever uma frase na nova imagem
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);
        graphics.drawString( "Topzera", 0, newHeight - 100);

        //escrever a nova imagem em um arquivo
        ImageIO.write(newImage, "png", new File(nomeArquivo));
    }
}
