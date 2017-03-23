package org.homegrown.boardgamelibrary.Utils;


public enum CountryCodes {
    BELGIUM("0032"), NETHERLANDS("0031"), FRANCE("0033"), GERMANY("0049");

    private String code;

    private CountryCodes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
