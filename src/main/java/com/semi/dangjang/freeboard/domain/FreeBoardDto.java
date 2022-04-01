package com.semi.dangjang.freeboard.domain;

import com.semi.dangjang.common.BaseDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access=AccessLevel.PUBLIC)  //�⺻�����ڸ� ������ش� 
public class FreeBoardDto extends BaseDto{
	    
	private String free_seq="";
	private String user_id="";
	private String title="";
	private String content="";
	private String image="";
	private String wdate="";
	private String like_cnt="";
	private String hit="";
	private String user_seq="";
	private String filename="";
	private String image_url="";
	
	@Builder
	public FreeBoardDto(String free_seq, String user_id, String title, String content, String image, String wdate,
			String like_cnt, String hit, String user_seq) {
		super();
		this.free_seq = free_seq;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.image = image;
		this.wdate = wdate;
		this.like_cnt = like_cnt;
		this.hit = hit;
		this.user_seq = user_seq;
		this.filename = filename;
		this.image_url = image_url;
	}
	
	
	
}
