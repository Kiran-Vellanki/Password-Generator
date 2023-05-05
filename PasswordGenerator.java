import java.util.Random;
import java.util.regex.Pattern;

public class PasswordGenerator {
    static final int MIN_LEN = 8;
    static final int MAX_LEN = 16;

    static final int[] nums = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    static final char[] special = new char[] { '!', '@', '#', '$', '%', '^', '&', '*', '(', ')' };

    static final char[] lower = new char[26];

    static final char[] upper = new char[26];

    static final String[] arr = new String[] { "nums", "special", "lower", "upper" };

    private static String generate() {

        String res = "";
        while (!isValid(res)) {
            res = "";
            int rand = (int) (Math.random() * (MAX_LEN - MIN_LEN + 1) + MIN_LEN);

            while (rand > 0) {
                int randArrPos = new Random().nextInt(arr.length);
                String arrName = arr[randArrPos];
                if (arrName.equals("nums")) {
                    int randPos = new Random().nextInt(nums.length);
                    res += nums[randPos];
                } else if (arrName.equals("special")) {
                    int randPos = new Random().nextInt(special.length);
                    res += special[randPos];
                } else if (arrName.equals("lower")) {
                    int randPos = new Random().nextInt(lower.length);
                    res += lower[randPos];
                } else if (arrName.equals("upper")) {
                    int randPos = new Random().nextInt(upper.length);
                    res += upper[randPos];
                }
                rand--;
            }

        }
        return res;

    }

    private static boolean isValid(String pass) {

        return Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,16}$", pass);
        /*
         * ^ represents starting character of the string.
         * (?=.*[0-9]) represents a digit must occur at least once.
         * (?=.*[a-z]) represents a lower case alphabet must occur at least once.
         * (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
         * (?=.*[@#$%^&-+=()] represents a special character that must occur at least
         * once.
         * (?=\\S+$) white spaces don’t allowed in the entire string.
         * .{8, 16} represents at least 8 characters and at most 20 characters.
         * $ represents the end of the string.
         */
    }

    private static void addElements(char[] lower, char[] upper) {

        for (char i = 'a'; i <= 'z'; i++) {
            lower[i - 'a'] = i;
            upper[i - 'a'] = (char) (i ^ 32);
        }
    }

    public static void main(String[] args) {
        addElements(lower, upper);
        System.out.println(generate());
    }
}
