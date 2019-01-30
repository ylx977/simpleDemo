package com.demo.mongodb;

import java.util.List;

import org.bson.Document;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 * 引用jar包
 * <!-- https://mvnrepository.com/artifact/org.mongodb/mongo-java-driver -->
 * 
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongo-java-driver</artifactId>
    <version>3.6.0</version>
</dependency>

 * @author fuzamei
 *
 */
public class MongodbInsertDemo {
	public static void main(String[] args) {
		MongoClientURI mongoClientURI = new MongoClientURI("mongodb://root:root@localhost:27017/admin");//也可以通过配置uri来连接数据库
//		ServerAddress serverAddress = new ServerAddress("localhost",27017);
//		MongoCredential mongoCredential = MongoCredential.createCredential("root", "admin", new char[]{'r','o','o','t'});
//		
//		MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
		MongoClient mongoClient = new MongoClient(mongoClientURI);
		
		MongoDatabase database = mongoClient.getDatabase("foobar");
//		MongoCollection<Document> collection = database.getCollection("mycoll");
		MongoCollection<Document> collection = database.getCollection("persons");
		
		//document本质就是一个bson
		Document document = new Document("age",12)
				.append("name", "jack")
				.append("email", "2222222@qq.com")
				.append("c", 99d)
				.append("m", 99d)
				.append("e", 99d)
				.append("country", "China");
		
		
		collection.insertOne(document);
		mongoClient.close();
	}
}
