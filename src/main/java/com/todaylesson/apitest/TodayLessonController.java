package com.todaylesson.apitest;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.todaylesson.service.LoginService;
import com.todaylesson.service.MailSendService;

@Controller
public class TodayLessonController {

	@Resource(name="loginService")
	private LoginService loginService;
	
	@Autowired
	private MailSendService mailSender;
	
	
	 @RequestMapping("/admin")
		public String admin()
		{
			return "/todaylesson_sec/todaylesson_sec_admin";
		}
	 
	 @RequestMapping("/senior")
	 public String senior() {
		 return "/todaylesson_sec/todaylesson_sec_senior"; 
	 }
		 
	 @RequestMapping("/member")
	 public String member()
	 {
		 return "/todaylesson_sec/todaylesson_sec_member";
	 }
		 
		 @RequestMapping("/alluser")
		 public String all()
		 {
			 //return "/todaylesson_sec/todaylesson_sec_all";
			 return "/TodayLesson_UserPage/hs_us_main";
		 }
			 
		 @RequestMapping("/error")
		 public String error()
		 {
			  return "/todaylesson_sec__error";
		 }
		 		 
		 @RequestMapping("/customlogin")
		 public String login(String error, String logout, Model model)
		 {
			 if (error !=null)
				 model.addAttribute("error", "Please check your ID or Password");
			 	
			 if(logout !=null)
				 model.addAttribute("logout","logout");
			 
			 
			 return "/todaylesson_sec_login";
		 }
		 
		 @RequestMapping("/join")
		 public String join(HttpServletRequest request, Model model) throws Exception {

		        String api_key = "NCSRC0XSPD85BDRL"; //������ ���� api key�� �߰�
		        String api_secret = "2LVQYEMQFBIBEG8WVXKQOWQ6KPDDVJQ9";  //������ ���� api secret�� �߰�

		        com.todaylesson.Sms.Coolsms coolsms = new com.todaylesson.Sms.Coolsms(api_key, api_secret);
		        //�� �κ��� Ȩ���������� ���� �ڹ������� �߰��Ѵ��� �� Ŭ������ import�ؾ� �� �� �ִ� Ŭ�����̴�.
		        

		        HashMap<String, String> set = new HashMap<String, String>();
		        set.put("from", "01026063254"); // ���Ź�ȣ

		        set.put("to", (String)request.getParameter("to")); // �߽Ź�ȣ, jsp���� ������ �߽Ź�ȣ�� �޾� map�� �����Ѵ�.
		        set.put("text", (String)request.getParameter("text")); // ���ڳ���, jsp���� ������ ���ڳ����� �޾� map�� �����Ѵ�.
		        set.put("type", "sms"); // ���� Ÿ��
		        String text=(String)request.getParameter("text");
		        model.addAttribute("to",(String)request.getParameter("to"));
		        model.addAttribute("auth_num",text);
		       
		        System.out.println(set);

		        JSONObject result = coolsms.send(set); // ������&���۰���ޱ�

		        if ((boolean)result.get("status") == true) {

		          // �޽��� ������ ���� �� ���۰�� ���
		          System.out.println("����");
		          System.out.println(result.get("group_id")); // �׷���̵�
		          System.out.println(result.get("result_code")); // ����ڵ�
		          System.out.println(result.get("result_message")); // ��� �޽���
		          System.out.println(result.get("success_count")); // �޽������̵�
		          System.out.println(result.get("�����޼�����: error_count")); // ������ ������ ������ �޽��� ��
		        } else {

		          // �޽��� ������ ����
		          System.out.println("����");
		          System.out.println(result.get("code")); // REST API �����ڵ�
		          System.out.println(result.get("message")); // �����޽���
		        }
 return "/todaylesson_joinform";
		 }
		 
		 @RequestMapping("/findId")
		 public String findId()
		 {
			 return "yi_find_id";
		 }
		 
		 /*id�ߺ� üũ*/
			@ResponseBody 
			 @RequestMapping(value="/idCheck", method= RequestMethod.POST)
			 public int idCheck(@RequestParam("id") String member_id,Model model)
			 {
				  System.out.println(member_id);
				  int row = loginService.idCheck(member_id);
				  model.addAttribute("data",row);
				  return row;
			 }
		 /*id ã��*/
			@ResponseBody
			@RequestMapping(value = "/userSearch", method = RequestMethod.POST)
			public String userIdSearch(@RequestParam("inputName_1") String member_name, 
					@RequestParam("inputPhone_1") String member_phone) {
				HashMap<String,Object> map=new HashMap<>();
				System.out.println(member_name);
				System.out.println(member_phone);
				map.put("member_name", member_name);
				map.put("member_phone", member_phone);
				String result = loginService.get_searchId(map);
				
				System.out.println(result);

				return result;
			}
		
			
			
			
			/*pwd ã��*/
			@RequestMapping(value="/searchPassword",method=RequestMethod.GET)
			@ResponseBody
			public String passwordSearch(@RequestParam("inputId_2")String member_id,
					@RequestParam("inputEmail_2") String member_email
					,HttpServletRequest request) {
				mailSender.mailSendWithPassword(member_id,member_email,request);
				
				return "/userSearchPassword";
				                               
				
			}
			

		 
		 @RequestMapping("/ej_join")
		 public String joinej()
		 {
			 return "/ej_joinform";
		 }
		 
		 //jsp�� �־��༭ �ʿ����
		/* @RequestMapping("/logout")
		 public String logout()
		 {
			 return "/yi_logout";
		 }*/

}

