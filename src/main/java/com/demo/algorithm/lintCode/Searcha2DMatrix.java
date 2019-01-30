package com.demo.algorithm.lintCode;
/**
28. Search a 2D Matrix
Write an efficient algorithm that searches for a value in an m x n matrix.

This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example
Consider the following matrix:

[
    [1, 3, 5, 7],
    [10, 11, 16, 20],
    [23, 30, 34, 50]
]
Given target = 3, return true.

Challenge
O(log(n) + log(m)) time
 * @author fuzamei
 *
 */
public class Searcha2DMatrix {

	/**
     * @param matrix: matrix, a list of lists of integers
     * @param target: An integer
     * @return: a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
    	int outerLength = matrix.length;
    	
    	if(outerLength==0){
    		return false;
    	}
    	
    	//保留原始长度
    	int offset = outerLength*2-2;
    	int nowPosition = outerLength-1;
    	int[] nowArray = null;
    	
    	//大数组二分
    	while(true){
    		if(nowPosition > outerLength-1 || nowPosition < 0){
    			//越界就false
    			return false;
    		}
			int[] temp = matrix[nowPosition];
			offset = offset%2!=0 ? (offset+1)/2 : offset/2;//偶数的长度和奇数长度的区分
			if(temp[0]>target){
				nowPosition -=offset;
			}else if(temp[temp.length-1]<target){
				nowPosition += offset;
			}else{
				nowArray=temp;
				if(temp.length==0){
					return false;
				}
				break;
			}
    		
    		if(offset <= 1){
    			int[] tempx = matrix[nowPosition];
    			if(tempx[0] <= target && tempx[tempx.length-1] >= target) nowArray=temp;
    			//差距值等于1说明已经没有数组匹配，直接返回false
    			return false;
    		}
    	}
    	
    	int arrayLength = nowArray.length;
    	//保留原始长度
    	int offset2 = arrayLength*2-2;
    	int nowPosition2 = arrayLength-1;
    	//小数组二分
    	while(true){
    		if(nowPosition2 > arrayLength-1 || nowPosition2 < 0){
    			//越界就false
    			return false;
    		}
    		int temp = nowArray[nowPosition2];
    		offset2 = offset2%2!=0 ? (offset2+1)/2 : offset2/2;//偶数的长度和奇数长度的区分
    		if(temp>target){
				nowPosition2 -=offset2;
			}else if(temp<target){
				nowPosition2 += offset2;
			}else{
				return true;
			}
    		if(offset2 <= 1){
    			if(nowArray[nowPosition2]==target) return true;
    			return false;
    		}
    	}
    	
    }
	
	public static void main(String[] args) {
		Searcha2DMatrix s = new Searcha2DMatrix();
//		boolean searchMatrix = s.searchMatrix(new int[][]{
//			new int[]{1,4,5},
//			new int[]{6,7,8},
//			}, 6);
		boolean searchMatrix = s.searchMatrix(new int[][]{
			new int[]{1,5,10,11,16,23,24,26,29,34,41,48,49,56,63,67,71,74,75},
			new int[]{97,118,131,150,160,182,202,226,251,273,289,310,326,349,368,390,401,412,428},
			new int[]{445,455,466,483,501,519,538,560,581,606,631,643,653,678,702,726,748,766,781},
			new int[]{792,817,837,858,872,884,901,920,936,957,972,982,1001,1024,1044,1063,1086,1098,1111},
			new int[]{1129,1151,1172,1194,1213,1224,1234,1250,1267,1279,1289,1310,1327,1348,1371,1393,1414,1436,1452},
			new int[]{1467,1477,1494,1510,1526,1550,1568,1585,1599,1615,1625,1649,1663,1674,1693,1710,1735,1750,1769},
		}, 1086);
		
//		boolean searchMatrix = s.searchMatrix(new int[][]{
//			new int[]{1,4,8,15,20,22,25,32,36,43,49,51,53,55,59,65,69,73,80},
//			new int[]{100,116,136,148,169,188,207,222,245,266,283,299,323,347,363,384,406,431,447},
//			new int[]{460,477,494,512,532,548,562,582,604,617,630,643,663,675,690,713,735,758,783},
//			new int[]{805,819,839,855,868,878,890,909,927,941,961,971,985,1000,1024,1037,1061,1086,1101},
//			new int[]{1124,1135,1157,1182,1198,1221,1235,1254,1267,1277,1294,1319,1342,1361,1382,1400,1419,1440,1453},
//			new int[]{1472,1495,1517,1542,1554,1567,1588,1603,1625,1642,1661,1680,1690,1702,1713,1725,1748,1771,1793},
//		}, 81);
		System.out.println(searchMatrix);
	}

}
