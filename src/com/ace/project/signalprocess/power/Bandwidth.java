package com.ace.project.signalprocess.power;

public class Bandwidth {
    private final double low;
    private final double high;

    public Bandwidth(double low, double high) {
	this.low = low;
	this.high = high;
    }

    public double getLow() {
	return this.low;
    }

    public double getHigh() {
	return this.high;
    }
}
