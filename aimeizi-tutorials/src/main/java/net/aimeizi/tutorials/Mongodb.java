package net.aimeizi.tutorials;


public class Mongodb {
	
	private Mongo mongo;
	private DBCollection dbCollection;
	private DB db;
	
	public Mongodb() throws Exception{
		//使用ip地址创建Mongo对象
		mongo=new Mongo("127.0.0.1");
		//获取orcl数据库
		db=mongo.getDB("orcl");
		//判断是否存在集合person
		boolean b=db.collectionExists("person");
		System.out.println("是否存在集合[person]:"+b);
		dbCollection = db.getCollection("person");
		long count=dbCollection.count();
		System.out.println("总记录数是:"+count);
		DBCursor cursor =dbCollection.find().skip(20).limit(20);
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
		
		
	}
	
	
	
	public void list(){
		dbCollection=db.getCollection("person");
		BasicDBObject dbObject=new BasicDBObject();
		dbObject.put("age", new BasicDBObject("$gt",20).append("$lt", 60));
		DBCursor cursor = dbCollection.find(dbObject);
		System.out.println(cursor.count());
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
		
	}
	
	
	
	private boolean insert(){
		
		dbCollection=db.getCollection("person");
		BasicDBObject dbObject=new BasicDBObject();
		dbObject.put("name", "zhangsan");
		dbObject.put("age", 20);
		WriteResult writeResult = dbCollection.save(dbObject);
		System.out.println(writeResult.getN());
		return false;
	}
	
	private boolean delete(){
		dbCollection=db.getCollection("person");
		BasicDBObject dbObject=new BasicDBObject();
		dbObject.put("name", "zhangsan");
		WriteResult writeResult = dbCollection.remove(dbObject);
		System.out.println(writeResult.getN());
		return false;
	}
	
	
	private boolean update(){
		dbCollection=db.getCollection("person");
		BasicDBObject dbObject=new BasicDBObject();
		dbObject.put("name", "s0020");
		BasicDBObject dbObject2=new BasicDBObject();
		dbObject2.put("name", "s0020");
		dbObject2.put("age", 65);
		WriteResult writeResult = dbCollection.update(dbCollection.findOne(dbObject), dbObject2);
		System.out.println(writeResult.getN());
		return false;
	}
	
	
	private Object getOne(){
		dbCollection=db.getCollection("person");
		BasicDBObject dbObject=new BasicDBObject();
		dbObject.put("name", "s0020");
		//dbObject.put("age", 65);
		DBObject object=dbCollection.findOne(dbObject);
		System.out.println(object.toMap().get("name")+"\t"+object.toMap().get("age"));
		return object;
	}
	
	
	public static void main(String[] args) throws Exception{
		Mongodb mongodb=new Mongodb();
		//mongodb.insert();
		//mongodb.getOne();
		//mongodb.update();
		//mongodb.delete();
		//mongodb.list();
	}
}
