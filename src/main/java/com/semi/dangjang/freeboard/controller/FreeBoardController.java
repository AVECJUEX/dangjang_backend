package com.semi.dangjang.freeboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.semi.dangjang.freeboard.domain.FreeBoardDto;
import com.semi.dangjang.freeboard.service.FreeBoardService;
import com.semi.dangjang.common.FileDownload;
import com.semi.dangjang.common.FileUploadUtil;



@CrossOrigin("*") //http:127.0.0.1 Ȥ�� localhost
@RestController	//jsp�� ȣ������ �ʰ� json���·� �����͸� ������.
public class FreeBoardController {
	
	@Value("${fileUploadPath}")	//src/main/resources/application.properties �� ���� ���ε� �Ѵ�.
	String fileUploadPath;
	
	@Value("${domain}")
	String domain;
	
	@Resource(name="freeboardService")
	FreeBoardService freeboardService;
	
	
	@RequestMapping("/freeboard/list/{pg}")	//freeboard/list/1
	HashMap<String, Object> getList(@PathVariable("pg")int pg, FreeBoardDto dto)
	{
		//System.out.println("curpage  " + pg);
		dto.setStart((pg-1)*dto.getPageSize());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("totalCnt", freeboardService.getTotalCnt(dto));
		map.put("list",  freeboardService.getList(dto));
		
		return map;
	}
	
	
	@RequestMapping("/freeboard/view/{free_seq}")
	FreeBoardDto getView(@PathVariable("free_seq")long free_seq)
	{		
		return	freeboardService.getView(free_seq);
	}
	
	// Map -> HashMap�� �߻�Ŭ����  -- aixos �� json ���� �����µ� json �������� 
	//@RequestBody  �� �־�� �Ѵ� 
	@RequestMapping("/freeboard/insert")
	Map<String, String> insert(MultipartFile file ,  FreeBoardDto dto, HttpServletRequest req)
	{		
		System.out.println(dto.getTitle());
		System.out.println(dto.getUser_id());
		System.out.println(dto.getContent());
		
		//fileupload/image�� �����.
		String uploadDir = fileUploadPath+ "/image" ;	//������ ���ε� �� ���
		
		//http://localhost:9090/fileupload/image/1582531436.jpeg
		if(file!=null)
		{
			try {
				//������ �ָ� ���ε��ϸ鼭 ���ο� ���ϸ��� ��ȯ�Ѵ�. ���ϸ��� �ߺ��� �� �ֱ� �����̴�.
				String filename=FileUploadUtil.upload(uploadDir, file);
				//dto.setFilename(filename);
				//dto.setImage(domain +"/"+ uploadDir + "/"+ filename);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		freeboardService.insert(dto);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "success");
		return map;
	}
	
//	
//    @GetMapping("/download/image/{file}")
//    public void download(@PathVariable("file")String file, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
//            throws ServletException, IOException {
//        
//	   FileDownload.download(fileUploadPath+"/image",  file, httpServletResponse);
//        
//    }

    @RequestMapping("/freeboard/delete/{free_seq}")
	Map<String, String> delete(@PathVariable("free_seq")long free_seq, HttpServletRequest req)
	{		
		FreeBoardDto dto=new FreeBoardDto();
		dto.setFree_seq(free_seq);
		freeboardService.delete(dto);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "success");
		return map;
	}
	
    
    @RequestMapping("/freeboard/update")
   	Map<String, String> update(MultipartFile file ,  FreeBoardDto dto, HttpServletRequest req)
   	{		
   		System.out.println(dto.getTitle());
   		System.out.println(dto.getUser_id());
   		System.out.println(dto.getContent());

   		
   		
   		String uploadDir = fileUploadPath+ "/image" ;
   		
   		//http://localhost:9090/user-photos/image/1582531436.jpeg
   		if(file!=null)
   		{
   			try {
   				String filename=FileUploadUtil.upload(uploadDir, file);
   		//		dto.setFilename(filename);
   		//		dto.setImage_url(domain +"/"+ uploadDir + "/"+ filename);
   		//		
   			} catch (IOException e) {
   				e.printStackTrace();
   			}
   		}
   		
   		//System.out.println("**********************************    "  +  dto.getId());
   		
   		freeboardService.update(dto);
   		Map<String, String> map = new HashMap<String, String>();
   		map.put("result", "success");
   		return map;
   	}
    
    
    
}






