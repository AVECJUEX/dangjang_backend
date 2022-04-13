package com.semi.dangjang.like;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semi.dangjang.freeboard.domain.FreeBoardDto;
import com.semi.dangjang.freeboard.service.FreeBoardService;

@CrossOrigin("*") //모든 ?��책을 ?��?��?��?��?�� ?���?. �??��?�� 9090�? 리액?��?�� 3000?�� ?��결하?���? ?���? 출처�? ?��?��?�� ?���??�� 주고받을 ?�� ?��?��.
				  //cross origin?�� ?��?�� 리액?��?�� �??���? ?��?��?��켜�??��.
@RestController	//컨트롤러?��?�� ?���?
public class LikeController {
	
	//마찬�?�?�? application.properties ?�� domain=http://127.0.0.1:9090?�� ?��?��?��?��.
	@Value("${domain}")			
	String domain;
	
	@Resource(name="likeService")
	LikeService likeService;
	
	@Resource(name="freeboardService")
	FreeBoardService freeboardService;
	
    @RequestMapping("/like/insert")
    Map<String, Object> insert(LikeDto dto, HttpServletRequest req)
	{		
		System.out.println("user--------------" + dto.getUser_seq());
		System.out.println("board--------------" + dto.getFree_seq());
		System.out.println("like--------------" + dto.getLike());
		
		likeService.insert(dto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
    	map.put("likedto", likeService.getView(dto));
    	map.put("like_cnt", freeboardService.getView(Long.parseLong(dto.getFree_seq())).getLike_cnt());

		return map;
	}
    
    @RequestMapping("/like/delete")
	Map<String, Object> delete(LikeDto dto, HttpServletRequest req)
	{	
    	System.out.println("user--------------" + dto.getUser_seq());
		System.out.println("board--------------" + dto.getFree_seq());
		
		likeService.delete(dto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
    	map.put("likedto", likeService.getView(dto));
    	map.put("like_cnt", freeboardService.getView(Long.parseLong(dto.getFree_seq())).getLike_cnt());

		return map;
	}
    
    @RequestMapping("/like/view")
    Map<String, Object> getView(LikeDto dto)
	{		
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("likedto", likeService.getView(dto));
    	map.put("like_cnt", freeboardService.getView(Long.parseLong(dto.getFree_seq())).getLike_cnt());
    	map.put("result", "success");
    	
		return map;
	}
}
