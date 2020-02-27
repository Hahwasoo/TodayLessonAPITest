package com.todaylesson.service;

import java.util.Random;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.todaylesson.Mapper.MemberMapper;

@Service
public class MailSendService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MemberMapper mapper;//userDao
	
	// �̸��� ���� ����� �޼���
		private String init() {
			Random ran = new Random();
			StringBuffer sb = new StringBuffer();
			int num = 0;

			do {
				num = ran.nextInt(75) + 48;
				if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
					sb.append((char) num);
				} else {
					continue;
				}

			} while (sb.length() < size);
			if (lowerCheck) {
				return sb.toString().toLowerCase();
			}
			return sb.toString();
		}

		// ������ �̿��� Ű ����
		private boolean lowerCheck;
		private int size;

		public String getKey(boolean lowerCheck, int size) {
			this.lowerCheck = lowerCheck;
			this.size = size;
			return init();
		}

		public void mailSendWithPassword(String member_id, String member_email, HttpServletRequest request) {
			// TODO Auto-generated method stub
			String key = getKey(false, 6);
					
			MimeMessage mail = mailSender.createMimeMessage();
			String htmlStr = "<h2>�ȳ��ϼ��� '"+ member_id +"' ��</h2><br><br>" 
					+ "<p>��й�ȣ ã�⸦ ��û���ּż� �ӽ� ��й�ȣ�� �߱��ص�Ƚ��ϴ�.</p>"
					+ "<p>�ӽ÷� �߱� �帰 ��й�ȣ�� <h2 style='color : blue'>'" + key +"'</h2>�̸� �α��� �� �������������� ��й�ȣ�� �������ֽø� �˴ϴ�.</p><br>"
					+ "<h3><a href='http://54.180.123.73/MS/'>MS :p Ȩ������ ���� ^0^</a></h3><br><br>"
					+ "(Ȥ�� �߸� ���޵� �����̶�� �� �̸����� �����ϼŵ� �˴ϴ�)";
			try {
				mail.setSubject("[MS :p] �ӽ� ��й�ȣ�� �߱޵Ǿ����ϴ�", "utf-8");
				mail.setText(htmlStr, "utf-8", "html");
				mail.addRecipient(RecipientType.TO, new InternetAddress(member_email));
				mailSender.send(mail);
			} catch (MessagingException e) { 
				e.printStackTrace();
			}
			// ��й�ȣ ��ȣȭ���ִ� �޼���...?�װ� ����ִµ��Ѥ�
			/*key = UserSha256.encrypt(key);*/
			// ������ ���̽� ���� ��ȣ�� ������ �����Ų��.
			mapper.searchPassword(member_id, member_email, key);
	

		}


	
}
