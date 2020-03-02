package com.todaylesson.apitest;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.todaylesson.DTO.MemberDTO;
import com.todaylesson.service.Hm_Us_MyManageService;
import com.todaylesson.service.MailSendService;

@Controller
public class FindPasswordController {
	
	@Autowired
	   private MailSendService mailSender;
	
	@RequestMapping("/findPw")
	public String findPw()
	{
		
		return "hm_find_pw";
	}
	@Resource(name="hm_us_mymanage")
	private Hm_Us_MyManageService hm_mymanageservice;
	
	@RequestMapping(value="/findPassword",method=RequestMethod.POST)
	@ResponseBody
	public String findPassword(@RequestParam("inputId_2")String member_id,
            @RequestParam("inputEmail_2") String member_email
            ,HttpServletRequest request){
		
		mailSender.mailSendWithPassword(member_id, member_email, request);
		System.out.println(member_email);
		
		return "/userSearchPassword";
	}
	
	@RequestMapping("/hmapitest")
	public String hmapitest()
	{
		return "hm_api_test";
	}
	
	
	@RequestMapping("/hmexceltest")
	public String hmexceltest()
	{
		return "hm_exceltest";
	}
	
	//�� ��������
	@RequestMapping("/hm_us_mymanage")
	public String hm_us_mymanage1()
	{
		
		return "hm_us_mymanage";
	}
	
	
	//���������� �α��ε� ���̵��� ��ť��Ƽ���� �����ͼ� �н����� ������
	@RequestMapping("/hm_us_mymanage2") 
	@ResponseBody 
	public String currentUserName(Principal principal
								,@RequestParam("member_pwd")String member_pwd) 
	{
		String member_id = principal.getName();
	
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("member_id", member_id);
		map.put("member_pwd", member_pwd);
	
		boolean result = hm_mymanageservice.checkpwd(map);	 
		 
  //result�� ó���ϰ� return
		System.out.println(result);
		
		if(result == true) 
		{
			return "hm_us_mymanagedetail";
		}
		else {
			return "hm_us_mymanagedetail2";
		}
	}

	
	
	
	//������ - ȸ������(��ü ����Ʈ)
	@RequestMapping("/admin_manage")
	public String adminmembermanage(Model model)
	{
		
		List<MemberDTO> list =hm_mymanageservice.adminmemberlist();
		model.addAttribute("list",list);
		return "hm_ad_user_memmanage";
		
	}
	
	
	
	
	
	/*
	
	
	//���̾�ϰ� �ذ�
	@Autowired
	   private JavaMailSenderImpl mailSender;
	   
	   @RequestMapping("/sendmail")
	   public String sendmail() {
	      final MimeMessagePreparator pp = new MimeMessagePreparator() {
			
	         
	         @Override
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
	            helper.setFrom("todaylesson@naver.com");
	            helper.setTo("todaylesson@naver.com");
	            helper.setSubject("�ȳ�");
	            helper.setText("<b>������~~~~~~~~~~~~~~</b>"
	                  + "<br>"
	                  + "<img src="+"https://ssl.pstatic.net/tveta/libs/1260/1260649/19aabf7c9a09e0d9ed84_20200211140438611.jpg"+">", true);
	         }
	      };
	      
	      mailSender.send(pp);
	      return "sendmail";
	   }
*/

	
	
}
