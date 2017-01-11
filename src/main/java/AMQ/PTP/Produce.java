/**   
* @Title: Produce.java 
* @Package main.java.AMQ.PTP 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wujun.tang   
* @date 2017年1月10日 下午5:50:04 
* @version 
*/
package main.java.AMQ.PTP;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import main.java.AMQ.ActiveMQ;

/** 
 * @ClassName: Produce 
 * @Description: 发送者
 * @author wujun.tang
 * @date 2017年1月10日 下午5:50:04 
 *  
 */
public class Produce extends ActiveMQ{
	/**
	 * 
	* @Title: produce 
	* @Description: 向队列发送消息
	* @author wujun.tang
	* @return void    返回类型 
	* @throws
	 */
	public void produce(){
		Connection connection = null;
		Session session = null;
		try {
			//获取一个连接
			connection = connectionFactory.createConnection();
			//启动连接
			connection.start();
			
			/*
             * 创建session会话
             * 第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
             * 第一个参数为false时，第二个参数的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
             * Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功。
             * Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
             * Session.DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。      
             */
			session = connection.createSession(true, Session.SESSION_TRANSACTED);
			
			//由会话创建发送者，指向目的地队列
			MessageProducer producer = session.createProducer(queue);
			 /*
             * 设置生产者持久化的模式，有两种可选
             * DeliveryMode.PERSISTENT 当activemq关闭的时候，队列数据将会被保存
             * DeliveryMode.NON_PERSISTENT 当activemq关闭的时候，队列里面的数据将会被清空，该设置为默认设置
             */
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			for (int i = 0; i < 10; i++) {
				System.out.println("向队列发送消息"+i);
				//创建消息
                TextMessage message = session.createTextMessage();
                message.setText("向队列发送消息：“"+i+"毛，赶紧回家吃饭！”");
                //发送者发送消息
                producer.send(message);
			}
			//事务性session，需要提交事务
            session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 try {
                 //关闭会话和连接
                 if(session != null){
                 session.close();
                 }
                 if(connection != null){
                     connection.close();
                 }
             } catch (JMSException e) {
                 e.printStackTrace();
             }
		}
	}
}
