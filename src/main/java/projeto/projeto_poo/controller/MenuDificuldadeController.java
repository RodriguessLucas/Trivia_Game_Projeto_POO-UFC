package projeto.projeto_poo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuDificuldadeController {
    @FXML
    private Button btnIniciarJogo;

    @FXML
    private ComboBox<String> cbMenuDificuldade;




    @FXML
    private ImageView pythonImage;

    @FXML
    private ImageView javaImage;

    @FXML
    private ImageView algorithmsImage;

    @FXML
    private ImageView homeImage;

    @FXML
    private ImageView playImage;

    @FXML
    private ImageView statsImage;

    @FXML
    private ImageView settingsImage;

    @FXML
    public void initialize() {
        Image image1 = new Image(getClass().getResource("/projeto/projeto_poo/view/images/python.png").toExternalForm());
        Image image2 = new Image(getClass().getResource("/projeto/projeto_poo/view/images/java.png").toExternalForm());
        Image image3 = new Image(getClass().getResource("/projeto/projeto_poo/view/images/algorithms.png").toExternalForm());
        Image image4 = new Image(getClass().getResource("/projeto/projeto_poo/view/images/home.png").toExternalForm());
        Image image5 = new Image(getClass().getResource("/projeto/projeto_poo/view/images/play.png").toExternalForm());
        Image image6 = new Image(getClass().getResource("/projeto/projeto_poo/view/images/stats.png").toExternalForm());
        Image image7 = new Image(getClass().getResource("/projeto/projeto_poo/view/images/settings.png").toExternalForm());
        pythonImage.setImage(image1);
        javaImage.setImage(image2);
        algorithmsImage.setImage(image3);
        homeImage.setImage(image4);
        playImage.setImage(image5);
        statsImage.setImage(image6);
        settingsImage.setImage(image7);
    }




}
