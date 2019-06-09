package com.jd.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 
 * @author Snailclimb
 * @date 2018��9��30��
 * @Description: ��Ҫһ������һ����ɵ����
 */
public class SemaphoreExample1 {
	// ���������
	private static final int threadCount = 20;

	public static void main(String[] args) throws InterruptedException {
		// ����һ�����й̶��߳��������̳߳ض�����������̳߳ص��߳�������̫�ٵĻ���ᷢ��ִ�еĺ�����
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		// һ��ֻ������ִ�е��߳�������
		final Semaphore semaphore = new Semaphore(5);

		for (int i = 0; i < threadCount; i++) {
			final int threadnum = i;
			threadPool.execute(new Runnable() {

				@Override
				public void run() {
					try {
						semaphore.acquire();// ��ȡһ����ɣ����Կ������߳�����Ϊ5/1=5
						test(threadnum);
						semaphore.release();// �ͷ�һ�����
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		threadPool.shutdown();
		System.out.println("finish");
	}

	public static void test(int threadnum) throws InterruptedException {
		Thread.sleep(1000);// ģ������ĺ�ʱ����
		System.out.println("threadnum:" + threadnum);
		Thread.sleep(1000);// ģ������ĺ�ʱ����
	}
}
