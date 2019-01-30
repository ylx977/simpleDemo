package com.demo.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Filters.*;//注意这里有一个静态导入，方便后面查询条件的类似函数式编程

public class MongoDBQueryFilterDemo {
	public static void main(String[] args) {
		ServerAddress serverAddress = new ServerAddress("localhost",27017);
		MongoCredential mongoCredential = MongoCredential.createCredential("root", "admin", new char[]{'r','o','o','t'});
		
		MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
		MongoClient mongoClient = new MongoClient(serverAddress,mongoCredential,mongoClientOptions);
		
		
		MongoDatabase database = mongoClient.getDatabase("foobar");
		MongoCollection<Document> collection = database.getCollection("mycoll");
		
		//Get A Single Document That Matches a Filter
		Document myDoc = collection.find(eq("i", 59)).first();
		System.out.println(myDoc==null ? null:myDoc.toJson());
		
		
		
		mongoClient.close();
	}
}
