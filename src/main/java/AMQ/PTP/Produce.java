/**   
* @Title: Produce.java 
* @Package main.java.AMQ.PTP 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author wujun.tang   
* @date 2017��1��10�� ����5:50:04 
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
 * @Description: ������
 * @author wujun.tang
 * @date 2017��1��10�� ����5:50:04 
 *  
 */
public class Produce extends ActiveMQ{
	/**
	 * 
	* @Title: produce 
	* @Description: ����з�����Ϣ
	* @author wujun.tang
	* @return void    �������� 
	* @throws
	 */
	public void produce(){
		Connection connection = null;
		Session session = null;
		try {
			//��ȡһ������
			connection = connectionFactory.createConnection();
			//��������
			connection.start();
			
			/*
             * ����session�Ự
             * ��һ������:�Ƿ�֧���������Ϊtrue�������Եڶ�����������jms����������ΪSESSION_TRANSACTED
             * ��һ������Ϊfalseʱ���ڶ���������ֵ��ΪSession.AUTO_ACKNOWLEDGE��Session.CLIENT_ACKNOWLEDGE��DUPS_OK_ACKNOWLEDGE����һ����
             * Session.AUTO_ACKNOWLEDGEΪ�Զ�ȷ�ϣ��ͻ��˷��ͺͽ�����Ϣ����Ҫ������Ĺ����������ǽ��ն˷����쳣��Ҳ�ᱻ�����������ͳɹ���
             * Session.CLIENT_ACKNOWLEDGEΪ�ͻ���ȷ�ϡ��ͻ��˽��յ���Ϣ�󣬱������javax.jms.Message��acknowledge������jms�������Żᵱ�����ͳɹ�����ɾ����Ϣ��
             * Session.DUPS_OK_ACKNOWLEDGE��������ȷ��ģʽ��һ�����շ�Ӧ�ó���ķ������ôӴ�����Ϣ�����أ��Ự����ͻ�ȷ����Ϣ�Ľ��գ����������ظ�ȷ�ϡ�      
             */
			session = connection.createSession(true, Session.SESSION_TRANSACTED);
			
			//�ɻỰ���������ߣ�ָ��Ŀ�ĵض���
			MessageProducer producer = session.createProducer(queue);
			 /*
             * ���������߳־û���ģʽ�������ֿ�ѡ
             * DeliveryMode.PERSISTENT ��activemq�رյ�ʱ�򣬶������ݽ��ᱻ����
             * DeliveryMode.NON_PERSISTENT ��activemq�رյ�ʱ�򣬶�����������ݽ��ᱻ��գ�������ΪĬ������
             */
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			for (int i = 0; i < 10; i++) {
				System.out.println("����з�����Ϣ"+i);
				//������Ϣ
                TextMessage message = session.createTextMessage();
                message.setText("����з�����Ϣ����"+i+"ë���Ͻ��ؼҳԷ�����");
                //�����߷�����Ϣ
                producer.send(message);
			}
			//������session����Ҫ�ύ����
            session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 try {
                 //�رջỰ������
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
