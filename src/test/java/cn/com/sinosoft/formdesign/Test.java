package cn.com.sinosoft.formdesign;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Test {
	
	public static void main(String[] args) {
		try {
			Template tem = getTemplage();
			File outFile = new File("d://temp//简历333.doc");
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
			tem.process(getData(), writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static Template getTemplage() throws Exception{
		Configuration con = new Configuration();
		con.setDefaultEncoding("utf-8");
		con.setClassForTemplateLoading(Test.class, "/template/freemarker");
		con.setClassicCompatible(true);
		Template tem = con.getTemplate("test.xml");
		return tem;
	}
	
	public static Map<String, Object> getData(){
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("title", "占位符_我是标题");
		ret.put("tjzw", "占位符_推荐职位");
		ret.put("tjsj", "占位符_推荐时间");
		return ret;
	}

}
