import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {
    public void cria(InputStream inputStream, String fileName) throws Exception {

        // Leitura da Imagem
        // InputStream inputStream = new FileInputStream(new File("assets/TopMovies_7.jpg"));
        // InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_8.jpg").openStream();

        BufferedImage originalImage = ImageIO.read(inputStream);

        // Criar nova imagem em memória com transparencia e tamanho
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        
    
        // Copiar imagem original para a nova em memória
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // Configurar a fonte
        var font =  new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        // Escrever uma frase na nova imagem
        graphics.drawString("Filmasso!", 100, newHeight - 100);

        // Escrever nova imagem em um arquivo
        ImageIO.write(newImage, "png", new File("assets/" + fileName));
    }
}
