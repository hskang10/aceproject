package com.ace.project.signalprocess.generator;

public class SignalGenerator
{
	public static double[] sinusoidalGenerate(double frequency, int point,
			double sampleRate)
	{
		double[] signal = new double[point];

		for (int i = 0; i < point; i++)
			signal[i] = Math.sin(2 * Math.PI * frequency * i
					/ sampleRate);

		return signal;
	}
}