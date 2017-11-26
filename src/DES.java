import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DES {

    private Cipher ecipher;
    private Cipher dcipher;

    protected DES(SecretKey key) throws Exception{
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }

    protected String encrypt(String msg) throws Exception{
        byte[] byteMsg = msg.getBytes("UTF8");
        byte[] encryptedMsg = ecipher.doFinal(byteMsg);
        return new sun.misc.BASE64Encoder().encode(encryptedMsg);
    }

    protected String decrypt(String msg) throws Exception {
        byte[] byteMsg = new sun.misc.BASE64Decoder().decodeBuffer(msg);
        byte[] decryptedMsg = dcipher.doFinal(byteMsg);
        return new String(decryptedMsg, "UTF8");
    }

    public static void main(String[] args) throws Exception {

        SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        DES des = new DES(key);
        String msg = "hello world";

        String x = des.encrypt(msg);
        System.out.println(x);
        System.out.println(des.decrypt(x));

    }
}
