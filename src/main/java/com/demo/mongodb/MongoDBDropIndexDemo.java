package com.demo.mongodb;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.*;//注意这里有一个静态导入，方便后面查询条件的类似函数式编程

public class MongoDBDropIndexDemo {
	public static void main(String[] args) {
		
//		MongoClientURI mongoClientURI = new MongoClientURI("mongodb://root:root@localhost:27017/admin");
		
		ServerAddress serverAddress = new ServerAddress("localhost",27017);
		MongoCredential mongoCredential = MongoCredential.createCredential("root", "admin", new char[]{'r','o','o','t'});
		
		MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
		MongoClient mongoClient = new MongoClient(serverAddress,mongoCredential,mongoClientOptions);
		
		
		MongoDatabase database = mongoClient.getDatabase("foobar");
		MongoCollection<Document> collection = database.getCollection("persons");
		
		//Create Indexes
//		collection.dropIndex("name_1");//根据名字删除
		collection.dropIndex(new Document("name", 1));//根据key来删除
		
		mongoClient.close();
	}
}
