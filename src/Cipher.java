import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.scene.control.Label;



public class Cipher extends Application {

    private TextArea plainText = new TextArea();
    private TextArea encryptedText = new TextArea();
    private TextField plainKey = new TextField();
    private TextField cipherKey = new TextField();
    private Button encrypt = new Button("Encrypt");
    private Button decrypt = new Button("Decrypt");

    @Override
    public void start(Stage primaryStage) {

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

        encrypt.setOnAction(event ->{
            int key = Integer.parseInt(plainKey.getText());
            encryptedText.setText(cipher(plainText.getText(), key));
        });
        decrypt.setOnAction(event ->{
            int key = Integer.parseInt(cipherKey.getText());
            plainText.setText(decipher(encryptedText.getText(),key));
        });

        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Caesars Cipher");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Shift Cipher algorithm
     */
    private static String cipher(String msg, int shift){

        int key = shift % 26 + 26; //sets key according to shift

        //implemented StringBuilder b/c it is faster and more efficient
        StringBuilder encryptedMsg = new StringBuilder();

        // splits up message into char array and loops through each char
        for (char i : msg.toCharArray()){

            //only changes char if it is a letter
            if (Character.isLetter(i)){

                if (Character.isUpperCase(i)){

                    encryptedMsg.append((char)((i - 65 + key) % 26) + 65); // 65 is A in ASCII

                } else { encryptedMsg.append((char)((i - 97 + key) % 26) + 97);} // 97 is a in ASCII

            } else {encryptedMsg.append(i);}
        }

        return encryptedMsg.toString();
    }

    private static String decipher(String msg, int key){
        return cipher(msg, 26-key);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
