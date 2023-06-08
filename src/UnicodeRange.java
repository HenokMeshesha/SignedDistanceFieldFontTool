public enum UnicodeRange {


    BasicLatin(33, 127, "Basic Latin", false), Latin1Supplement(128, 255, "Latin-1 Supplement", false),
    LatinExtendedA(256, 383, "Latin Extended-A", false), LatinExtendedB(384, 592, "Latin Extended-B", false),
    IPAExtensions(592, 687, "IPA Extensions", false), SpacingModifierLetters(688, 767, "Spacing Modifier Letters", false),
    CombiningDiacriticalMarks(768, 879, "Combining Diacritical Marks", false), Greek(880, 1023, "Greek", false),
    Cyrillic(1024, 1279, "Cyrillic", false), Armenian(1328, 1423, "Armenian", false),
    Hebrew(1424, 1535, "Hebrew", false), Arabic(1536, 1791, "Arabic", false),
    Syriac(1792, 1871, "Syriac", false), Thaana(1920, 1983, "Thaana", false),
    Devanagari(2304, 2431, "Devanagari", false), Bengali(2432, 2559, "Bengali", false),
    Gurmukhi(2560, 2687, "Gurmukhi", false), Gujarati(2688, 2815, "Gujarati", false),
    Oriya(2816, 2943, "Oriya", false), Tamil(2944, 3071, "Tamil", false),
    Telugu(3072, 3199, "Telugu", false), Kannada(3200, 3327, "Kannada", false),
    Malayalam(3328, 3455, "Malayalam", false), Sinhala(3456, 3583, "Sinhala", false),
    Thai(3584, 3711, "Thai", false), Lao(3712, 3839, "Lao", false),
    Tibetan(3840, 4095, "Tibetan", false), Myanmar(4096, 4255, "Myanmar", false),
    Georgian(4256, 4351, "Georgian", false), HangulJamo(4352, 4607, "Hangul Jamo", false),
    Ethiopic(4608, 4991, "Ethiopic", false), Cherokee(5024, 5119, "Cherokee", false),
    UnifiedCanadianAboriginalSyllabics(5120, 5759, "Unified Canadian Aboriginal Syllabics", false),
    Ogham(5760, 5791, "Ogham", false), Runic(5792, 5887, "Runic", false),
    Khmer(6016, 6143, "Khmer", false), Mongolian(6144, 6319, "Mongolian", false),
    LatinExtendedAdditional(7680, 7935, "Latin Extended Additional", false),
    GreekExtended(7936, 8191, "Greek Extended", false),
    GeneralPunctuation(8192, 8303, "General Punctuation", false),
    SuperscriptsandSubscripts(8304, 8351, "Superscripts and Subscripts", false),
    CurrencySymbols(8352, 8399, "Currency Symbols", false),
    CombiningMarksforSymbols(8400, 8447, "Combining Marks for Symbols", false),
    LetterlikeSymbols(8448, 8527, "Letterlike Symbols", false),
    NumberForms(8528, 8591, "Number Forms", false), Arrows(8592, 8703, "Arrows", false),
   MathematicalOperators(8704, 8959, "Mathematical Operators", false),
    MiscellaneousTechnical(8960, 9215, "Miscellaneous Technical", false),
    ControlPictures(9216, 9279, "Control Pictures", false),
    OpticalCharacterRecognition(9280, 9311, "Optical Character Recognition", false),
    EnclosedAlphanumerics(9312, 9471, "Enclosed Alphanumerics", false),
    BoxDrawing(9472, 9599, "Box Drawing", false), BlockElements(9600, 9631, "Block Elements", false),
    GeometricShapes(9632, 9727, "Geometric Shapes", false),
    MiscellaneousSymbols(9728, 9983, "Miscellaneous Symbols", false),
    Dingbats(9984, 10175, "Dingbats", false), BraillePatterns(10240, 10495, "Braille Patterns", false),
    CJK_RadicalsSupplement(11904, 12031, "CJK Radicals Supplement", false),
    KangxiRadicals(12032, 12255, "Kangxi Radicals", false),
    IdeographicDescriptionCharacters(12272, 12287, "Ideographic Description Characters", false),
    CJK_SymbolsandPunctuation(12288, 12351, "CJK Symbols and Punctuation", false),
    Hiragana(12352, 12447, "Hiragana", false), Katakana(12448, 12543, "Katakana", false),
    Bopomofo(12544, 12591, "Bopomofo", false),
    HangulCompatibilityJamo(12592, 12687, "Hangul Compatibility Jamo", false),
    Kanbun(12688, 12703, "Kanbun", false), BopomofoExtended(12704, 12735, "Bopomofo Extended", false),
    EnclosedCJK_LettersandMonths(12800, 13055, "Enclosed CJK Letters and Months", false),
    CJK_Compatibility(13056, 13311, "CJK Compatibility", false),
    CJK_UnifiedIdeographsExtensionA(13312, 19893, "CJK Unified Ideographs Extension A", false),
    CJK_UnifiedIdeographs(19968, 40959, "CJK Unified Ideographs", false),
    YiSyllables(40960, 42127, "Yi Syllables", false), YiRadicals(42128, 42191, "Yi Radicals", false),
    HangulSyllables(44032, 55203, "Hangul Syllables", false),
    HighSurrogates(55296, 56191, "High Surrogates", false),
    HighPrivateUseSurrogates(56192, 56319, "High Private Use Surrogates", false),
    LowSurrogates(56320, 57343, "Low Surrogates", false), PrivateUse(57344, 63743, "Private Use", false),
    CJK_CompatibilityIdeographs(63744, 64255, "CJK Compatibility Ideographs", false),
    AlphabeticPresentationForms(64256, 64335, "Alphabetic Presentation Forms", false),
    ArabicPresentationForms_A(64336, 65023, "Arabic Presentation Forms-A", false),
    CombiningHalfMarks(65056, 65071, "Combining Half Marks", false),
    CJK_CompatibilityForms(65072, 65103, "CJK Compatibility Forms", false),
    SmallFormVariants(65104, 65135, "Small Form Variants", false),
    ArabicPresentationForms_B(65136, 65278, "Arabic Presentation Forms-B", false),
    Specials1(65279,65279,	"Specials 1", false),
    HalfwidthandFullwidthForms(65280,65519,	"Halfwidth and Fullwidth Forms", false),
    Specials2(65520,65533,	"Specials 2", false);


    public final int f;
    public final int l;
    public final String name;
    public Boolean available;

    UnicodeRange(int f, int l, String name, Boolean available) {
        this.f = f;
        this.l = l;
        this.name = name;
        this.available = available;
    }


}
