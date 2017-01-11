/**   
* @Title: ProduceAndReceiveTest.java 
* @Package main.java.AMQ.PTP 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wujun.tang   
* @date 2017年1月10日 下午6:01:47 
* @version 
*/
package main.java.AMQ.PTP;

import javax.jms.JMSException;

/** 
 * @ClassName: ProduceAndReceiveTest 
 * @Description: ProduceAndReceiveTest
 * @author wujun.tang
 * @date 2017年1月10日 下午6:01:47 
 *  
 */
public class ProduceAndReceiveTest {
	 public static void main(String[] args) throws JMSException {
	        //发送消息
	        new Produce().produce();
	        //接收消息
	        new Receive().receive();
	    }
}
