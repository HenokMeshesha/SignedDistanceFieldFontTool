import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


//import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;

public class Controller implements Initializable {

    @FXML
    public CheckBox cb_system;
    @FXML
    public ComboBox<String> combo_system;
    @FXML
    public CheckBox cb_font;
    @FXML
    public Button bt_font;
    @FXML
    public Button fontChooser;
    @FXML
    public CheckBox cb_svg;
    @FXML
    public Button bt_svg;
    @FXML
    public Button svg_chooser;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public TextArea txt_area;
    @FXML
    public ComboBox<String> combo_size;
    @FXML
    public ComboBox<String> combo_padding;
    @FXML
    public ComboBox<String> combo_spread;
    @FXML
    public Button gen_sdf;
    @FXML
    public ProgressBar loading_bar;
    @FXML
    public Button bt_save;
    @FXML
    public Button bt_save_as;
    @FXML
    public Button gen_raster;
    @FXML
    public ComboBox<String> combo_atls_res_w;
    @FXML
    public ComboBox<String> combo_atls_res_h;
    @FXML
    ImageView image_view;


    private Stage stage;
    public Font font;
    public static String[] font_names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        font = Font.decode(font_names[0]);
        initialLayout();
        fill_sys_font_comboBox();


    }

    private void initialLayout() {
        cb_system.setSelected(true);
        cb_font.setDisable(true);
        bt_font.setDisable(true);
        fontChooser.setDisable(true);
        cb_svg.setDisable(true);
        bt_svg.setDisable(true);
        svg_chooser.setDisable(true);
        txt_area.setWrapText(true);


        updateUnicodeRange();
        fill_font_size_combo_box();
        fill_spread_combo_box();
        fill_atls_w_h_combo_box();
        fill_padding_combo_box();


    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    private void fill_sys_font_comboBox() {


        List<String> fontList = new ArrayList<>(Arrays.asList(font_names));
        ObservableList<String> obsFontList = FXCollections.observableList(fontList);
        combo_system.setItems(obsFontList);

        combo_system.getSelectionModel().select(font_names[0]);

    }

    private void fill_font_size_combo_box() {
        List<String> glyph_size = new ArrayList<>();
        for (int i = 4; i <= 500; i++) {
            glyph_size.add("" + i);
        }
        ObservableList<String> sizes = FXCollections.observableArrayList(glyph_size);
        combo_size.setItems(sizes);
        combo_size.getSelectionModel().select("32");
    }

    private void fill_spread_combo_box() {
        List<String> spread_size = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            spread_size.add("" + i);
        }
        ObservableList<String> sizes = FXCollections.observableArrayList(spread_size);
        combo_spread.setItems(sizes);
        combo_spread.getSelectionModel().select("1");
    }

    private void fill_atls_w_h_combo_box() {
        List<String> atls_w_size = new ArrayList<>();
        for (int i = 32; i <= 2048; i *= 2) {
            atls_w_size.add("" + i);
        }
        ObservableList<String> size_w = FXCollections.observableArrayList(atls_w_size);
        combo_atls_res_w.setItems(size_w);
        combo_atls_res_w.getSelectionModel().select("512");

        List<String> atls_h_size = new ArrayList<>();
        for (int i = 32; i <= 2048; i *= 2) {
            atls_h_size.add("" + i);
        }
        ObservableList<String> size_h = FXCollections.observableArrayList(atls_h_size);
        combo_atls_res_h.setItems(size_h);
        combo_atls_res_h.getSelectionModel().select("512");
    }

    private void fill_padding_combo_box() {
        List<String> padding_size = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            padding_size.add("" + i);
        }
        ObservableList<String> sizes = FXCollections.observableArrayList(padding_size);
        combo_padding.setItems(sizes);
        combo_padding.getSelectionModel().select("1");
    }

    public void combo_sys_action(ActionEvent event) {

        font = Font.decode(font_names[combo_system.getSelectionModel().getSelectedIndex()]);
        updateUnicodeRange();
        //clear text
        txt_area.setText("");
    }

    public void gen_raster_action(ActionEvent event) {
        String fontName = font_names[combo_system.getSelectionModel().getSelectedIndex()];
        //todo move buffer out to global scope so not recalculate every time
        BufferedImage bufferedImage = SDF_Util.rasterCharacters(Font.decode(fontName),
                Integer.parseInt(combo_size.getSelectionModel().getSelectedItem()),
                Integer.parseInt(combo_padding.getSelectionModel().getSelectedItem()), txt_area.getText(),
                Integer.parseInt(combo_atls_res_w.getSelectionModel().getSelectedItem()),
                Integer.parseInt(combo_atls_res_h.getSelectionModel().getSelectedItem()));

//        for(int i = 0; i < 64; i++){
//            for(int j = 0; j< 64; j++){
//                System.out.print(bufferedImage.getRGB(i, j) + "  ");
//            }
//            System.out.println("");
//        }

        Image image = SwingFXUtils.toFXImage(bufferedImage, null);


//        int[] arr = bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null, 0, bufferedImage.getWidth());
////        int[] arr = new int[bufferedImage.getWidth()*bufferedImage.getHeight()];
////        int[] arr1 = bufferedImage.getData().getPixels(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), arr);
//        for(int i = 0; i < bufferedImage.getHeight(); i++){
//            for(int j = 0; j < bufferedImage.getWidth(); j++){
//                System.out.print(Math.abs(arr[j +i*bufferedImage.getWidth()]) > 0 ? "1" : " ");
//            }
//            System.out.println("");
//        }

        image_view.setImage(image);
    }

    public void gen_sdf_action(ActionEvent event) {
        if (!cb_system.isDisable()) {


            //System.out.println(gen_sdf("/image.png"));
            String fontName = font_names[combo_system.getSelectionModel().getSelectedIndex()];
//            BufferedImage b = SDF_Util.rasterCharacters(Font.decode(fontName),
//                    Integer.parseInt(combo_size.getSelectionModel().getSelectedItem()) * 4,
//                    4 * Integer.parseInt(combo_padding.getSelectionModel().getSelectedItem()), txt_area.getText(),
//                    4 * Integer.parseInt(combo_atls_res_w.getSelectionModel().getSelectedItem()),
//                    4 * Integer.parseInt(combo_atls_res_h.getSelectionModel().getSelectedItem()));
//            System.out.println(b.getWidth());
//            System.out.println(b.getHeight());

            BufferedImage b = null;
            try {
                b = ImageIO.read(new File("/C:\\Users\\Henok\\IdeaProjects\\DistanceFieldFontApp\\arc.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

           // Image image = SwingFXUtils.toFXImage(ffff, null);

            int[] arr = b.getRGB(0, 0, b.getWidth(), b.getHeight(), null, 0, b.getWidth());

            int dwn_scl = 4;
            int[] arr2 = gen_sdf(arr, b.getWidth(), b.getHeight(), Integer.parseInt(combo_spread.getSelectionModel().getSelectedItem()) * 4, 0, dwn_scl);

            System.out.println(arr2.length);
            System.out.println(Color.white.getRGB());

            BufferedImage bb = new BufferedImage(b.getWidth() / dwn_scl, b.getHeight() / dwn_scl, 6);
            bb.setRGB(0, 0, b.getWidth() / dwn_scl, b.getHeight() / dwn_scl, arr2, 0, b.getWidth() / dwn_scl);


            image_view.setImage(SwingFXUtils.toFXImage(bb, null));
            //System.out.println(ii.getWidth() + " " + ii.getHeight());

            try {
                ImageIO.write(bb, "png", new File("C:\\Users\\Henok\\IdeaProjects\\DistanceFieldFontApp\\arc2.png"));
            } catch (Exception ignored) {

            }


        }

    }

    private static native int[] gen_sdf(int[] pixels, int w, int h, int spread, int pad, int downScale);


    public void button(ActionEvent event) {
        System.out.println("Hello!");
    }

    public void fontFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Font");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Font File",
                        "*.ttf", "*.otf"),
                new FileChooser.ExtensionFilter("TTF", "*.ttf"),
                new FileChooser.ExtensionFilter("OTF", "*.otf")

        );

        File fileSave = fileChooser.showOpenDialog(stage);
        System.out.println("Finished");

    }

    public void cb_system_action(ActionEvent event) {

        if (cb_font.isDisable() && bt_font.isDisable() && fontChooser.isDisable() &&
                cb_svg.isDisable() && bt_svg.isDisable() && svg_chooser.isDisable()) {
            cb_system.setDisable(true);
            combo_system.setDisable(true);
            cb_font.setDisable(false);
            bt_font.setDisable(false);
            fontChooser.setDisable(false);
            cb_svg.setDisable(false);
            bt_svg.setDisable(false);
            svg_chooser.setDisable(false);

        }

        if (!cb_system.isDisable() && cb_system.isSelected()) {
            font = Font.decode(combo_system.getSelectionModel().getSelectedItem());
            System.out.println(font.getFamily());
            updateUnicodeRange();

        }

        if (!cb_system.isDisable() && cb_system.isSelected()) {

            cb_font.setDisable(true);
            bt_font.setDisable(true);
            fontChooser.setDisable(true);
            cb_svg.setDisable(true);
            bt_svg.setDisable(true);
            svg_chooser.setDisable(true);


        }
    }

    public void cb_font_action(ActionEvent event) {
        if (!cb_font.isDisable() && !cb_font.isSelected()) {
            if (cb_system.isDisable() && combo_system.isDisable()) {
                cb_system.setDisable(false);
                combo_system.setDisable(false);
                cb_font.setDisable(true);
                bt_font.setDisable(true);
                fontChooser.setDisable(true);
                cb_svg.setDisable(false);
                bt_svg.setDisable(false);
                svg_chooser.setDisable(false);


            }


        }
        if (!cb_font.isDisable() && cb_font.isSelected()) {
            cb_system.setDisable(true);
            combo_system.setDisable(true);

            cb_svg.setDisable(true);
            bt_svg.setDisable(true);
            svg_chooser.setDisable(true);

        }

    }


    public void cb_svg_action(ActionEvent event) {
        if (!cb_svg.isDisable() && cb_svg.isSelected()) {
            cb_system.setDisable(true);
            combo_system.setDisable(true);
            cb_font.setDisable(true);
            bt_font.setDisable(true);
            fontChooser.setDisable(true);
            txt_area.setDisable(true);
            scrollPane.setDisable(true);

            txt_area.setText("");

        }
        if (!cb_svg.isDisable() && !cb_svg.isSelected()) {
            cb_svg.setDisable(true);
            bt_svg.setDisable(true);
            svg_chooser.setDisable(true);
            cb_system.setDisable(false);
            combo_system.setDisable(false);
            cb_font.setDisable(false);
            bt_font.setDisable(false);
            fontChooser.setDisable(false);
            txt_area.setDisable(false);
            scrollPane.setDisable(false);

            txt_area.setText("");


        }
    }


    public void updateUnicodeRange() {

        for (UnicodeRange u : UnicodeRange.values()) u.available = false;

        for (UnicodeRange u : UnicodeRange.values()) {
            for (int i = u.f; i <= u.l; i++) {
                if (font.canDisplay(i)) {
                    u.available = true;
                    break;
                }
            }
        }


        VBox jj = (VBox) scrollPane.getContent();
        int sz = 0;
        if (jj != null) sz = jj.getChildren().size();

        System.out.println(sz + "yiuyiu");
        if (sz > 0)
            jj.getChildren().remove(0, sz);

        VBox box = null;
        if (jj == null) {
            box = new VBox();
            box.setSpacing(5);
            scrollPane.paddingProperty().setValue(new Insets(5, 5, 5, 5));
        }
        for (UnicodeRange u : UnicodeRange.values()) {
            if (u.available) {

                //Font tmpFnt = Font.decode(font_names[combo_system.getSelectionModel().getSelectedIndex()]);
                CheckBox b = new CheckBox(u.name);

                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        StringBuilder builder = new StringBuilder();
                        VBox box1 = (VBox) scrollPane.getContent();
                        int cbs_sz = box1.getChildren().size();

                        Font tmpFont = Font.decode(font_names[combo_system.getSelectionModel().getSelectedIndex()]);
                        txt_area.setFont(javafx.scene.text.Font.font(tmpFont.getFamily(), 20));

                        boolean flag = false;

                        for (int i = 0; i < cbs_sz; i++) {
                            CheckBox checkBox = (CheckBox) box1.getChildren().get(i);
                            UnicodeRange unicodeRange = UnicodeRange.BasicLatin;

                            if (checkBox.isSelected()) {
                                flag = true;
                                for (UnicodeRange ur : UnicodeRange.values()) {
                                    if (ur.name.equals(checkBox.getText())) {
                                        unicodeRange = ur;
                                        break;
                                    }
                                }


                                //assert unicodeRange != null;
                                for (int j = unicodeRange.f; j < unicodeRange.l; j++) {
                                    if (tmpFont.canDisplay(j)) builder.append(Character.toChars(j));
                                }
//                                System.out.println("Font is: " + font_names[combo_system.getSelectionModel().getSelectedIndex()]);


//                                System.out.println("Picked font " + txt_area.getFont().getFamily());
//                                //builder.append("\n\n\n");
                                System.out.println(builder.toString());
                                System.out.println(" ");
                                txt_area.setText(builder.toString());
                                //builder.delete(0, builder.toString().length());


                            }


                        }

                        if (!flag) txt_area.setText("");

                    }
                });
                if (box != null) {
                    box.getChildren().add(b);
                } else {
                    jj.getChildren().add(b);
                }
            }
        }


        if (jj == null)
            scrollPane.setContent(box);

    }

