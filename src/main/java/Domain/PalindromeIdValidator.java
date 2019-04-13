package Domain;

public class PalindromeIdValidator {
    public static boolean isPalindrome(String id){
        char[] charId = id.toCharArray();
        boolean palindrom = false;
        int i1 = 0;
        int i2 = charId.length - 1;
        while (i2 > i1) {
            if (charId[i1] != charId[i2]) {
                return false;
            }
            ++i1;
            --i2;
        }
        return true;
    }
}
