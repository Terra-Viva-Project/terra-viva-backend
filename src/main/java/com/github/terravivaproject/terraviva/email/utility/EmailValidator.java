package com.github.terravivaproject.terraviva.email.utility;

import java.util.regex.Pattern;

/**
 * desc
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 12 09 2022
 */
public class EmailValidator {
    /**
     * isValidEmail.
     *
     * @param email a {@link java.lang.String} object
     * @return a boolean
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
