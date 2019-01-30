package com.fuzamei.simpleDemo.classLoader.customizedClassLoader2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BrokerDelegateClassLoader extends ClassLoader {
	
private final static Path DEFAULT_CLASS_DIR = Paths.get("C:","Users","fuzamei","Desktop");
	
	private final Path classDir;
	
	//使用默认的路径
	public BrokerDelegateClassLoader() {
		super();
		this.classDir = DEFAULT_CLASS_DIR;
	}
	//允许传入指定路径的class路径
	public BrokerDelegateClassLoader(String classDir) {
		super();
		this.classDir = Paths.get(classDir);
	}
	//指定class路径的同时，指定父类加载器
	public BrokerDelegateClassLoader(String classDir,ClassLoader parent) {
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

	/**
	 * 重写loadClass方法
	 */
	@Override
	protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		
		//根据类的全路径名称进行加锁，确保每个类在多线程的情况下只被加载一次
		synchronized (getClassLoadingLock(name)) {
			//看已加载类的缓存中查看该类是否已经被加载了，如果已经加载就直接返回
			Class<?> klass = findLoadedClass(name);
			
			//如果缓存中没有被加载的类，则需要对其进行首次加载
			if(klass == null) {
				//如果类的全路径以java或者javax开头直接委托给系统类加载器对其进行加载
				if(name.startsWith("java.") || name.startsWith("javax")) {
					try {
						klass = getSystemClassLoader().loadClass(name);
					} catch (Exception e) {
						
					}
				}else {
					//其它的类用我们自定义的类加载器进行加载
					try {
						klass = this.findClass(name);
					} catch (Exception e) {
						
					}
					//如果还是没有加载到的话，则委托给父类加载器进行加载或者系统类加载器进行加载
					if(klass == null) {
						if(getParent() != null) {
							klass = getParent().loadClass(name);
						}else {
							klass = getSystemClassLoader().loadClass(name);
						}
					}
				}
			}
			
			//如果还是加载不到，直接报错
			if(klass == null) {
				throw new ClassNotFoundException("The class " + name + " not found.");
			}
			if(resolve) {
				resolveClass(klass);
			}
			return klass;
		}
		
	}
	
}
