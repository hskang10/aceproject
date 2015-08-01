package fftprocessing;

import java.util.Scanner;

import com.ace.project.complex.Complex;
import com.ace.project.signalprocess.eeg.power.EegPower;
import com.ace.project.signalprocess.generator.SignalGenerator;
import com.ace.project.signalprocess.power.SignalPower;
import com.ace.project.signalprocess.transform.FFT;

public class Main {
    public static void main(String[] args) {

	int sigSize = 220;
	double sampleRate = 220;
	double frequency;

	// Sinewave frequency
	Scanner scanner = new Scanner(System.in);

	System.out.print("Sinewave frequency : ");
	frequency = scanner.nextDouble();
	scanner.close();

	double begintime = System.nanoTime();

	// Random signal Generation
	Complex[] random_signal = new Complex[sigSize];

	for (int i = 0; i < sigSize; i++)
	    random_signal[i] = new Complex(Math.random());

	// Sinewave signal Generation
	double[] sine = SignalGenerator.sinusoidalGenerate(frequency, sigSize,
		sampleRate);

	// Sinewave -> FFT
	Complex[] fftsine = FFT.fft(sine, false);
	double[] freq = SignalPower.getFreq(fftsine, sampleRate);

	/*
	 * for (int i = 0; i < sine.size(); i++) {
	 * System.out.println(sine.get(i)); }
	 */
	// Magnitude Display

	for (int i = 0; i < fftsine.length; i++) {
	    System.out.println(fftsine[i].magnitude() + "/" + freq[i]);
	}

	// Power Display
	System.out.println(
		"thetapower = " + EegPower.calcTheta(fftsine, sampleRate));
	System.out.println(
		"alphapower = " + EegPower.calcAlpha(fftsine, sampleRate));
	System.out.println(
		"betapower = " + EegPower.calcBeta(fftsine, sampleRate));
	System.out.println(
		"gammapower = " + EegPower.calcGamma(fftsine, sampleRate));

	double execTime = (System.nanoTime() - begintime) / 1000000;
	System.out.println("Processing Time : " + execTime + "ms");
    }
}