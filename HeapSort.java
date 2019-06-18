package com.jd.test;

import java.util.ArrayList;
import java.util.List;

public class HeapSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Person> persons = new ArrayList<>();
		persons.add(new Person("A", 33));
		persons.add(new Person("B", 35));
		persons.add(new Person("C", 15));
		persons.add(new Person("D", 21));
		persons.add(new Person("E", 40));
		persons.add(new Person("F", 15));
		persons.add(new Person("G", 27));
		persons.add(new Person("H", 34));
		persons.add(new Person("I", 30));

		// Heap heap = new Heap();
		// heap.getTopMaxK(persons, 4);
		// heap.getTopMinK(persons, 4);
		// 40
		// 35 27
		// 34 33 15 18
		// 21 30

		Heap<Person> heap = new Heap<>(persons);
		heap.printHeap(heap.getHeapSize());
		// ��С������
		// heap.buildMaxHeap();
		// heap.heapMaxSort();
		// heap.printHeap(persons.size());

		// �Ӵ�С��
		heap.buildMinHeap();
		heap.heapMinSort();
		heap.printHeap(persons.size());
	}

	static class Heap<E extends Comparable> {
		private ArrayList<E> datas;
		private int heapSize;

		public Heap() {

		}

		public Heap(ArrayList<E> datas) {
			super();
			this.datas = datas;
			this.heapSize = datas.size();
		}

		public ArrayList<E> getDatas() {
			return datas;
		}

		public void setDatas(ArrayList<E> datas) {
			this.datas = datas;
		}

		public int getHeapSize() {
			return heapSize;
		}

		public void setHeapSize(int heapSize) {
			this.heapSize = heapSize;
		}

		public E getDataOfIndex(int index) {
			if (index < 0 || index > heapSize || index > datas.size())
				throw new IllegalArgumentException();
			return datas.get(index);
		}

		public void setDataAtIndex(int index, E data) {
			if (index < 0 || index > heapSize || index > datas.size())
				throw new IllegalArgumentException();
			datas.set(index, data);
		}

		public int getLeftIndex(int index) {
			return index * 2 + 1;
		}

		public int getRightIndex(int index) {
			return index * 2 + 2;
		}

		public int getParentIndex(int index) {
			return (index - 1) / 2;
		}

		// ����ѵ���
		public void maxHeapify(int index) {
			while (true) {
				int left = getLeftIndex(index);
				int right = getRightIndex(index);
				int largest = index;

				if (left < heapSize
						&& getDataOfIndex(left)
								.compareTo(getDataOfIndex(index)) >= 0) {
					largest = left;
				}

				if (right < heapSize
						&& getDataOfIndex(right).compareTo(
								getDataOfIndex(largest)) >= 0) {
					largest = right;
				}

				if (largest == index)
					return;
				E temp = getDataOfIndex(index);
				setDataAtIndex(index, getDataOfIndex(largest));
				setDataAtIndex(largest, temp);

				index = largest;
			}
		}

		// ���������
		public void buildMaxHeap() {
			for (int i = (heapSize - 1) / 2; i >= 0; i--) {
				maxHeapify(i);
			}
		}

		// С���ѵ���
		public void minHeapify(int index) {
			while (true) {
				int left = getLeftIndex(index);
				int right = getRightIndex(index);
				int smallest = index;

				if (left < heapSize
						&& getDataOfIndex(left)
								.compareTo(getDataOfIndex(index)) <= 0) {
					smallest = left;
				}

				if (right < heapSize
						&& getDataOfIndex(right).compareTo(
								getDataOfIndex(smallest)) <= 0) {
					smallest = right;
				}

				if (smallest == index)
					return;
				E temp = getDataOfIndex(index);
				setDataAtIndex(index, getDataOfIndex(smallest));
				setDataAtIndex(smallest, temp);

				index = smallest;
			}
		}

		// ����С����
		public void buildMinHeap() {
			for (int i = (heapSize - 1) / 2; i >= 0; i--) {
				minHeapify(i);
			}
		}

		// ��С������
		public void heapMaxSort() {
			buildMaxHeap();
			int length = heapSize;
			for (int i = length - 1; i > 0; i--) {

				E temp = getDataOfIndex(i);
				setDataAtIndex(i, getDataOfIndex(0));
				setDataAtIndex(0, temp);
				heapSize--;
				maxHeapify(0);
			}
		}

		// �ɴ�С��
		public void heapMinSort() {
			buildMinHeap();
			int length = heapSize;
			for (int i = length - 1; i > 0; i--) {

				E temp = getDataOfIndex(i);
				setDataAtIndex(i, getDataOfIndex(0));
				setDataAtIndex(0, temp);
				heapSize--;
				minHeapify(0);
			}
		}

		// ��ȡ��С��K����
		public void getTopMinK(List<E> inputDatas, int k) {
			ArrayList<E> topKDatas = new ArrayList<>();
			for (int i = 0; i < k; i++) {
				topKDatas.add(inputDatas.get(i));
			}
			Heap<E> topKHeap = new Heap<>(topKDatas);
			topKHeap.buildMaxHeap();

			for (int i = k; i < inputDatas.size(); i++) {
				if (inputDatas.get(i).compareTo(topKHeap.getDataOfIndex(0)) <= 0) {
					topKHeap.setDataAtIndex(0, inputDatas.get(i));
					topKHeap.buildMaxHeap();
				}
			}
			topKHeap.printHeap(k);
		}

		// ��ȡ����K����
		public void getTopMaxK(List<E> inputDatas, int k) {
			ArrayList<E> topKDatas = new ArrayList<>();
			for (int i = 0; i < k; i++) {
				topKDatas.add(inputDatas.get(i));
			}
			Heap<E> topKHeap = new Heap<>(topKDatas);
			topKHeap.buildMinHeap();

			for (int i = k; i < inputDatas.size(); i++) {
				if (inputDatas.get(i).compareTo(topKHeap.getDataOfIndex(0)) >= 0) {
					topKHeap.setDataAtIndex(0, inputDatas.get(i));
					topKHeap.buildMinHeap();
				}
			}
			topKHeap.printHeap(k);
		}

		public void printHeap(int length) {
			for (int i = 0; i < length; i++) {
				System.out.print(datas.get(i).toString() + "-");
			}
			System.out.println();
		}
	}

	static class Person implements Comparable {

		private String name;
		private int age;

		public Person(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public int compareTo(Object o) {
			return this.age - ((Person) o).age;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}
	}

}
