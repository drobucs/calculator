package com.drobucs.android_hw1.expression;

public class Multiply extends AbstractBinaryOperation {

    public Multiply(final MarkExpression expLeft, final MarkExpression expRight) {
        super("*", 300, expLeft, expRight);
    }

    @Override
    public double calculate(double left, double right) {
        return left * right;
    }

}
