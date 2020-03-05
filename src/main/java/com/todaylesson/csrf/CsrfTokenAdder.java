package com.todaylesson.csrf;

import java.io.ByteArrayOutputStream; 

import java.io.IOException;

import java.io.OutputStreamWriter;

import java.io.PrintWriter;

 

import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.ServletException;

import javax.servlet.ServletOutputStream;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletResponseWrapper;

 

import org.apache.commons.lang.StringUtils;

import org.springframework.security.web.csrf.CsrfToken;

/**

 * CsrfTokenAdder.java

 * - CSRF Ptotection�� ���� Text/Html "����"�� �����±� Csrf Token ����

 * 

 * ################ Spring Security CSRF Protection ���� ##################

 * [CsrfTokenAdder.java, ��������] ###"���信 �±� ����"###

 * - Spring Security4 �̻��� CSRF Enabled�� �⺻����

 * - HttpServletRequest(�Ϲ�  JSP��û), XMLHttpRequest(AJAX) ��û�̵� ���信 Csrf �����ױ� ����

 * - CSRF Protection�� ���� Text/Html ���信 �����±� Xsrf Token ����

 * - AJAX JSON����(�޺� ä����)�� �����±� Csrf Token ������ �ʿ����.

 * 

 * [security-config.xaml]

 * - �ִ��� Spring Security ������ �ʿ���� �κп� ���� ����(/resources, /images ��)

 * - RequestMatcher�� ���� ����(������ ��ť��Ƽ���� ��û�� ���� CSRF�� �����Ұ��� �ƴҰ��� �Ǵ�)

 * - AccessDeniedHandler�� ���� ����(403 �����߻��� ����)

 * 

 * [IfpCsrfRequestMatcher.java] ###"��û�� ���� CSRF ���뿩�� ����"###

 * - ������ ��ť��Ƽ���� CSRF�� �����Ұ��� �ƴҰ��� �Ǵ�

 * - AJAX CALL, ùȭ��, �����޴��ε�, �˾��ε� ��û�� CSRF �������� ����

 * - (TEXT/HTML�� �������� ������ �κ��� CsrfTokenAdder ���Ϳ��� Csrf �����±� ������)

 * - 403 Forbidden ������ �߻��ϴ� �κ��� �ִٸ� �̰����� �߰��� ��

 * 

 * [IfpAccessDeniedHandler.java]

 * - 403 Forbidden ���� �߻��ϴ� ��� ó���ϴ� �ڵ鷯

 * ######################################################################

 */

public class CsrfTokenAdder implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String url = ((HttpServletRequest)request).getRequestURL().toString();

		String responseText = null;

		HtmlResponseWrapper  newResponse = null;

		CsrfToken token = null;

		

		// ���ʹ�� ���� ������ ���

		final String[] EXCLUDE_URL_LIST = {"/webjars", "/favicon.ico","/fileupload", "/filedownload", "/css", "/js", "/images", "/fonts", "/ckeditor", "/WEB-INF" };

		

		String paramName = null;

		String strToken = null; 

		String tokenStr = null;

		String csrfTokenInput = null;

		String replacedText = null;	

		boolean excludeState = false;

		

		//-------------------------------------

		// ���� ���� URI SKIP

		//-------------------------------------

		for( String target : EXCLUDE_URL_LIST )

		{

			if( url.indexOf( target ) > -1 )

			{

				excludeState = true;

				break;

			}

		}

 

		if( excludeState )

		{

			chain.doFilter( request, response );

			return;

		}

		//-------------------------------------

		

		//-------------------------------------------------------------

		// AJAX APPLICATION/JSON RESPONSE, NON TEXT/HTML

		// ȭ�� �޺� �ʱ�ȭ ��� AJAX CALL �ϴ� ���

		// �� ���� CSRF �±׸� ������ �ʿ����.

		//-------------------------------------------------------------

        if ( ((HttpServletResponse)response).getContentType() != null 

        		&& "XMLHttpRequest".equals(((HttpServletRequest) request).getHeader("X-Requested-With"))

        		&& ((HttpServletResponse)response).getContentType().contains("application/json")) {  

			response.getWriter().write(responseText);

        } 

        //-------------------------------------------------------------

        // ������ text/html�� ��� Csrf ���� �±׸� ���� </form> �տ� �����Ѵ�. 

        // �Ϲ� JSP�� ������ ����, AJAX TEXT/HTML ������ �˾� ȭ��ε� ��

        //-------------------------------------------------------------

        else if ( ((HttpServletResponse)response).getContentType() != null         		

        		&& ((HttpServletResponse)response).getContentType().contains("text/html")) {   

        	

        	newResponse = new HtmlResponseWrapper ((HttpServletResponse) response);	

    		chain.doFilter(request, newResponse);

    		

    		responseText = newResponse.getCaptureAsString();  

			

        	token = (CsrfToken) request.getAttribute("_csrf");

            paramName = token.getParameterName();

            strToken = token.getToken();

			  

			if (token != null) {

				tokenStr = String.format("<input type=\"hidden\" name=\"%s\" id=\"%s\" value=\"%s\" />",

						paramName, paramName, strToken);

			}

			

			// �Ϲ� JSP�� ������ ������ ���(HttpServletRequest, TEXT/HTML����)

			if (!StringUtils.contains(responseText, "_csrf.parameterName")

				&& StringUtils.contains(responseText, "<form>")) {

				 csrfTokenInput = tokenStr + "</form>";

			     replacedText = StringUtils.replace(responseText, "</form>", csrfTokenInput);

			} 

			// �˾�â �ε�(XMLHttpRequest��û,AJAX TEXT/HTML����)

			else {

				csrfTokenInput = "<form>" + tokenStr + "</form></body>";

				replacedText = StringUtils.replace(responseText, "</body>", csrfTokenInput);

			}	

		        

			response.getWriter().write(replacedText);

		}

		else {	

			chain.doFilter(request, response);

		}

	}

 

	@Override

	public void init(FilterConfig arg0) throws ServletException {

	}

 

	@Override

	public void destroy() {

	}
	
}
