/**   
* @Title: SubscribeTest.java 
* @Package main.java.AMQ.PubAndSub 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author wujun.tang   
* @date 2017��1��10�� ����6:46:09 
* @version 
*/
package main.java.AMQ.PubAndSub;

/** 
 * @ClassName: SubscribeTest 
 * @Description: TODO(������һ�仰��������������) 
 * @author wujun.tang
 * @date 2017��1��10�� ����6:46:09 
 *  
 */
public class SubscribeTest {
    public static void main(String[] args) {
        //������
        new Subscribe().subscribe("С�ںٺ�");
        new Subscribe().subscribe("======����=======");
        new Subscribe().subscribe("9999999999��������������999999999");
    }
}