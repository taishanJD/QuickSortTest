package com.jd.test;

/**
 * 快排 参考 https://blog.csdn.net/qq_36651925/article/details/89467744
 */
public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] datas = { 1, 7, 6, 0, 0, 7, 3, 5, 9, 1, 4 };
		print(datas);
		quickSort(datas, 0, datas.length - 1);
		print(datas);
	}

	private static void quickSort(int[] datas, int start, int end) {

		// 写错的第一个点：递归终止的条件
		if (start > end)
			return;
		int i = start, j = end;

		// 写错的第二个点：基准是每个区间的第一个值，是data[i]而不是data[start]
		int temp = datas[i];
		while (i < j) {

			// 写错的第三个点：前后两个哨兵移动的条件：包含等于。j从后往前，遇到大于等于基准的值，继续走，直到遇到小于基准的值，停下。i由前向后同理。
			while (i < j && datas[j] >= temp) {
				j--;
			}
			while (i < j && datas[i] <= temp) {
				i++;
			}
			if (i < j) {
				// i,j位置的值交换位置
				int temp1 = datas[i];
				datas[i] = datas[j];
				datas[j] = temp1;
			}
		}
		// 第一轮排序结束，将基准值和i位置的值交换。这样此时基准值左边全是小于它的值，右边全是大于它的值
		datas[start] = datas[i];
		datas[i] = temp;
		// print(datas);

		quickSort(datas, start, j - 1);
		quickSort(datas, j + 1, end);
	}

	private static void print(int[] datas) {
		for (int i = 0; i < datas.length; i++) {
			System.out.print(datas[i] + " ");
		}
		System.out.println();
	}
}
