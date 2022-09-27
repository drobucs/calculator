package com.drobucs.android_hw1.expression;

public class Negate extends AbstractUnaryOperation {

    public Negate(MarkExpression exp) {
        super(exp, "-");
    }

    @Override
    public double calculate(double val) {
        return -val;
    }

}
