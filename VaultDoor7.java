import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

class VaultDoor7 {
    public static void main(String args[]) {
        VaultDoor7 vaultDoor = new VaultDoor7();
        Scanner scanner = new Scanner(System.in);
        vaultDoor.ArrToPassWord();
        System.out.print("Enter vault password: ");
        String userInput = scanner.next();
        String input = userInput.substring("picoCTF{".length(),userInput.length()-1);
        if (vaultDoor.checkPassword(input)) {
            System.out.println("Access granted.");
        } else {
            System.out.println("Access denied!");
        }
    }

    // Each character can be represented as a byte value using its
    // ASCII encoding. Each byte contains 8 bits, and an int contains
    // 32 bits, so we can "pack" 4 bytes into a single int. Here's an
    // example: if the hex string is "01ab", then those can be
    // represented as the bytes {0x30, 0x31, 0x61, 0x62}. When those
    // bytes are represented as binary, they are:
    //
    // 0x30: 00110000
    // 0x31: 00110001
    // 0x61: 01100001
    // 0x62: 01100010
    //
    // If we put those 4 binary numbers end to end, we end up with 32
    // bits that can be interpreted as an int.
    //
    // 00110000001100010110000101100010 -> 808542562
    //
    // Since 4 chars can be represented as 1 int, the 32 character password can
    // be represented as an array of 8 ints.
    //
    // - Minion #7816
    public int[] passwordToIntArray(String hex) {
        int[] x = new int[8];
        byte[] hexBytes = hex.getBytes();
        for (int i=0; i<8; i++) {
            x[i] = hexBytes[i*4]   << 24
                    | hexBytes[i*4+1] << 16
                    | hexBytes[i*4+2] << 8
                    | hexBytes[i*4+3];
        }
        return x;
    }

    public void ArrToPassWord() {
        int[] arr = new int[8];

        arr[0] = 1096770097; // "Hello" trong ASCII
        arr[1] = 1952395366; // " World" trong ASCII
        arr[2] = 1600270708;
        arr[3] = 1601398833;
        arr[4] = 1716808014;
        arr[5] = 1734304867;
        arr[6] = 942695730;
        arr[7] = 942748212;

        byte[] asciiBytes = new byte[arr.length * 4];

        // Chuyển các giá trị int thành byte
        for (int i = 0; i < arr.length; i++) {
            asciiBytes[i * 4+ 3] = (byte) (arr[i] & 0xFF);
            asciiBytes[i * 4 + 2] = (byte) (arr[i] >> 8 & 0xFF);
            asciiBytes[i * 4 + 1] = (byte) (arr[i] >> 16 & 0xFF);
            asciiBytes[i * 4 ] = (byte) (arr[i] >> 24 & 0xFF);
        }

        // Chuyển byte array thành String (ASCII)
        String result = new String(asciiBytes);

        // In ra kết quả
        System.out.println(result);
    }



    public boolean checkPassword(String password) {
        if (password.length() != 32) {
            return false;
        }
        int[] x = passwordToIntArray(password);
        return x[0] == 1096770097
                && x[1] == 1952395366
                && x[2] == 1600270708
                && x[3] == 1601398833
                && x[4] == 1716808014
                && x[5] == 1734304867
                && x[6] == 942695730
                && x[7] == 942748212;
    }
}