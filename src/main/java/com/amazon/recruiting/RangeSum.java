package com.amazon.recruiting;

import java.util.ArrayList;

public class RangeSum {

	public static ArrayList<Integer> sumRange(int[] i, int r) {

	    ArrayList<Integer> retval = new ArrayList<Integer>();
	    int firstRangeSum = 0;
	    for(int j=0; j<r; j++) {
	        firstRangeSum += i[j];
	    }
	    retval.add(firstRangeSum);
	    for(int k=1; k<=i.length-r; k++) {
	        int previousAnswerIndex = k-1;
	        int previousSum = retval.get(previousAnswerIndex);
	        int nextSum = previousSum - i[previousAnswerIndex] + i[k+r-1];
	        retval.add(nextSum);
	    }
	    
	    return retval;
	}
	
	public static void main(String[] args) {
		int[] i = {1,2,3,4,5};
		ArrayList<Integer> retval = RangeSum.sumRange(i, 3);
		System.out.println(retval);
	}
	
}
