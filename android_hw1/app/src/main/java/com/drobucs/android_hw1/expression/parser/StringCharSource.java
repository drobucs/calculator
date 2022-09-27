package com.drobucs.android_hw1.expression.parser;

public class StringCharSource implements CharSource {
    private final String string;
    private int pos;

    public StringCharSource(String string) {
        this.string = string;
        pos = -1;
    }

    @Override
    public char next() {
        return string.charAt(++pos);
    }

    @Override
    public boolean hasNext() {
        return pos + 1 < string.length();
    }

    @Override
    public IllegalArgumentException error(String message) {
        return new IllegalArgumentException(message);
    }
}
