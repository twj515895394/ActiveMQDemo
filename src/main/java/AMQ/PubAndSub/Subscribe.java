/**   
* @Title: Subscribe.java 
* @Package main.java.AMQ.PubAndSub 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wujun.tang   
* @date 2017年1月10日 下午6:12:29 
* @version 
*/
package main.java.AMQ.PubAndSub;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import main.java.AMQ.ActiveMQ;
/** 
 * @Description:订阅者 
 */
public class Subscribe extends ActiveMQ{
    /**
     * @Description :订阅发布模式：订阅者
     */
    public void subscribe(final String name){
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
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //由当前会话创建订阅者，指向目的地主题
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(new MessageListener() {

                public void onMessage(Message paramMessage) {
                    TextMessage message = (TextMessage)paramMessage;

                    try {
                        System.out.println("我是消费者:"+name+",我接收到的消息为：" + message.getText());
                    }
                    catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (JMSException e) {
            e.printStackTrace();
                try {
                    //关闭会话和连接
                    if(session != null){
                        session.close();
                    }
                    if(connection != null){
                    connection.close();
                    }
                } catch (JMSException e1) {
                    e1.printStackTrace();
                }
        }
    }
}
