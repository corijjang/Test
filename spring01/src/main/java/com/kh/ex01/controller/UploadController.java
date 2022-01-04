package com.kh.ex01.controller;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ex01.util.MyFileUploadUtil;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	private static final String UPLOAD_PATH = "D:/upload";

	@RequestMapping(value="/uploadAjax", method=RequestMethod.POST, 
			produces = "application/text;charset=utf-8")
	@ResponseBody
	public String uploadAjax(MultipartFile file) throws IOException {
		System.out.println("UploadController, uploadAjax, file: " + file);
		String originalName = file.getOriginalFilename();
		System.out.println("UploadController, uploadAjax, originalName: " 
				+ originalName);
		String filePath = MyFileUploadUtil.uploadFile(
				UPLOAD_PATH, originalName, file.getBytes());
		boolean isImage = MyFileUploadUtil.isImage(originalName);
		if (isImage) {
			boolean result = MyFileUploadUtil.makeThumbnail(filePath);
			if (!result) {
				return "fail";
			}
		}
		
		return filePath.substring(UPLOAD_PATH.length());
		
	}
	
	@RequestMapping(value="/displayImage", method=RequestMethod.GET)
	@ResponseBody
	public byte[] displayImage(String fileName) throws Exception {
		// /2021/12/27/96e769f8-281c-4137-aa91-debc0ea615e7_3.png
		// /2021/12/27/sm_96e769f8-281c-4137-aa91-debc0ea615e7_3.png
		System.out.println("UploadController, displayFile, fileName: " + fileName);
		int slashIndex = fileName.lastIndexOf("/");
		String front = fileName.substring(0, slashIndex + 1);
		String rear = fileName.substring(slashIndex + 1);
		FileInputStream fis = new FileInputStream(
				UPLOAD_PATH + front + "sm_" + rear);
		// org.apache.commons.io.IOUtils
		byte[] bytes = IOUtils.toByteArray(fis);
		return bytes;
	}
	
	@RequestMapping(value="/deleteFile", method=RequestMethod.GET)
	@ResponseBody
	public String deleteFile(String fileName) {
		System.out.println("UploadController, deleteFile, fileName:" + fileName);
		boolean result = MyFileUploadUtil.deleteFile(UPLOAD_PATH + fileName);
		if (result == true) {
			return "success";
		}
		return "fail";
	}
}
