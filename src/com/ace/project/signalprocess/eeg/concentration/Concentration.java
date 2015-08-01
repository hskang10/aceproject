package com.ace.project.signalprocess.eeg.concentration;

import com.ace.project.complex.Complex;
import com.ace.project.signalprocess.eeg.power.EegPower;

public class Concentration {
    public static double calcConcentration(Complex[] signal,
	    double sampleRate) {
	double SMRPower = EegPower.calcSMR(signal, sampleRate);
	double midBetaPower = EegPower.calcMidBeta(signal, sampleRate);
	double thetaPower = EegPower.calcTheta(signal, sampleRate);

	return (SMRPower + midBetaPower) / thetaPower;
    }

    public static double calcRLB(Complex[] signal, double sampleRate) {
	double lowBetaPower = EegPower.calcLowBeta(signal, sampleRate);
	double totalBandPower = EegPower.calcTotalEegBand(signal, sampleRate);

	return lowBetaPower / totalBandPower;
    }

    public static double calcRMB(Complex[] signal, double sampleRate) {
	double midBetaPower = EegPower.calcMidBeta(signal, sampleRate);
	double totalBandPower = EegPower.calcTotalEegBand(signal, sampleRate);

	return midBetaPower / totalBandPower;
    }
}
