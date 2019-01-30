package com.demo.mongodb;

import java.util.List;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
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
public class MongodbDemo {
	public static void main(String[] args) {
		ServerAddress serverAddress = new ServerAddress("localhost",27017);
		MongoCredential mongoCredential = MongoCredential.createCredential("root", "admin", new char[]{'r','o','o','t'});
		
		MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
		MongoClient mongoClient = new MongoClient(serverAddress,mongoCredential,mongoClientOptions);
		
//		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
//		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase("foobar");
		MongoIterable<String> listCollectionNames = database.listCollectionNames();
		MongoCursor<String> iterator = listCollectionNames.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
		mongoClient.close();
	}
}
