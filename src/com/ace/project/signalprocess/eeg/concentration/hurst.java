package com.ace.project.signalprocess.eeg.concentration;

import java.util.*;

public class hurst {

    /* METHOD */
    
    public static double CalHurst(double[] EEGdata) {
	double hurst = 0;
	double mean = 0;
	double diffMaxMin = 0;

	for (int i = 0; i < EEGdata.length; i++) {
	    mean += EEGdata[i] / EEGdata.length; // Calculate the mean of
						 // EEGdata
	}

	double[] meanAdjustedSeries = new double[EEGdata.length];
	double[] cumulativeDeviateSeries = new double[EEGdata.length];

	for (int i = 0; i < EEGdata.length; i++) {
	    meanAdjustedSeries[i] = EEGdata[i] - mean;
	    cumulativeDeviateSeries[i] = meanAdjustedSeries[i]
		    + cumulativeDeviateSeries[i - 1];
	}
	Arrays.sort(cumulativeDeviateSeries); // Ascending sort

	diffMaxMin = cumulativeDeviateSeries[EEGdata.length - 1]
		- cumulativeDeviateSeries[0];

	double sum = 0;
	for (int i = 0; i < EEGdata.length; i++) {
	    meanAdjustedSeries[i] *= meanAdjustedSeries[i];
	    sum += meanAdjustedSeries[i];
	}
	sum = sum / EEGdata.length; // Variation
	sum = Math.sqrt(sum); // Standard deviation

	hurst = Math.log(diffMaxMin / sum); // Hurst exponent

	return hurst;
    }
}