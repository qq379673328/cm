/**
 * 
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2014-10-29
 */
package cn.com.sinosoft.common.util.spring;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * spring日期自定义绑定
 * 
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2014-10-29
 */
public class CustomDateEditor extends PropertyEditorSupport {

	private final SimpleDateFormat dateFormat;
	private final boolean allowEmpty;
	private final int exactDateLength;

	public CustomDateEditor(SimpleDateFormat dateFormat, boolean allowEmpty) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
		this.exactDateLength = -1;
	}

	public CustomDateEditor(SimpleDateFormat dateFormat,
			boolean allowEmpty, int exactDateLength) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
		this.exactDateLength = exactDateLength;
	}

	public void setAsText(String text) throws IllegalArgumentException {
		if ((this.allowEmpty) && (!(StringUtils.hasText(text)))) {
			setValue(null);
		} else {
			if ((text != null) && (this.exactDateLength >= 0)
					&& (text.length() != this.exactDateLength)) {
				throw new IllegalArgumentException(
						"Could not parse date: it is not exactly"
								+ this.exactDateLength + "characters long");
			}
			try {
				if(text.contains("-")){//日期字符串
					setValue(new Date(this.dateFormat.parse(text).getTime()));
				}else{//时间戳
					setValue(new Date(Long.valueOf(text)));
				}
			} catch (ParseException ex) {
				throw new IllegalArgumentException("Could not parse date: "
						+ ex.getMessage(), ex);
			}
		}
	}

	public String getAsText() {
		Date value = (Date) getValue();
		return ((value != null) ? this.dateFormat.format(value) : "");
	}

}
