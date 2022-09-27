package com.drobucs.android_hw1.expression;

public class Divide extends AbstractBinaryOperation {

    public Divide(final MarkExpression expLeft, final MarkExpression expRight) {
        super("/", 300, expLeft, expRight);
    }

    @Override
    public double calculate(double left, double right) {
        if (right == 0) {
            throw new IllegalArgumentException("error: divide by zero");
        }
        return left / right;
    }

}
