/**
 * 
 */
package com.proxy.http.plug;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.JstlView;

/**
 * @author Administrator
 *
 */
public class JstlViewPlus extends JstlView {
	/**
	 * 部署路径属性名称
	 */
	public static final String CONTEXT_PATH = "base";
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		model.put(CONTEXT_PATH, "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
		super.renderMergedOutputModel(model, request, response);
	}
	/**
	 * Exposes a JSTL LocalizationContext for Spring's locale and MessageSource.
	 * @see JstlUtils#exposeLocalizationContext
	 */
	
}
