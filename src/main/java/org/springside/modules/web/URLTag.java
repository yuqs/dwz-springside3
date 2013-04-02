package org.springside.modules.web;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.utils.encode.EncodeUtils;

public class URLTag extends BodyTagSupport 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5636798157755500338L;
	
	private String encrypt = "true";
	
	public int doEndTag() throws JspException {
		
		BodyContent bodyContent = getBodyContent();
		String url = bodyContent.getString();
		
		if(encrypt != null && encrypt.equals("false"))
		{
			return writerBody(url);
		}
		
		if (!StringUtils.contains(url, "?"))
		{
			return writerBody(url);
		}
		
		String[] divs = url.split("[?]");
		String div1;
		try
		{
			div1 = EncodeUtils.urlEncode(divs[1]);
		}
		catch(Exception e)
		{
			throw new JspException(e);
		}
		
		String newUrl = divs[0] + "?" + div1;
		bodyContent.clearBody();

		return writerBody(newUrl);

	}
	
	private int writerBody(String bodyStr) throws JspException
	{
		JspWriter jspOut = pageContext.getOut();
		try 
		{
			jspOut.print(bodyStr);
		} 
		catch (IOException e) 
		{
			throw new JspException(e);
		}
		return super.doEndTag();
	}

	public String getEncrypt() {
		return encrypt;
	}

	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}
	
	
}
