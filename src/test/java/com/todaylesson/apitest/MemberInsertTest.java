package com.todaylesson.apitest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.todaylesson.service.MemberService;
import com.todaylesson.DTO.Member_AuthDTO;
import com.todaylesson.DTO.MemberDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml"
	                ,"file:src\\main\\resources\\spring-security.xml"
})
public class MemberInsertTest {

// ����� �����Ҷ� ��ȣȭ�� ��й�ȣ�� ����. 
// ������ ������ ���ÿ� �ο��Ҷ� ( �� : admin�� user�� ���ÿ� �ٶ�) ����Ŭ�� insert all �������� �̿�������
// mysql���� insert all�� ���⶧���� values("a1","ROLE_ADMIN"), ("a1", "ROLE_USER"), ..... �̷� ���°� �Ǿ����.
// �������� mapper.xml���� Ȯ�� ����
		   
		  @Resource(name="memberService")
		  private MemberService service;
		  @Test
		  public void t1()
		  {
/*			  MemberVO vo=new MemberVO(); 
			  vo.setUserid("a1");
			  vo.setUserpwd("a1");
			  vo.setUsername("hong");
			  vo.setEnabled(true);
			  ArrayList<AuthVo> arr=new ArrayList<AuthVo>();
			  arr.add(new AuthVo("a1","ROLE_ADMIN"));
			  arr.add(new AuthVo("a1", "ROLE_USER"));
			  vo.setAuthList(arr);
			  assertEquals(2, service.insert(vo));*/
			  
			  // a1�� ������ admin�� user. auth�� 2���̹Ƿ� service.insert(vo) == 2 �� �Ǹ� true.
			  
			  MemberDTO dto=new MemberDTO(); 
			  dto.setMember_no(1);
			  dto.setMember_id("a2");
			  dto.setMember_pwd("a2");
			  dto.setMember_name("kim");
			  dto.setMember_birth("1994-09-25");
			  dto.setMember_phone("010-9988-2728");
			  dto.setMember_addr("��õ");
			  dto.setMember_zipcode(12345);
			  dto.setMember_email("k_zoey94@naver.com");
			  dto.setMember_nick("�չ㻧����");
			  dto.setMember_img("����");
			  ArrayList<Member_AuthDTO> arr=new ArrayList<>();
			  arr.add(new Member_AuthDTO("ROLE_ADMIN","a2"));
			  dto.setAuthList(arr);
			  assertEquals(1, service.insert(dto));
			  
			  //a2�� ������ user �ϳ��� �����Ƿ� 1.
		  }
		
		

	}
