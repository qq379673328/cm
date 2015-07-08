/**
 * 
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-6-16
 */
package cn.com.sinosoft.core.exception;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 自定义异常处理
 * 
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-6-16
 */
public class CustomSimpleMappingExceptionResolver extends
		SimpleMappingExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		String viewName = determineViewName(ex, request);
		if (viewName != null) {
			Integer statusCode = determineStatusCode(request, viewName);
			if (statusCode != null) {
				applyStatusCodeIfPossible(request, response, statusCode);
			}
			// 验证失败异常
			if (ex instanceof ConstraintViolationException) {//hibernate 验证失败
				Set<ConstraintViolation<?>> viols = ((ConstraintViolationException) ex)
						.getConstraintViolations();
				Iterator<ConstraintViolation<?>> ite = viols
						.iterator();
				StringBuffer message = new StringBuffer();
				while (ite.hasNext()) {
					ConstraintViolation<?> next = ite.next();
					message.append(next.getMessage() + "<br />");
				}
				Exception exce = new Exception(message.toString());
				exce.setStackTrace(new StackTraceElement[]{});
				return getModelAndView(viewName, exce, request);
			} else {
				ex.printStackTrace();
				// 不返回异常信息
				return getModelAndView(viewName, null, request);
			}
		} else {
			return null;
		}
	}

}
