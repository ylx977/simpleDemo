package com.fuzamei.simpleDemo.coudownlatch.pagequery;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PageQuery {

	public static void main(String[] args) throws Exception {
		SqlSessionFactory sqlSessionFactory = GetSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		
		//纯同步查询分页，需要7182ms & 7477ms & 7478ms
//		long start = System.currentTimeMillis();
//		List<User> findUsersByPage = mapper.findUsersByPage(2000000, 20);
//		int findUsersCount = mapper.findUsersCount();
//		long end = System.currentTimeMillis();
//		System.out.println("耗时：" + (end-start) + "ms");
//		findUsersByPage.forEach(System.out::println);
//		System.out.println("总共有多少数据：" + findUsersCount);
		
		
		//异步转同步查询，需要 4895ms & 4873ms & 4853ms
		long start = System.currentTimeMillis();
		CountDownLatch LATCH = new CountDownLatch(2);
		FutureTask<List<User>> findUsersTask = new FutureTask<List<User>>(()->{
			List<User> findUsersByPage = mapper.findUsersByPage(2000000, 20);
			LATCH.countDown();
			return findUsersByPage;
		});
		FutureTask<Integer> findCountTask = new FutureTask<Integer>(()->{
			int findUsersCount = mapper.findUsersCount();
			LATCH.countDown();
			return findUsersCount;
		});
		new Thread(findUsersTask).start();
		new Thread(findCountTask).start();
		LATCH.await();
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end-start) + "ms");
		findUsersTask.get().forEach(System.out::println);
		System.out.println("总共有多少数据：" + findCountTask.get());
	}
	
}
