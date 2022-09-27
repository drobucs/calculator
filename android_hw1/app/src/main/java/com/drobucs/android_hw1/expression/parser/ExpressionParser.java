package com.drobucs.android_hw1.expression.parser;

import com.drobucs.android_hw1.expression.*;

public class ExpressionParser extends BaseParser implements Parser {
    private final String[][] OPERATIONS = {
            {"*", "/"},
            {"+", "-"},
    };

    public ExpressionParser(final CharSource source) {
        super(source);
    }

    public ExpressionParser() {
        this(null);
    }

    @Override
    public Expression parse(String expression) throws IllegalArgumentException {
        if (expression.isEmpty()) {
            return new Const(0);
        }
        startParse(new StringCharSource(expression));
        Expression result = parseXElement(OPERATIONS.length + 1);
        skipWhitespace();
        if (!end()) {
            throw new IllegalArgumentException("invalid suffix of expression");
        }
        return result;
    }

    // 1Element -> const, variable, (expression)
    // XElement -> [x - 1]Element [x priority operation] [x - 1]Element, [x - 1]Element
    private MarkExpression parse1Element() {
        skipWhitespace();
        if (take('-')) {
            if (between('0', '9')) {
                return new Const(parseNumber(true));
            } else {
                return new Negate(parse1Element());
            }
        } else if (between('0', '9')) {
            return new Const(parseNumber(false));
        } else {
            throw error("Unexpected starts of number: " + getCh());
        }
    }

    // 1 <= x <= OPERATION.length + 1
    private MarkExpression parseXElement(final int x) {
        if (x == 1) {
            return parse1Element();
        }
        skipWhitespace();
        MarkExpression exp = parseXElement(x - 1);
        boolean isNewOperation;
        do {
            skipWhitespace();
            isNewOperation = false;
            for (final String operation : OPERATIONS[x - 2]) {
                if (take(operation.charAt(0))) {
                    isNewOperation = true;
                    exp = expByOperation(exp, operation, x);
                    break;
                }
            }
        } while (isNewOperation);
        return exp;
    }

    private double parseNumber(final boolean negate) {
        final StringBuilder sb = new StringBuilder();
        if (negate) {
            sb.append('-');
        }
        while (between('0', '9')) {
            sb.append(take());
        }
        if (take('.')) {
            sb.append('.');
        }
        while (between('0', '9')) {
            sb.append(take());
        }
        if (take('E')) {
            sb.append('E');
            if (take('-')) {
                sb.append('-');
            }
            while (between('0', '9')) {
                sb.append(take());
            }
        }

        return Double.parseDouble(sb.toString());
    }

    private MarkExpression expByOperation(final MarkExpression exp, final String operation, final int x) {
        if (startsWith(operation, '*')) {
           return new Multiply(exp, parseXElement(x - 1));
        } else if (startsWith(operation, '/')) {
            return new Divide(exp, parseXElement(x - 1));
        } else if (startsWith(operation, '+')) {
            return new Add(exp, parseXElement(x - 1));
        } else if (startsWith(operation, '-')) {
            return new Subtract(exp, parseXElement(x - 1));
        }
        throw error("Unexpected operation -> operation[0] = '" + operation.charAt(0) + "'");
    }

    private static boolean startsWith(final String operation, final char c) {
        return operation.charAt(0) == c;
    }
}
