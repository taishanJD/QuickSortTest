package com.jd.test;

/**
 * ���� �ο� https://blog.csdn.net/qq_36651925/article/details/89467744
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

		// д��ĵ�һ���㣺�ݹ���ֹ������
		if (start > end)
			return;
		int i = start, j = end;

		// д��ĵڶ����㣺��׼��ÿ������ĵ�һ��ֵ����data[i]������data[start]
		int temp = datas[i];
		while (i < j) {

			// д��ĵ������㣺ǰ�������ڱ��ƶ����������������ڡ�j�Ӻ���ǰ���������ڵ��ڻ�׼��ֵ�������ߣ�ֱ������С�ڻ�׼��ֵ��ͣ�¡�i��ǰ���ͬ��
			while (i < j && datas[j] >= temp) {
				j--;
			}
			while (i < j && datas[i] <= temp) {
				i++;
			}
			if (i < j) {
				// i,jλ�õ�ֵ����λ��
				int temp1 = datas[i];
				datas[i] = datas[j];
				datas[j] = temp1;
			}
		}
		// ��һ���������������׼ֵ��iλ�õ�ֵ������������ʱ��׼ֵ���ȫ��С������ֵ���ұ�ȫ�Ǵ�������ֵ
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
