package com.todaylesson.apitest;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
		 public String join()
		 {
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

