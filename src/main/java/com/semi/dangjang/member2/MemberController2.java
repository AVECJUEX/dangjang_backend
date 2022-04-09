package com.semi.dangjang.member2;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class MemberController2 {
	
	@Resource(name="memberService2")
	MemberService2 memberService;
	
	@RequestMapping("/member/view/{userid}")
	HashMap<String, Object> member_myinfo(@PathVariable("userid") String userid, HttpServletRequest request, HttpServletResponse response)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();

		if(userid==null) {
			map.put("result", "fail");
			return map;
		}
		
		MemberDto dto = new MemberDto();
		dto.setUserid(userid);
		
		MemberDto resultDto = memberService.getInfo(dto);
		
		map.put("result", "success");
		map.put("info",resultDto);
		return map;
	}
		
	@RequestMapping("/member/isDuplicate")
	public HashMap<String, String> member_isDuplicate(MemberDto dto)
	{
		System.out.println("userid : " + dto.getUserid());
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", memberService.isDuplicate(dto)+"");
		
		return map;
	}
	
	@RequestMapping(value="/member/logout")
	public HashMap<String, String>  member_logout(HttpServletRequest request)
	{
		// loginYN �÷� �߰��� �ڵ� �߰��Ұ�
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "success");
		return map;
	}
	
	
	@RequestMapping(value="/member/findid")
	public HashMap<String, String> member_findid_proc(MemberDto dto)
	{	
		MemberDto findDto = memberService.findId(dto);
		HashMap<String, String> map = new HashMap<String, String>();
		if (findDto==null)
			map.put("result", "fail");
		else
		{
			map.put("result", findDto.getUserid());
			map.put("userid", findDto.getUserid());
			map.put("nickname", findDto.getNick_name());
			map.put("email", findDto.getEmail());
			map.put("phone", findDto.getPhone());
			map.put("role", findDto.getRole());
		}
		return map;
	}
	
	
	@RequestMapping(value="/member/findpass")
	public HashMap<String, String> member_findpass_proc(MemberDto dto)
	{	
		// �н����� �����ϴ� �ڵ� �߰��Ұ�
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "fail");
		return map;
	}
	
	@RequestMapping(value="/member/update")
	public HashMap<String, String> member_update(MemberDto dto)
	{	
		memberService.update(dto);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "success");	
		return map;
	}
	

}
