package com.demo.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBQueryAllDemo {
	public static void main(String[] args) {
		ServerAddress serverAddress = new ServerAddress("localhost",27017);
		MongoCredential mongoCredential = MongoCredential.createCredential("root", "admin", new char[]{'r','o','o','t'});
		
		MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
		MongoClient mongoClient = new MongoClient(serverAddress,mongoCredential,mongoClientOptions);
		
		
		MongoDatabase database = mongoClient.getDatabase("foobar");
		MongoCollection<Document> collection = database.getCollection("mycoll");
		
		//Find All Documents in a Collection
		MongoCursor<Document> iterator = collection.find().iterator();
		while(iterator.hasNext()){
			Document document = iterator.next();
			System.out.println(document.toJson());
		}
		mongoClient.close();
	}
}
