package net.aimeizi.tutorials;


/**
 * 二分法查找
 * @author 冯靖
 *
 */
public class BinarySearch {
	
	/**
	 * 二分法查找算法
	 * @param array 一维数组(充当数据结果集)
	 * @param key 目标元素(要查找的值)
	 * @return 没找到返回-1
	 */
	public static int binary(int[] array,int key){
		
		int low = 0;
		int high = array.length-1;
		
		while(low <= high){
			int middle = (low+high)/2;
			if(key == array[middle]){//找到,返回下标索引
				return middle;
			}else if(key > array[middle]){
				low = middle + 1;
			}else if(key < array[middle]){
				high = middle-1;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		System.out.println(binary(new int[]{3, 5, 8, 13, 15, 16, 20, 27, 31, 56},20));
		//Arrays.binarySearch(new int[]{3, 5, 8, 13, 15, 16, 20, 27, 31, 56},20);
	}

}
