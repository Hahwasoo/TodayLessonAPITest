package com.todaylesson.apitest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TodayLessonController {

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
		 
		 /*@RequestMapping("/alluser")
		 public String all()
		 {
			 return "/todaylesson_sec/todaylesson_sec_all";
		 }*/
	 
	    @RequestMapping("/alluser")
		public String UserHome() {
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
		 
		 //jsp�� �־��༭ �ʿ����
		/* @RequestMapping("/logout")
		 public String logout()
		 {
			 return "/yi_logout";
		 }*/
	
}
