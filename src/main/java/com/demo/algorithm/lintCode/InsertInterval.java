package com.demo.algorithm.lintCode;

import java.util.List;
/**
30. Insert Interval
Given a non-overlapping interval list which is sorted by start point.

Insert a new interval into it, make sure the list is still in order and non-overlapping (merge intervals if necessary).

Example
Insert (2, 5) into [(1,2), (5,9)], we get [(1,9)].

Insert (3, 4) into [(1,2), (5,9)], we get [(1,2), (3,4), (5,9)].
 * @author fuzamei
 *
 */
public class InsertInterval {

	/**
	 * Definition of Interval:
	 * public classs Interval {
	 *     int start, end;
	 *     Interval(int start, int end) {
	 *         this.start = start;
	 *         this.end = end;
	 *     }
	 * }
	 */
	
	public class Interval {
	      int start, end;
	      Interval(int start, int end) {
	          this.start = start;
	          this.end = end;
	      }
	  }
	
	/**
     * @param intervals: Sorted interval list.
     * @param newInterval: new interval.
     * @return: A new interval list.
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        // write your code here
    	int size = intervals.size();
    	if(size==0){
    		intervals.add(newInterval);
    		return intervals;
    	}
    	
    	int offset = (size-1)*2;
    	int position = (size-1);
    	int nstart = newInterval.start;
    	int nend = newInterval.end;
    	//二分查
    	while(true){
    		Interval interval = intervals.get(position);
    		if(interval.start > nend){
    			offset = offset%2 != 0 ? (offset+1)/2 : offset/2;
    			position -= offset;
    			if(offset <= 1){
    				intervals.add(position,newInterval);
        			break;
        		}
    		}else if(interval.end < nstart){
    			offset = offset%2 != 0 ? (offset+1)/2 : offset/2;
    			position += offset;
    			if(offset <= 1){
    				intervals.add(position,newInterval);
        			break;
        		}
    		}else if(interval.end == nstart || interval.start == nend){
    			return intervals;
    		}
    		
    		
    	}
    	return intervals;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
