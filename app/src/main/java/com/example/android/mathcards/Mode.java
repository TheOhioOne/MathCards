package com.example.android.mathcards;

/**
 * Created by alex on 3/22/16.
 */
public enum Mode {
    add("+"),subtract("-"),multiply("x"),divide("\u00f7"),square("     2");

    private String operator;

    Mode(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
