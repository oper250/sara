package com.jk.sara.common;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonDAO {

	@Autowired
	protected SqlSession sqlSession;
}