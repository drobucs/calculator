package com.drobucs.android_hw1.expression.parser;

public interface CharSource {
    char next();
    boolean hasNext();
    IllegalArgumentException error(String message);
}
