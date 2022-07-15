package com.treefactory.myapp;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {

		log.info("upload form");
	}

	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {

		String uploadFolder = "c:\\upload";
		
			for (MultipartFile multipartFile : uploadFile) {
				log.info("-------------------------------------------");

				log.info("Upload file name : " + multipartFile.getOriginalFilename());

				log.info("Upload file size : " + multipartFile.getSize());

				//c:\\upload+업로드 되는 파일이름
				File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
				
				try {
					//저장
					multipartFile.transferTo(saveFile);
					
				}//end try
				catch (Exception e) {
					
					log.error(e.getMessage());
				}//end catch
				
		}//end for

	}
	
	//ajax 파일업로드 처리
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		
		log.info("upload ajax.do");
	}
	
	//연 월 일 폴더 생성하기 위한 날짜 -> 2022-07-13 의 "-" 를 운영체제에 맞는 구분자로 바꿈
	private String getFolder() {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		log.info("날짜 : "+sdf);
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
		
	}
	
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxPost(MultipartFile[] uploadFile) {
		
		log.info("update ajax post.....");
		
		String uploadFolder = "c:\\upload";
		//업로드 경로에 날짜를 추가시킴
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload path" + uploadPath);
		//해당 년,월,일 마다 폴더 말들어짐
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		
		for(MultipartFile multipartFile : uploadFile) {
			
			log.info("-----------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload file size : " + multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			//IE 는 file path 가  추가적으로 필요(모든 파일 경로가 다나와서 ), \\인덱스 다음에서 끝까지(파일이름만) 잘라오기
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			log.info("only file name :" + uploadFileName);
			//랜덤 문자열 생성기
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_"+uploadFileName;
			
//			File saveFile = new File(uploadFolder, uploadFileName);
			//년,월,일 포함된 위치로 저장
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}//end catch
			
		}//end for
		
		
		
	}
	
	//파일이 이미지타입인지 검사
	private Boolean checkImageType(File file) {
		
		try {
			//probeContentType - Mime타입을 확인하지못하면 null을 반환한다(내용이 아니라 파일 확장자를 이용하면 판단함)
			//MIME 란 파일변환은 이야기한다
			String contentType = Files.probeContentType(file.toPath());
			//마입 타입 - 파일의 종류(image)/ 파일포맷(.gif or .jpg)
			return contentType.startsWith("image");
		}catch (Exception e) {
			
			e.printStackTrace();
		}

	return false;
	}
	
}
