/**   
* @Title: Receive.java 
* @Package main.java.AMQ.PTP 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author wujun.tang   
* @date 2017��1��10�� ����5:58:51 
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
 * @Description: ������ 
 * @author wujun.tang
 * @date 2017��1��10�� ����5:58:51 
 *  
 */
public class Receive extends ActiveMQ{
	/**
     * @Description :�Ӷ��н�����Ϣ
     */
	public void receive(){
		Connection connection = null;
        Session session = null;
        try {
            //��ȡһ������
            connection = connectionFactory.createConnection();
            //��������
            connection.start();
            /*
             * ����session�Ự:ʹ�ÿͻ���ȷ�Ϸ�ʽ�����Ա�֤ҵ�������֮����Ϣ�ŴӶ�����ɾ��
             * 
             * ��һ������:�Ƿ�֧���������Ϊtrue�������Եڶ�����������jms����������ΪSESSION_TRANSACTED
             * ��һ������Ϊfalseʱ���ڶ���������ֵ��ΪSession.AUTO_ACKNOWLEDGE��Session.CLIENT_ACKNOWLEDGE��DUPS_OK_ACKNOWLEDGE����һ����
             * Session.AUTO_ACKNOWLEDGEΪ�Զ�ȷ�ϣ��ͻ��˷��ͺͽ�����Ϣ����Ҫ������Ĺ����������ǽ��ն˷����쳣��Ҳ�ᱻ�����������ͳɹ���
             * Session.CLIENT_ACKNOWLEDGEΪ�ͻ���ȷ�ϡ��ͻ��˽��յ���Ϣ�󣬱������javax.jms.Message��acknowledge������jms�������Żᵱ�����ͳɹ�����ɾ����Ϣ��
             * Session.DUPS_OK_ACKNOWLEDGE��������ȷ��ģʽ��һ�����շ�Ӧ�ó���ķ������ôӴ�����Ϣ�����أ��Ự����ͻ�ȷ����Ϣ�Ľ��գ����������ظ�ȷ�ϡ�      
             */
            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            //�ɵ�ǰ�Ự���������ߣ�ָ��Ŀ�ĵض���
            MessageConsumer consumer = session.createConsumer(queue);
            while (true) {
                //ͨ�������߽�����Ϣ
                TextMessage message = (TextMessage) consumer.receive(2000);
                if (null != message) {
                    //ҵ����
                    System.out.println("�Ӷ����յ���Ϣ��" + message.getText());
                    //�ͻ���ȷ�Ͻ��յ���Ϣ����Ϣ�Ӷ�����ɾ��
                    message.acknowledge();
                } else {
                    System.out.println("��ǰδ�ܴӶ����յ��κ���Ϣ��");
                    //����ѭ����ע��֮���õ�ǰ�����߾��г�����ظö��е�����
                    //break;
                }
            }
        } catch (JMSException e) {
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
