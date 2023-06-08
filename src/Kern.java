import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kern {
    private static List<KerningPair> getFontKerningPairsOneChar(List<Character> availableChars, Font font, char firstChar) {
        List<KerningPair> ret = new ArrayList<>();

        char[] chars = new char[availableChars.size() * 2];

        for (int i = 0; i < availableChars.size(); i++) {
            chars[i * 2] = firstChar;
            chars[i * 2 + 1] = availableChars.get(i);
        }

        Map<AttributedCharacterIterator.Attribute, Object> withKerningAttrs = new HashMap<>();

        withKerningAttrs.put(TextAttribute.FONT, font);
        withKerningAttrs.put(TextAttribute.KERNING,
                TextAttribute.KERNING_ON);
        Font withKerningFont = Font.getFont(withKerningAttrs);
        GlyphVector withKerningVector = withKerningFont.layoutGlyphVector(
                getFontRenderContext(withKerningFont), chars, 0,
                chars.length, Font.LAYOUT_LEFT_TO_RIGHT);
        int[] withKerningX = new int[availableChars.size()];
        for (int i = 0; i < availableChars.size(); i++) {
            withKerningX[i] = withKerningVector.getGlyphLogicalBounds(
                    i * 2 + 1).getBounds().x;
        }

        Map<AttributedCharacterIterator.Attribute, Object> noKerningAttrs = new HashMap<>();
        noKerningAttrs.put(TextAttribute.FONT, font);
        noKerningAttrs.put(TextAttribute.KERNING, 0);
        Font noKerningFont = Font.getFont(noKerningAttrs);
        GlyphVector noKerningVector = noKerningFont.layoutGlyphVector(
                getFontRenderContext(noKerningFont), chars, 0,
                chars.length, Font.LAYOUT_LEFT_TO_RIGHT);
        for (int i = 0; i < availableChars.size(); i++) {
            int noKerningX = noKerningVector.getGlyphLogicalBounds(
                    i * 2 + 1).getBounds().x;
            int kerning = withKerningX[i] - noKerningX;
            if (kerning > 0) {
                ret.add(new KerningPair(firstChar, availableChars.get(i),
                        kerning));
            }
        }
        return ret;
    }
    private static FontRenderContext getFontRenderContext(Font font) {
        return (new JPanel()).getFontMetrics(font).getFontRenderContext();
    }


    static class KerningPair {
        public char firstCodePoint;
        public char secondCodePoint;
        public int kerning;

        public KerningPair(Character firstChar, Character secondChar, int kerning) {
            firstCodePoint = firstChar;
            secondCodePoint = secondChar;
            this.kerning = kerning;

        }

//        KerningPair() {
//        }
    }
}