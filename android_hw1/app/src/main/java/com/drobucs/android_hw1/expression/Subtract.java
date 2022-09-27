package com.drobucs.android_hw1.expression;

public class Subtract extends AbstractBinaryOperation {

    public Subtract(final MarkExpression expLeft, final MarkExpression expRight) {
        super("-", 400, expLeft, expRight);
    }

    @Override
    public double calculate(double left, double right) {
        return left - right;
    }

}
