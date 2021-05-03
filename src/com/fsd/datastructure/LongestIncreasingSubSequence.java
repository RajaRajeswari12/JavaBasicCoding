package com.fsd.datastructure;

public class LongestIncreasingSubSequence {

	int[] lengthIndex ;
	int[] subSequenceArray;

	public int[] longestIncreasingSubSequence(int[] sequenceArray) {
		int length = sequenceArray.length;
		lengthIndex = new int[length];
		subSequenceArray = new int[length];

		for(int index=0;index < length;index++) {
			lengthIndex[index] = 1;		
		}

		for(int secondIndex = 1;secondIndex < length;secondIndex++) {
			for(int firstIndex = 0;firstIndex < secondIndex;firstIndex++ ) {
				if(sequenceArray[firstIndex] < sequenceArray[secondIndex]) {
					int value1 = lengthIndex[firstIndex]+1;
					int value2 = lengthIndex[secondIndex];
					int max = max(value1,value2);
					lengthIndex[secondIndex] = max;
					if(value1 == max) {
						subSequenceArray[secondIndex] = firstIndex;
					}
				}
			}
		}

		int index[] = getLongestSubSequenceIndex(lengthIndex);

		int[] subSequenced = getSubSequence(index[1],index[0], subSequenceArray);

		for(int subSeqIndex=subSequenced.length-1; subSeqIndex >= 0;subSeqIndex--) {
			int subSequenceIndex = subSequenced[subSeqIndex];
			System.out.print("\t"+sequenceArray[subSequenceIndex]);
		}

		return lengthIndex;

	}
	public int max(int value1,int value2) {
		if(value1 == value2) {
			return value1;
		}else if(value1 > value2) {
			return value1;
		}else {
			return value2;
		}
	}
	public int[] getLongestSubSequenceIndex(int[] lengthIndex) {
		int max = 0,index=0;
		int maxIndex[] = new int[2];
		for(int sequencedIndex=0 ; sequencedIndex< lengthIndex.length;sequencedIndex++) {
			max =  max(lengthIndex[sequencedIndex], max);		
			if(max == lengthIndex[sequencedIndex]) {
				index = sequencedIndex;
			}
		}
		maxIndex[0] = index;
		maxIndex[1] = max;
		return maxIndex;
	}
	public int[] getSubSequence(int max,int index,int[] subSequenceArray) {
		int result[] = new int[max];
		int subSequenceIndex =1;
		result[0] = index;
		while(index != 0) {
			result[subSequenceIndex] = subSequenceArray[index];
			index = subSequenceArray[index];
			subSequenceIndex++;
		}
		return result;
	}
	public static void main(String[] args) {
		LongestIncreasingSubSequence l = new  LongestIncreasingSubSequence();
		int a[] = {0,1,2,3,4,12,12,13,14,10,6,9,13,3,11,7,15};
		int[] result = l.longestIncreasingSubSequence(a);
	}

}
