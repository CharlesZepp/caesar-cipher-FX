import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *  A simple FX GUI that allows the user to enter text to encrypt/ decrypt using a
 *  hybrid of Caesar Cipher and DES (yes very old encryption methods)
 */

public class GUI extends Application {

    private TextArea plainText = new TextArea();
    private TextArea encryptedText = new TextArea();
    private TextField plainKey = new TextField();
    private TextField cipherKey = new TextField();
    private Button encrypt = new Button("Encrypt");
    private Button decrypt = new Button("Decrypt");

    /* create an instance of cipher tools*/
    private CaesarCipher tool = new CaesarCipher();


    @Override
    public void start(Stage primaryStage) throws Exception {

        SecretKey desKey = KeyGenerator.getInstance("DES").generateKey();
        DES des = new DES(desKey);

        //create basic layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.add(new Label("Plain Text"), 0, 0);
        gridPane.add(plainText, 0,1);

        gridPane.add(new Label("Encrypted Text"), 1, 0);
        gridPane.add(encryptedText, 1,1);

        plainKey.setMaxWidth(50);
        gridPane.add(new Label("Key"), 0, 2);
        gridPane.add(plainKey, 0,3);

        cipherKey.setMaxWidth(50);
        gridPane.add(new Label("Key"), 1, 2);
        gridPane.add(cipherKey, 1,3);

        gridPane.add(encrypt,0,4);
        gridPane.add(decrypt, 1,4);

        //implement tool methods on button clicks
        encrypt.setOnAction(event ->{
            int key = Integer.parseInt(plainKey.getText());
            String caesar = tool.cipher(plainText.getText(), key);

            try {
                String desE = des.encrypt(caesar);
                encryptedText.setText(desE);
            } catch (Exception e){
                System.out.println(e);
            }
        });
        decrypt.setOnAction(event ->{
            int key = Integer.parseInt(cipherKey.getText());
            try {
                String desD = des.decrypt(encryptedText.getText());
                plainText.setText(tool.decipher(desD, key));
            } catch (Exception e){
                System.out.println(e);
            }

        });


        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Caesars GUI");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
