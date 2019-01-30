package com.fuzamei.simpleDemo.classLoader.customizedClassLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {
	
	private final static Path DEFAULT_CLASS_DIR = Paths.get("C:","Users","fuzamei","Desktop");
	
	private final Path classDir;
	
	//使用默认的路径
	public MyClassLoader() {
		super();
		this.classDir = DEFAULT_CLASS_DIR;
	}
	//允许传入指定路径的class路径
	public MyClassLoader(String classDir) {
		super();
		this.classDir = Paths.get(classDir);
	}
	//指定class路径的同时，指定父类加载器
	public MyClassLoader(String classDir,ClassLoader parent) {
		super(parent);
		this.classDir = Paths.get(classDir);
	}
	
	public void tryResolve(Class<?> clazz) {
		this.resolveClass(clazz);
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		//读取class的二进制数据
		byte[] classBytes = this.readClassBytes(name);
		if(classBytes==null || classBytes.length == 0) {
			throw new ClassNotFoundException("can not load the class " + name);
		}
		return this.defineClass(name, classBytes, 0, classBytes.length);
	}
	//将class文件读入内存
	private byte[] readClassBytes(String name) throws ClassNotFoundException {
		String classPath = name.replace(".", "/");
		Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
		if(!classFullPath.toFile().exists()) {
			throw new ClassNotFoundException("The class " + name + " not found.");
		}
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			Files.copy(classFullPath, baos);
			byte[] byteArray = baos.toByteArray();
			return byteArray;
		}catch (IOException e) {
			throw new ClassNotFoundException("load the class " + name + " occur error.", e);
		}
	}
	@Override
	public String toString() {
		return "My ClassLoader";
	}
	
	
	public static void main(String[] args) {
//		String string = DEFAULT_CLASS_DIR.toAbsolutePath().toString();
//		System.out.println(string);
		String name = "com.fuzamei.demo";
		String classPath = name.replace(".", "/");
		Path resolve = Paths.get("C:","Users","fuzamei","Desktop","com","fuzamei","demo").resolve(Paths.get(classPath + ".class"));
		System.out.println(resolve.toAbsolutePath().toString());
	}

}
