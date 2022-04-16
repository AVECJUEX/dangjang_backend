package com.semi.dangjang.freeboard.repository;

import java.util.List;

import com.semi.dangjang.freeboard.domain.FreeBoardDto;





public interface FreeBoardDao {
	List<FreeBoardDto> getList(FreeBoardDto dto);
	int getTotalCnt(FreeBoardDto dto);
	FreeBoardDto getView(FreeBoardDto dto);
	void insert(FreeBoardDto dto);
	void updateHit(int id);//��ȸ�� ����
	void update(FreeBoardDto dto);
	void delete(FreeBoardDto dto);
	
}
