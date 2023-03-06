package egovframework.common.util;

import java.io.File;
import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;

public class StringUtils {
	// 비번 sha256 암호화 
	public static String testSHA256(String txt) throws Exception{
	    StringBuffer sbuf = new StringBuffer();
	     
	    MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
	    mDigest.update(txt.getBytes());
	     
	    byte[] msgStr = mDigest.digest() ;
	     
	    for(int i=0; i < msgStr.length; i++){
	        byte tmpStrByte = msgStr[i];
	        String tmpEncTxt = Integer.toString((tmpStrByte & 0xff) + 0x100, 16).substring(1);
	         
	        sbuf.append(tmpEncTxt) ;
	    }
	     
	    return sbuf.toString();
	}
	
	// 비번 MD5 암호화
	/**
	 * @param s
	 * @return 암호화된 String
	 * "MD5:"+Utility.MD5("!@dusthvmxm!@") ->8f1cdcfa9e7c5158b268c5b222006f95
	 */
	public static String MD5(String s){
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch(Exception e){
			e.printStackTrace();
		}
		
		String eip;
		byte[] bip = md5.digest(s.getBytes());
		String temp = "";
		for(int i=0; i < bip.length; i++){
			eip = "" + Integer.toHexString((int)bip[i] & 0x000000ff);
			if (eip.length() < 2) eip = "0" + eip;
			temp = temp + eip;
		}
		return temp;
	}
	/**
	 * getRootPath : 프로젝트 디렉토리 path
	 * @return
	 */
	public static String getRootPath() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		File file = new File(loader.getResource("").getFile());
		String path = file.getParent();
		path = path.replace("WEB-INF",  "");
		path = path.replace("%20", " ");
		file = null;
		
		return path;
	}

	
	// ip 찾기 
	public static String IPetRemoteAddr(HttpServletRequest request) {
        String ip = null;
        ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-Real-IP"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-RealIP"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        }
        
		return ip;
	}
	


	
}