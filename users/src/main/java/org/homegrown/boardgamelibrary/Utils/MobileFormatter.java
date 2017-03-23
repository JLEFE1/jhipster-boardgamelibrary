package org.homegrown.boardgamelibrary.Utils;


import org.apache.commons.lang3.StringUtils;
import org.homegrown.boardgamelibrary.exception.ValidationException;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.*;


public class MobileFormatter {

    public static String formatMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            throw new ValidationException("Mobile should not be empty", "mobile");
        }
        String removedSlash = remove(mobile, "/");
        String removedDash = remove(removedSlash, "-");
        String removedSpace = remove(removedDash, " ");
        String removedParenthesis = remove(removedSpace, "(0)");
        String formattedMobile;
        if (!Pattern.matches("^\\+?[0-9]{10,16}$", removedParenthesis)) {
            throw new ValidationException("Mobile has wrong format", "mobile");
        }
        if (startsWith(removedParenthesis, "+")) {
            formattedMobile = replace(removedParenthesis, "+", "00");
        } else {
            formattedMobile = removedParenthesis;
        }
        Optional<CountryCodes> code = Arrays.stream(CountryCodes.values())
                .filter(value -> startsWith(formattedMobile, value.getCode())).findFirst();
        if (!code.isPresent()) {
            throw new ValidationException("Mobile does not contain country code", "mobile");
        }
        return formattedMobile;
    }
}
