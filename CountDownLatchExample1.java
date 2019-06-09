package com.jd.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample1 {

	// ���������
	private static final int threadCount = 10;

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// ����һ�����й̶��߳��������̳߳ض�����������̳߳ص��߳�������̫�ٵĻ���ᷢ��ִ�еĺ�����
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		for (int i = 0; i < threadCount; i++) {
			final int threadnum = i;
			threadPool.execute(new Runnable() {

				@Override
				public void run() {
					try {
						test(threadnum);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						countDownLatch.countDown();// ��ʾһ�������Ѿ������
					}
				}
			});
		}
		countDownLatch.await();
		threadPool.shutdown();
		System.out.println("finish");
	}

	public static void test(int threadnum) throws InterruptedException {
		Thread.sleep(1000);// ģ������ĺ�ʱ����
		System.out.println("threadnum:" + threadnum);
		Thread.sleep(1000);// ģ������ĺ�ʱ����
	}

}
