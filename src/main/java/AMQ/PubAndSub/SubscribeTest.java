/**   
* @Title: SubscribeTest.java 
* @Package main.java.AMQ.PubAndSub 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wujun.tang   
* @date 2017年1月10日 下午6:46:09 
* @version 
*/
package main.java.AMQ.PubAndSub;

/** 
 * @ClassName: SubscribeTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author wujun.tang
 * @date 2017年1月10日 下午6:46:09 
 *  
 */
public class SubscribeTest {
    public static void main(String[] args) {
        //订阅者
        new Subscribe().subscribe("小黑嘿嘿");
        new Subscribe().subscribe("======咻咻=======");
        new Subscribe().subscribe("9999999999哈哈哈哈哈哈哈999999999");
    }
}