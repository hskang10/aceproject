package com.ace.project.signalprocess.eeg.power;

import com.ace.project.complex.Complex;
import com.ace.project.signalprocess.power.Bandwidth;
import com.ace.project.signalprocess.power.SignalPower;

public class EegPower {
    private static final Bandwidth THETA_BAND, ALPHA_BAND, BETA_BAND,
	    GAMMA_BAND, SMR_BAND, LOW_BETA_BAND, MID_BETA_BAND, TOTAL_BAND;

    static {
	THETA_BAND = new Bandwidth(4, 8);
	ALPHA_BAND = new Bandwidth(8, 13);
	BETA_BAND = new Bandwidth(13, 30);
	GAMMA_BAND = new Bandwidth(30, 50);
	SMR_BAND = new Bandwidth(12, 15);
	LOW_BETA_BAND = new Bandwidth(12, 15);
	MID_BETA_BAND = new Bandwidth(15, 20);
	TOTAL_BAND = new Bandwidth(4, 50);
    }

    public static double calcTheta(Complex[] signal, double sampleRate) {
	return SignalPower.calcAbsolutePower(signal, sampleRate, THETA_BAND);
    }

    public static double calcAlpha(Complex[] signal, double sampleRate) {
	return SignalPower.calcAbsolutePower(signal, sampleRate, ALPHA_BAND);
    }

    public static double calcBeta(Complex[] signal, double sampleRate) {
	return SignalPower.calcAbsolutePower(signal, sampleRate, BETA_BAND);
    }

    public static double calcGamma(Complex[] signal, double sampleRate) {
	return SignalPower.calcAbsolutePower(signal, sampleRate, GAMMA_BAND);
    }

    public static double calcSMR(Complex[] signal, double sampleRate) {
	return SignalPower.calcAbsolutePower(signal, sampleRate, SMR_BAND);
    }

    public static double calcLowBeta(Complex[] signal, double sampleRate) {
	return SignalPower.calcAbsolutePower(signal, sampleRate, LOW_BETA_BAND);
    }

    public static double calcMidBeta(Complex[] signal, double sampleRate) {
	return SignalPower.calcAbsolutePower(signal, sampleRate, MID_BETA_BAND);
    }

    public static double calcTotalEegBand(Complex[] signal, double sampleRate) {
	return SignalPower.calcAbsolutePower(signal, sampleRate, TOTAL_BAND);
    }
}