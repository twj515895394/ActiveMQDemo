/**   
* @Title: ProduceAndReceiveTest.java 
* @Package main.java.AMQ.PTP 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author wujun.tang   
* @date 2017��1��10�� ����6:01:47 
* @version 
*/
package main.java.AMQ.PTP;

import javax.jms.JMSException;

/** 
 * @ClassName: ProduceAndReceiveTest 
 * @Description: ProduceAndReceiveTest
 * @author wujun.tang
 * @date 2017��1��10�� ����6:01:47 
 *  
 */
public class ProduceAndReceiveTest {
	 public static void main(String[] args) throws JMSException {
	        //������Ϣ
	        new Produce().produce();
	        //������Ϣ
	        new Receive().receive();
	    }
}
