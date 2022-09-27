package com.drobucs.android_hw1.expression;

public class Const extends AbstractElement {

    public Const(final double value) {
        super(value);
    }

    @Override
    public double evaluate(double varValue) {
        return (Double)value;
    }

}
