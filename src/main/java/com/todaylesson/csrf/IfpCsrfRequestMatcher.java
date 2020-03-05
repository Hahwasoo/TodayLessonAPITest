package com.todaylesson.csrf;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**

 * IfpCsrfRequestMatcher.java

 * - ��û�� ���� ������ ��ť��Ƽ���� CSRF�� �����Ұ��� �ƴҰ��� �Ǵ�

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


public class IfpCsrfRequestMatcher implements RequestMatcher {
	

  final Log logger = LogFactory.getLog( IfpAccessDeniedHandler.class );

	

    @Override

    public boolean matches(HttpServletRequest request) {

    	

    	String strUrl = request.getRequestURL().toString();

    	String strUri = request.getRequestURI();

    	String queryString = request.getQueryString() == null ? "" : request.getQueryString();

    	String contentType = request.getContentType() == null ? "" : request.getContentType();

 

    	//---------------------------------------------------------

    	// CSRF ���͸�, �ʿ�� �߰� �ϼ���. ���⿡ �߰����Ͻø� 403 ���� �߻�!

    	//---------------------------------------------------------

    	// AJAX CALL

        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With")))        	                                           

        	                                           return false;
  //ȭ��
     // ùȭ��
        else if ("/".equals(strUri))                   return false;        
     // ùȭ��
        else if ("/todaylesson".equals(strUri))                   return false;
     //������ȭ��
        else if ("/todaylessonadmin".equals(strUri))                   return false;  
     //�ôϾ�ȭ��
        else if ("/todaylessonsenior".equals(strUri))                   return false;   
     //�α���
        else if ("/customlogin".equals(strUri))                   return false;
     //�α׾ƿ�
        else if ("/logout".equals(strUri))                   return false;
        
      

        
        
  //����
      //TodayLessonController
      //ȸ������
        else if ("/join".equals(strUri))                   return false;
      //���̵�ã��
        else if ("/findId".equals(strUri))                   return false;
      //ȸ�����԰��
        /*else if ("/joinresult".equals(strUri))                   return false;*/
      //idã��
        else if ("/userSearch".equals(strUri))                   return false;
      //User_YI_FreeBoard_Controller
      //�����Խ���
        else if ("/freeboard".equals(strUri))                   return false;
      //�Խñۻ󼼺���
        else if ("/detail/{freeboard_no}".equals(strUri))                   return false;   
        
        
        
   //����
      //���̺��ã�� 
        else if ("/findPw".equals(strUri))                   
        	 return false; 
      //���� �̸��� & ���̵� �� �޾Ƽ� �ӽ� ��й�ȣ ����
        else if("/findPassword".equals(strUri))
             return false;
     //���� �� �������� �������� ����
        else if("/hm_us_mymanage".equals(strUri))
             return false;
     //���� �� ���� ���� 1�� ��й�ȣ �����ޱ�
        else if("/hm_us_mymanage2".equals(strUri))
             return false; 
     //   
        
        
   //����
        
        
        
        
   //����
      //�����۾���
        else if ("/lesson_write".equals(strUri))                   
        	 return false;  
      //�����ּ��˾�
        else if ("/jusoPopup".equals(strUri))    
        	 return false;   
        
        
        
        

        else {

        	logger.info("###################################### request.getRequestURL() :: " + strUrl);

        	logger.info("###################################### request.getRequestURI() :: " + strUri);

        	logger.info("###################################### request.getQueryString() :: " + queryString);

        	logger.info("###################################### request.getContentType() :: " + contentType);        	

        }

        return true;

    }
}
