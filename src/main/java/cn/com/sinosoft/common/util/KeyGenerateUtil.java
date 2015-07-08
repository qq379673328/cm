/**
 * 
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-4-30
 */
package cn.com.sinosoft.common.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 标识生成工具类
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-4-30
 */
public class KeyGenerateUtil {
	
	private static Random r = new Random();
	
	/**
	 * 生成编号
	 *
	 * 
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public static String genetatePk19(){
		return "" + System.currentTimeMillis() + r.nextInt(999999);
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 100; i++){
			System.out.println(KeyGenerateUtil.genetatePk19());
		}
	}
	
}
