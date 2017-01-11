/**   
* @Title: Subscribe.java 
* @Package main.java.AMQ.PubAndSub 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author wujun.tang   
* @date 2017��1��10�� ����6:12:29 
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
 * @Description:������ 
 */
public class Subscribe extends ActiveMQ{
    /**
     * @Description :���ķ���ģʽ��������
     */
    public void subscribe(final String name){
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
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //�ɵ�ǰ�Ự���������ߣ�ָ��Ŀ�ĵ�����
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(new MessageListener() {

                public void onMessage(Message paramMessage) {
                    TextMessage message = (TextMessage)paramMessage;

                    try {
                        System.out.println("����������:"+name+",�ҽ��յ�����ϢΪ��" + message.getText());
                    }
                    catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (JMSException e) {
            e.printStackTrace();
                try {
                    //�رջỰ������
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