//    private String[] getSystemFontPath() {
//        String[] result;
//        if (SystemUtils.IS_OS_WINDOWS) {
//            result = new String[1];
//            String path = System.getenv("WINDIR");
//            result[0] = path + "\\" + "Fonts";
//            System.out.println(result[0]);
//            return result;
//        }
//
//        if (SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_MAC) {
//            result = new String[3];
//            result[0] = System.getProperty("user.home") + File.separator + "Library/Fonts";
//            result[1] = "/Library/Fonts";
//            result[2] = "/System/Library/Fonts";
//            return result;
//        }
//
//        if (SystemUtils.IS_OS_LINUX) {
//            String[] pathToCheck = {System.getProperty("user.home") + File.separator + ".fonts",
//                    "/usr/share/fonts/truetype",
//                    "/usr/share/fonts/TTF"};
//            ArrayList<String> lists = new ArrayList<>();
//
//            for (int i = pathToCheck.length - 1; i >= 0; i--) {
//                String path = pathToCheck[i];
//                File tmp = new File(path);
//                if (tmp.exists() && tmp.isDirectory() && tmp.canRead()) {
//                    lists.add(path);
//                }
//            }
//
//            if (lists.isEmpty()) {
//                result = new String[0];
//            } else {
//                result = new String[lists.size()];
//                result = lists.toArray(result);
//            }
//            return result;
//        }
//
//        return null;
//    }

//    private List<File> getSysFontFile() {
//        String[] extensions = new String[]{"ttf", "TTF", "TTC", "ttc", "otf", "OTF"};
//        String[] paths = getSystemFontPath();
//        ArrayList<File> files = new ArrayList<>();
//        for (int i = 0; i < Objects.requireNonNull(paths).length; i++) {
//            File fontDir = new File((paths[i]));
//            if (!fontDir.exists()) break;
//            files.addAll(FileUtils.listFiles(fontDir, extensions, true));
//        }
//        return files;
//    }

}
