package com.jk.sara.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@SuppressWarnings( "unchecked" )
public class CommonController {

	protected Logger log;

	protected CommonController(Class<?> c ) {
		log = LoggerFactory.getLogger( c );
	}

	protected boolean isLoggedIn( HttpServletRequest request ) {
		HttpSession session = request.getSession();
		long userSeqno = CommonUtil.getLong( session.getAttribute( "USER_SEQNO" ) );
		return !( userSeqno < 1L );
	}
	
	protected long getUserSeqno( HttpServletRequest request ) {
		HttpSession session = request.getSession();
		return CommonUtil.getLong( session.getAttribute( "USER_SEQNO" ) );
	}
	
	public JSONObject getErrorResponse( String errCode, String errMsg ) {
		JSONObject resItem = new JSONObject();
		resItem.put( "resCode", errCode);
		resItem.put( "resMsg", errMsg);
		
		return resItem;
	}

	/*  */
	public Map<String, Object> getResponse(Map<String, Object> rsMap) throws Exception {
		rsMap.put("rsCode", "0000");
		rsMap.put("rsMsg", "SUCCESS");

		return rsMap;
	}

}