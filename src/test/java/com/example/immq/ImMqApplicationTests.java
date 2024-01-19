package com.example.immq;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

@SpringBootTest
class ImMqApplicationTests {

    @Test
    void contextLoads() throws ExecutionException, InterruptedException {
        new MyThread().start();
        System.out.println("2222222222");
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();
        MyCallable myCallable = new MyCallable();
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Future<String> future = executorService.submit(myCallable);
//        String s = future.get();
//        System.out.println(s);
        FutureTask<String> stringFutureTask = new FutureTask<>(myCallable);
        new Thread(stringFutureTask).start();
        System.out.println(stringFutureTask.get());
    }

    @Test
    void test() {
        Object lock = new Object();
        new Thread(
                () -> {
                    System.out.println("thread1 is waiting");
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        )
    }

    @Test
    void test1() {
        int[] nums=new int[100];
        Set<Integer> set = new HashSet<>();
        for (int i:nums){
            set.add(i);
        }
        int count=0;
        int max=0;
        for (int i:nums){
            if (!set.contains(i-1)){
                while(set.contains(i)){
                    count++;
                    i++;
                }
                if (count>max){
                    max=count;
                }
            }
        }
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     *
     *
     *
     * 示例 1:
     *
     * 输入: nums = [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 示例 2:
     *
     * 输入: nums = [0]
     * 输出: [0]
     *
     *
     * 提示:
     *
     * 1 <= nums.length <= 104
     * -231 <= nums[i] <= 231 - 1
     *
     *
     * 进阶：你能尽量减少完成的操作次数吗？
     */
    @Test
    void test2() {
       int[] nums=new int[100];
       for (int i=0;i<nums.length;i++){
           if (nums[i]==0){
               int j=i+1;
               while(nums[j]==0){
                  j++;
                  if (j==nums.length){break;}
               }
               nums[i]=nums[j];
               nums[j]=0;
           }
       }

    }

    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("11111111");
        }
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("myrunnable");
        }
    }

    class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "success";
        }
    }

}
