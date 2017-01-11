/**   
* @Title: Receive.java 
* @Package main.java.AMQ.PTP 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wujun.tang   
* @date 2017年1月10日 下午5:58:51 
* @version 
*/
package main.java.AMQ.PTP;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import main.java.AMQ.ActiveMQ;

/** 
 * @ClassName: Receive 
 * @Description: 接收者 
 * @author wujun.tang
 * @date 2017年1月10日 下午5:58:51 
 *  
 */
public class Receive extends ActiveMQ{
	/**
     * @Description :从队列接收消息
     */
	public void receive(){
		Connection connection = null;
        Session session = null;
        try {
            //获取一个连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            /*
             * 创建session会话:使用客户端确认方式，可以保证业务处理完毕之后，消息才从队列中删除
             * 
             * 第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
             * 第一个参数为false时，第二个参数的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
             * Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功。
             * Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
             * Session.DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。      
             */
            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            //由当前会话创建接收者，指向目的地队列
            MessageConsumer consumer = session.createConsumer(queue);
            while (true) {
                //通过订阅者接收消息
                TextMessage message = (TextMessage) consumer.receive(2000);
                if (null != message) {
                    //业务处理
                    System.out.println("从队列收到消息：" + message.getText());
                    //客户端确认接收到消息，消息从队列中删除
                    message.acknowledge();
                } else {
                    System.out.println("当前未能从队列收到任何消息。");
                    //跳出循环，注释之后，让当前订阅者具有持续监控该队列的能力
                    //break;
                }
            }
        } catch (JMSException e) {
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
