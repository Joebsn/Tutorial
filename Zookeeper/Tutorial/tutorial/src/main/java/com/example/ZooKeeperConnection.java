package com.example;

// import java classes
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

// import zookeeper classes
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;

public class ZooKeeperConnection 
{
   // declare zookeeper instance to access ZooKeeper ensemble
   private ZooKeeper zoo;
   final CountDownLatch connectedSignal = new CountDownLatch(1); //CountDownLatch is used to stop (wait) the main process until the client connects with the ZooKeeper ensemble.

   // Method to connect zookeeper ensemble.
   //creates a ZooKeeper object, connects to the ZooKeeper ensemble, and then returns the object
   public ZooKeeper connect(String host) throws IOException,InterruptedException
   {
	
      zoo = new ZooKeeper(host,5000,new Watcher() //The Watcher callback will be called once the client connects 
      //with the ZooKeeper ensemble and the Watcher callback calls the countDown method of the CountDownLatch to release the lock, await in the main process.
      {
         public void process(WatchedEvent we) 
         {
            if (we.getState() == KeeperState.SyncConnected) 
            {
               connectedSignal.countDown();
            }
         }
      });
		
      connectedSignal.await();
      return zoo;
   }

   // Method to disconnect from zookeeper server
   public void close() throws InterruptedException 
   {
      zoo.close();
   }
}