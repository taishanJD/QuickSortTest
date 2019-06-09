package com.jd.test;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 解决CAS 出现的ABA问题，使用AtomicStampedReference
 */
public class AtomicStampedReferenceTest {
	public static void main(String[] args) throws InterruptedException{
		final AtomicStampedReference<Integer> reAtomicStampedReference = new AtomicStampedReference<Integer>(0,0);
		final Integer ref1 = reAtomicStampedReference.getReference();
		final Integer stamp1 = reAtomicStampedReference.getStamp();
		
		// 初始值： 0---0
		System.out.println(ref1+"---"+stamp1);
		
		// t1线城先执行，更新值：10---0---true
		final Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(ref1+"---"+stamp1+"---"+reAtomicStampedReference.compareAndSet(ref1, ref1+10, stamp1, stamp1+1));
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					//保证t1执行完
					t1.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					// t2执行更新，发现stamp变动，compareAndSet返回false，值不会更新  值：10---0---false
					 Integer reference = reAtomicStampedReference.getReference();
		                System.out.println(reference + "---" + stamp1 + "---"
		                + reAtomicStampedReference.compareAndSet(reference, reference + 10, stamp1, stamp1 + 1));
				}
			}
		});
		
		t1.start();
		t2.start();
		t2.join();
		
		System.out.println(reAtomicStampedReference.getReference()+"---"+reAtomicStampedReference.getStamp());
	}

}
