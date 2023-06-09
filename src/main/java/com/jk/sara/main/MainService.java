package com.jk.sara.main;

import com.jk.sara.common.CommonException;
import com.jk.sara.common.CommonService;
import com.jk.sara.common.CommonUtil;
import com.jk.sara.common.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class MainService extends CommonService {

	@Autowired
	private MainDAO mainDAO;

	public MainService() {
		super(MainService.class);
	}

	private static final int GET_BUCKET_ITEMS_SEARCH_COUNT = 30;		// 버킷리스트 조회건수
	private static final int GET_STORY_ITEMS_SEARCH_COUNT = 30;			// 스토리리스트 조회건수

	/* 로그인 */
	public String login(Map<String, Object> paramMap) throws Exception {
		String[] reqKeys = {"userId", "userPwd"};		// 필수키
		super.checkVal(paramMap, reqKeys);					// 벨리데이션 체크

		paramMap.put("userPwd", Encryptor.sha512( (String)paramMap.get("userPwd") ) );

		String userSeq = mainDAO.login(paramMap);

		if("0".equals(userSeq)) {	// 해당 사용자번호가 존재하지 않는 경우
			throw new CommonException( "0001", "아이디 또는 비밀번호를 확인하여 주세요" );
		}
		
		return userSeq;
	}

	public Map<String, Object> getBucketList(Map<String, Object> paramMap) throws Exception {
		String[] reqKeys = {"stNo"};		// 필수키
		super.checkVal(paramMap, reqKeys);

		Map<String, Object> rsMap = new HashMap<String, Object>();
		String moreYn = "";		// 더보기여부
		int nextStNo = 0;

		paramMap.put("searchCnt", GET_BUCKET_ITEMS_SEARCH_COUNT);

		// 버킷 목록 조회
		List<Map<String, Object>> bucketList = mainDAO.selectBucketList(paramMap);

		if( !CommonUtil.isEmptyList(bucketList) ) {
			moreYn = (String) bucketList.get(0).get("moreYn");		// 더보기여부 SET
			nextStNo = (Integer) paramMap.get("stNo") + GET_BUCKET_ITEMS_SEARCH_COUNT;
		}

		rsMap.put( "rsList", bucketList );
		rsMap.put( "moreYn", moreYn );
		rsMap.put( "nextStNo", nextStNo );

		return rsMap;
	}

}
