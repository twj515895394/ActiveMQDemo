/**   
* @Title: ActiveMQ.java 
* @Package main.java.AMQ 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wujun.tang   
* @date 2017年1月10日 下午2:47:21 
* @version 
*/
package main.java.AMQ;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

/** 
 * @ClassName: ActiveMQ 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author wujun.tang
 * @date 2017年1月10日 下午2:47:21 
 *  
 */
public class ActiveMQ {
	//默认用户名、密码、uri: tcp//localhost:61616/
	private final String DEFAULT_USERNAME = ActiveMQConnectionFactory.DEFAULT_USER;
	private final String DEFAULT_PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
	private final String DEFAULT_URL = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
	
	//消息代理连接工厂
	protected ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(DEFAULT_USERNAME, DEFAULT_PASSWORD, DEFAULT_URL);
	
	//目的地：队列
	protected Destination queue = new ActiveMQQueue("jsun.queue");
	//目的地：主题
	protected Destination topic = new ActiveMQQueue("jsun.topic");
	
}
