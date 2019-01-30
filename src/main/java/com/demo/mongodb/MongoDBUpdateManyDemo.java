package com.demo.mongodb;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.*;//注意这里有一个静态导入，方便后面查询条件的类似函数式编程
import static com.mongodb.client.model.Updates.*;//注意这里有一个静态导入，方便后面查询条件的类似函数式编程

public class MongoDBUpdateManyDemo {
	public static void main(String[] args) {
		ServerAddress serverAddress = new ServerAddress("localhost",27017);
		MongoCredential mongoCredential = MongoCredential.createCredential("root", "admin", new char[]{'r','o','o','t'});
		
		MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
		MongoClient mongoClient = new MongoClient(serverAddress,mongoCredential,mongoClientOptions);
		
		
		MongoDatabase database = mongoClient.getDatabase("foobar");
		MongoCollection<Document> collection = database.getCollection("persons");
		
		//Update Multiple Documents
		UpdateResult updateResult = collection.updateMany(eq("age", 127d), inc("age", -100));
		System.out.println(updateResult.getModifiedCount());
		
		mongoClient.close();
	}
}
