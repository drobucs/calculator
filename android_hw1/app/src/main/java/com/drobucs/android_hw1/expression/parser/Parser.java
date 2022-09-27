package com.drobucs.android_hw1.expression.parser;

import com.drobucs.android_hw1.expression.Expression;

public interface Parser {
    Expression parse(String expression) throws IllegalArgumentException;
}
