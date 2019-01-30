package com.demo.algorithm.lintCode;

import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 12. 带最小值操作的栈
实现一个带有取最小值min方法的栈，min方法将返回当前栈中的最小值。

你实现的栈将支持push，pop 和 min 操作，所有操作要求都在O(1)时间内完成。

样例
如下操作：push(1)，pop()，push(2)，push(3)，min()， push(1)，min() 返回 1，2，1

注意事项
如果堆栈中没有数字则不能进行min方法的调用
 * @author fuzamei
 *
 */
public class MinStack {
	
	private Stack<Integer> deque;
	private int min = Integer.MAX_VALUE;
	public MinStack() {
		deque = new Stack<>();
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int number) {
        deque.push(number);
        if(number < min){
        	min = number;
        }
    }

    /*
     * @return: An integer
     */
    public int pop() {
    	Integer pop = deque.pop();
    	if(pop.equals(min)){
    		min = Integer.MAX_VALUE;
    		Iterator<Integer> iterator = deque.iterator();
    		while(iterator.hasNext()){
    			Integer next = iterator.next();
    			if(min > next){
    				min = next;
    			}
    		}
    	}
    	return pop;
    }

    /*
     * @return: An integer
     */
    public int min() {
        return min;
    }
    
    public static void main(String[] args) {
    	MinStack m = new MinStack();
    	m.push(1);
//    	System.out.println(m.min());
    	System.out.println(m.pop());
    	m.push(2);
    	m.push(3);
    	System.out.println(m.min());
    	m.push(1);
    	System.out.println(m.min());
	}

}
