public class CaesarCipher {

    protected String cipher(String msg, int shift){

        int key = shift % 26 + 26; //sets key according to shift

        //implemented StringBuilder b/c it is faster and more efficient
        StringBuilder encryptedMsg = new StringBuilder();

        // splits up message into char array and loops through each char
        for (char i : msg.toCharArray()){

            //only changes char if it is a letter
            if (Character.isLetter(i)){

                if (Character.isUpperCase(i)){

                    encryptedMsg.append((char)(((i - 65 + key) % 26) + 65)); // 65 is A in ASCII

                } else { encryptedMsg.append((char)(((i - 97 + key) % 26) + 97));} // 97 is a in ASCII

            } else {encryptedMsg.append(i);}
        }

        return encryptedMsg.toString();
    }

    protected String decipher(String msg, int key){
        return cipher(msg, 26-key);
    }
}
