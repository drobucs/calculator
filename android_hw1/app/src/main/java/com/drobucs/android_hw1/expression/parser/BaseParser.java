package com.drobucs.android_hw1.expression.parser;

public class BaseParser {
    private static final char END = 0;
    protected CharSource source;
    private char ch;

    protected BaseParser(final CharSource source) {
        startParse(source);
    }

    protected void startParse(final CharSource source) {
        if (source == null) {
            return;
        }
        this.source = source;
        take();
    }

    protected char getCh() {
        return ch;
    }

    protected boolean end() {
        return ch == END;
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected void skipWhitespace() {
        while (source.hasNext() && Character.isWhitespace(ch)) {
            ch = source.next();
        }
    }
}
