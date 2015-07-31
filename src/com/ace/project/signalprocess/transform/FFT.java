package com.ace.project.signalprocess.transform;

import com.ace.project.complex.Complex;

public class FFT
{
	public static Complex[] fft(double[] signal, Boolean bInverse)
	{
		int len = signal.length;
		int extendedlen = next2Pow(len);
		Complex[] result = new Complex[next2Pow(extendedlen)];

		int nDir = bInverse ? 1 : -1;
		int log2NumData = 0;
		int HalfNumData;
		int ButterFly, ButterFlyHalf;
		Complex complexvalue;
		double AngleRadian;
		double CosineValue, SineValue;
		Complex complexbuf;
		double ArcRe, ArcIm, ReBuf, ImBuf, ArcBuf;

		for (int i = 0; i < len; i++)
			result[i] = new Complex(signal[i]);

		for (int i = len; i < extendedlen; i++)
			result[i] = new Complex(0);

		while (extendedlen != (1 << log2NumData))
			log2NumData++;

		HalfNumData = extendedlen >> 1;

		int j = 1;

		for (int i = 1; i < extendedlen; i++)
		{
			if (i < j)
			{
				complexvalue = result[j - 1];
				result[j - 1] = result[i - 1];
				result[i - 1] = complexvalue;
			}
			int k = HalfNumData;

			while (k < j)
			{
				j -= k;
				k = k >> 1;
			}

			j += k;
		}

		// butterfly 과정 수행
		for (int step = 1; step <= log2NumData; step++)
		{
			ButterFly = 1 << step;
			ButterFlyHalf = ButterFly >> 1;
			ArcRe = 1;
			ArcIm = 0;
			AngleRadian = Math.PI / (double) ButterFlyHalf;
			CosineValue = (double) Math.cos(AngleRadian);
			SineValue = nDir * (double) Math.sin(AngleRadian);

			for (j = 1; j <= ButterFlyHalf; j++)
			{
				int i = j;
				while (i <= extendedlen)
				{
					int mm = i + ButterFlyHalf;

					ReBuf = result[mm - 1].getReal() * ArcRe
							- result[mm - 1].getImag() * ArcIm;
					ImBuf = result[mm - 1].getReal() * ArcIm
							+ result[mm - 1].getImag() * ArcRe;
					complexbuf = new Complex(ReBuf, ImBuf);

					result[mm - 1] = result[i - 1].sub(complexbuf);
					result[i - 1] = result[i - 1].add(complexbuf);

					i += ButterFly;
				}
				ArcBuf = ArcRe;
				ArcRe = ArcRe * CosineValue - ArcIm * SineValue;
				ArcIm = ArcBuf * SineValue + ArcIm * CosineValue;
			}
		}

		if (!bInverse)
		{
			for (j = 0; j < extendedlen; j++)
				result[j] = result[j].divide((double) extendedlen);
		}

		return result;
	}
	public static Complex[] fft(Complex[] signal, Boolean bInverse)
	{
		int len = signal.length;
		int extendedlen = next2Pow(len);
		Complex[] result = new Complex[next2Pow(extendedlen)];

		int nDir = bInverse ? 1 : -1;
		int log2NumData = 0;
		int HalfNumData;
		int ButterFly, ButterFlyHalf;
		Complex complexvalue;
		double AngleRadian;
		double CosineValue, SineValue;
		Complex complexbuf;
		double ArcRe, ArcIm, ReBuf, ImBuf, ArcBuf;

		for (int i = 0; i < len; i++)
			result[i] = new Complex(signal[i]);

		for (int i = len; i < extendedlen; i++)
			result[i] = new Complex(0);

		while (extendedlen != (1 << log2NumData))
			log2NumData++;

		HalfNumData = extendedlen >> 1;

		int j = 1;

		for (int i = 1; i < extendedlen; i++)
		{
			if (i < j)
			{
				complexvalue = result[j - 1];
				result[j - 1] = result[i - 1];
				result[i - 1] = complexvalue;
			}
			int k = HalfNumData;

			while (k < j)
			{
				j -= k;
				k = k >> 1;
			}

			j += k;
		}

		// butterfly 과정 수행
		for (int step = 1; step <= log2NumData; step++)
		{
			ButterFly = 1 << step;
			ButterFlyHalf = ButterFly >> 1;
			ArcRe = 1;
			ArcIm = 0;
			AngleRadian = Math.PI / (double) ButterFlyHalf;
			CosineValue = (double) Math.cos(AngleRadian);
			SineValue = nDir * (double) Math.sin(AngleRadian);

			for (j = 1; j <= ButterFlyHalf; j++)
			{
				int i = j;
				while (i <= extendedlen)
				{
					int mm = i + ButterFlyHalf;

					ReBuf = result[mm - 1].getReal() * ArcRe
							- result[mm - 1].getImag() * ArcIm;
					ImBuf = result[mm - 1].getReal() * ArcIm
							+ result[mm - 1].getImag() * ArcRe;
					complexbuf = new Complex(ReBuf, ImBuf);

					result[mm - 1] = result[i - 1].sub(complexbuf);
					result[i - 1] = result[i - 1].add(complexbuf);

					i += ButterFly;
				}
				ArcBuf = ArcRe;
				ArcRe = ArcRe * CosineValue - ArcIm * SineValue;
				ArcIm = ArcBuf * SineValue + ArcIm * CosineValue;
			}
		}

		if (!bInverse)
		{
			for (j = 0; j < extendedlen; j++)
				result[j] = result[j].divide((double) extendedlen);
		}

		return result;
	}

	protected static int next2Pow(int num)
	{
		int result = 1;

		while (result < num)
			result = result << 1;

		return result;
	}
}