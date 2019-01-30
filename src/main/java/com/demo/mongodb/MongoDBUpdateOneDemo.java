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

public class MongoDBUpdateOneDemo {
	public static void main(String[] args) {
		ServerAddress serverAddress = new ServerAddress("localhost",27017);
		MongoCredential mongoCredential = MongoCredential.createCredential("root", "admin", new char[]{'r','o','o','t'});
		
		MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
		MongoClient mongoClient = new MongoClient(serverAddress,mongoCredential,mongoClientOptions);
		
		
		MongoDatabase database = mongoClient.getDatabase("foobar");
		MongoCollection<Document> collection = database.getCollection("persons");
		
		//Update a Single Document
		//Document对象就是Bson，可以把Document对象理解为“{}”
//		UpdateOptions updateOptions = new UpdateOptions();
//		updateOptions.upsert(true);//相当于db.persons.update({},{},true);
		
		UpdateResult updateResult = collection.updateOne(eq("name","zhangsuying"),new Document("$set",new Document("age", 22d)));
		System.out.println("修改条数为："+updateResult.getModifiedCount());
		
		mongoClient.close();
	}
}
