/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cesar
 */
public class Imagem {
    public static BufferedImage getRedimensionada(BufferedImage i){
        BufferedImage newImage = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(i, 0, 0, 300, 300, null);

        return newImage;
    }
}
