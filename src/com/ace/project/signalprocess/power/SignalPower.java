package com.ace.project.signalprocess.power;

import com.ace.project.complex.Complex;

public class SignalPower
{
	public static double[] getFreq(Complex[] signal, double sampleRate)
	{
		int len = signal.length; // signal length
		double[] freq = new double[len];

		for (int i = 0; i < len; i++)
			freq[i] = sampleRate * i / len;

		return freq;
	}

	public static double[] calcPower(Complex[] signal)
	{
		int len = signal.length;
		double[] power = new double[len];

		for (int i = 0; i < len; i++)
			power[i] = Math.pow(signal[i].magnitude(), 2);

		return power;
	}

	public static double calcAbsolutePower(Complex[] signal,
			double sampleRate, Bandwidth bandwidth)
	{
		int first, last;
		double absolutePower = 0;

		first = getIndexOfFreq(signal, sampleRate, bandwidth.getLow(),
				FrequencyPosition.RIGHT);
		last = getIndexOfFreq(signal, sampleRate, bandwidth.getHigh(),
				FrequencyPosition.LEFT);

		double[] power = calcPower(signal);

		for (int i = first; i <= last; i++)
			absolutePower += power[i];

		return absolutePower;
	}

	public static double calcAbsolutePower(Complex[] signal, double sampleRate,
			double low, double high)
	{
		Bandwidth bandwidth = new Bandwidth(low, high);
		int first, last;
		double absolutePower = 0;

		first = getIndexOfFreq(signal, sampleRate, bandwidth.getLow(),
				FrequencyPosition.RIGHT);
		last = getIndexOfFreq(signal, sampleRate, bandwidth.getHigh(),
				FrequencyPosition.LEFT);

		double[] power = calcPower(signal);

		for (int i = first; i <= last; i++)
			absolutePower += power[i];

		return absolutePower;
	}

	public static double calcTotalPower(Complex[] signal, double sampleRate)
	{
		int len = signal.length;
		double[] power = calcPower(signal);
		double totalPower = 0;

		for (int i = 0; i < len; i++)
			totalPower += power[i];

		return totalPower;
	}

	protected static int getIndexOfFreq(Complex[] signal, double sampleRate,
			double freqToFind, FrequencyPosition position)
	{
		int index = 0;
		int max = signal.length;
		double[] freq = getFreq(signal, sampleRate);

		while (freq[index] < freqToFind)
		{
			if (max - 1 > index)
				index++;
			else
				break;
		}

		if (freqToFind == freq[index] || position == FrequencyPosition.RIGHT)
			return index;
		else
			return index - 1;
	}
}