/**   
* @Title: PublishTest.java 
* @Package main.java.AMQ.PubAndSub 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wujun.tang   
* @date 2017年1月10日 下午6:45:23 
* @version 
*/
package main.java.AMQ.PubAndSub;

/** 
 * @ClassName: PublishTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author wujun.tang
 * @date 2017年1月10日 下午6:45:23 
 *  
 */
public class PublishTest {
    public static void main(String[] args) {
        //发布
        new Publish().publish();
    }
}
