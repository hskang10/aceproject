package com.ace.project.signalprocess.eeg.concentration;

import java.util.*;

public class Hurst {

    /* METHOD */
    
    public static double calHurst(double[] EEGdata) {
	double hurst = 0;
	double mean = 0;
	double diffMaxMin = 0;
	int length = EEGdata.length;

	for (int i = 0; i < length; i++) {
	    mean += EEGdata[i] / length; // Calculate the mean of
						 // EEGdata
	}

	double[] meanAdjustedSeries = new double[length];
	double[] cumulativeDeviateSeries = new double[length];

	meanAdjustedSeries[0] = EEGdata[0] - mean;
	cumulativeDeviateSeries[0] = meanAdjustedSeries[0];
	
	for (int i = 1; i < length; i++) {	    
	    meanAdjustedSeries[i] = EEGdata[i] - mean;
	    cumulativeDeviateSeries[i] = meanAdjustedSeries[i]
		    + cumulativeDeviateSeries[i - 1];
	}
	Arrays.sort(cumulativeDeviateSeries); // Ascending sort

	diffMaxMin = cumulativeDeviateSeries[length - 1]
		- cumulativeDeviateSeries[0];

	double sum = 0;
	for (int i = 0; i < length; i++) {
	    meanAdjustedSeries[i] *= meanAdjustedSeries[i];
	    sum += meanAdjustedSeries[i];
	}
	sum = sum / length; // Variation
	sum = Math.sqrt(sum); // Standard deviation

	hurst = Math.log(diffMaxMin / sum); // Hurst exponent

	return hurst;
    }
}