import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphMetrics;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class SDF_Util {
    public static BufferedImage rasterCharacters(Font font, int f_sz, int pad, String s, int atlas_width, int atlas_height) {
        // Font f = new Font("Arial", Font.PLAIN, (int)Math.ceil(32. * 1.3333333333));
        Font f = font.deriveFont(Font.PLAIN, (int) Math.ceil(f_sz * 1.3333333333));

        BufferedImage target = new BufferedImage(atlas_width, atlas_height, BufferedImage.TYPE_4BYTE_ABGR);

        // for every cha in s
        int x_move = pad;
        int y_move = pad;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);


            BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics g = img.getGraphics();
            g.setFont(f);
            FontRenderContext frc = g.getFontMetrics().getFontRenderContext();

            Rectangle2D rect = f.getStringBounds(c + "", frc);

            int b_height = (int) Math.ceil(rect.getHeight());
            int b_width = (int) Math.ceil(rect.getHeight());

            Graphics2D g2 = (Graphics2D) target.getGraphics();
            g2.setFont(f);
            g2.setColor(Color.WHITE);
            g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            char[] chars = {c};
       GlyphVector charVector = f.layoutGlyphVector(frc, chars, 0, 1, 0);
        GlyphMetrics charMetrics = charVector.getGlyphMetrics(0);
         b_width = (int) charMetrics.getAdvanceX();
         //b_height =  (int) charMetrics.getAdvanceY() + (int) charMetrics.;


            g2.drawString("" + c, x_move, y_move + b_height);

            x_move = x_move + pad + b_width;
            if (x_move + 2*b_width >= atlas_width) {
                y_move = y_move + b_height + pad;
                x_move = pad;
            }

        }

        try {
            ImageIO.write(target, "png", new File("out/production/DistanceFieldFontApp/image.png"));
        } catch (Exception ignored) {

        }

        return target;
    }
}
