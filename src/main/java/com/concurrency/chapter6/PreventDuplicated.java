package com.concurrency.chapter6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class PreventDuplicated {

//	private static final String LOCK_PATH = "C:\\Users\\fuzamei\\Desktop";
	private static final String LOCK_PATH = "/home/";
	private static final String LOCK_FILE = ".lock";
	private static final String PERMISSIONS = "rw-------";
	
	public static void main(String[] args) throws IOException {
		//注入hook线程，在程序退出时删除lock文件
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				System.out.println("The program received kill signal");
				getLockFile().toFile().delete();
			}
		});
		
		checkRunning();
		
		for(;;){
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("pargram is running.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private static void checkRunning() throws IOException {
		Path path = getLockFile();
		Set<String> supportedFileAttributeViews = path.getFileSystem().supportedFileAttributeViews();
		supportedFileAttributeViews.forEach(System.out::println);
		if(path.toFile().exists()){
			throw new RuntimeException("the program already running");
		}
		Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);
		
		//windows系统不支持
		Files.createFile(path, PosixFilePermissions.asFileAttribute(perms));
	}


	private static Path getLockFile(){
		return Paths.get(LOCK_PATH, LOCK_FILE);
	}
	
}
