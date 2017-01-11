/**   
* @Title: ActiveMQ.java 
* @Package main.java.AMQ 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author wujun.tang   
* @date 2017��1��10�� ����2:47:21 
* @version 
*/
package main.java.AMQ;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

/** 
 * @ClassName: ActiveMQ 
 * @Description: TODO(������һ�仰��������������) 
 * @author wujun.tang
 * @date 2017��1��10�� ����2:47:21 
 *  
 */
public class ActiveMQ {
	//Ĭ���û��������롢uri: tcp//localhost:61616/
	private final String DEFAULT_USERNAME = ActiveMQConnectionFactory.DEFAULT_USER;
	private final String DEFAULT_PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
	private final String DEFAULT_URL = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
	
	//��Ϣ�������ӹ���
	protected ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(DEFAULT_USERNAME, DEFAULT_PASSWORD, DEFAULT_URL);
	
	//Ŀ�ĵأ�����
	protected Destination queue = new ActiveMQQueue("jsun.queue");
	//Ŀ�ĵأ�����
	protected Destination topic = new ActiveMQQueue("jsun.topic");
	
}
