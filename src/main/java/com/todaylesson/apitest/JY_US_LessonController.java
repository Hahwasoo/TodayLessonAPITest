package com.todaylesson.apitest;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.todaylesson.DTO.LessonDTO;
import com.todaylesson.utils.UploadFileUtils;

public class JY_US_LessonController {

	
	private String path = "temp";
	// ������ ����Ǵ� ����
	
	
	@RequestMapping("lesson_write")
	public String write() {
		return "jy_lessonwrite";
	}
	
	@RequestMapping("jusoPopup")
	public String jusoPopup() {
		return "jy_jusoPopup";
	}	
	
	
	
	// �ش��ϴ� ��ü�� �������� �� ������ �����Ƿ� ���ϴ� ��ü�� ���� �޾Ƽ� ����ϸ� ��
	// �׷��� httpsession�� ����
	@RequestMapping("insertresult")
	public String insertresult(HttpServletRequest request, LessonDTO dto, MultipartFile file) throws IOException, Exception {
		
		
		String imgUploadPath = path + File.separator + "imgUpload";
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;

		if(file != null) {
		 fileName =  UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath); 
		} else {
		 fileName = path + File.separator + "images" + File.separator + "none.png";
		}

		dto.setLesson_thumb(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		
		return "insertresult";
	}
	
	
	
	
	
	
}
