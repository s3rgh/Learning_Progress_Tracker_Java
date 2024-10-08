package tracker.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CredentialValidatorUtils {
    public static final String INCORRECT_CREDENTIALS = "Incorrect credentials.";
    public static final String INCORRECT_FIRST_NAME = "Incorrect first name.";
    public static final String INCORRECT_LAST_NAME = "Incorrect last name.";
    public static final String INCORRECT_EMAIL = "Incorrect email.";

    public static boolean validateCredentials(String string) {
        if (string == null || string.isEmpty()) {
            System.out.println(INCORRECT_CREDENTIALS);
            return false;
        }

        if (string.split(" ").length < 3) {
            System.out.println(INCORRECT_CREDENTIALS);
            return false;
        }

        var strings = string.trim().split(" ");
        var firstName = strings[0];
        var lastName = Arrays.stream(strings).skip(1)
                .limit(strings.length - 2)
                .collect(Collectors.joining(" "));

        var email = strings[strings.length - 1];

        if (!firstName.matches("^[a-zA-Z]+[-']?[a-zA-Z]+$")) {
            System.out.println(INCORRECT_FIRST_NAME);
            return false;
        }

        if (!lastName.matches("^[a-zA-Z](?!.*[-'\\s]{2})[a-zA-Z-'\\s]*[a-zA-Z]$")) {
            System.out.println(INCORRECT_LAST_NAME);
            return false;
        }

        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]+$")) {
            System.out.println(INCORRECT_EMAIL);
            return false;
        }
        return true;
    }
}
