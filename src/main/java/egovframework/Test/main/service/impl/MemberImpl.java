package egovframework.Test.main.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.Test.main.service.TestMemberVO;
import egovframework.Test.main.service.dao.MemberDAO;

@Service("memberService")
public class MemberImpl {
	
	@Resource(name="memberDAO")
	private MemberDAO memberDAO;
	
	public TestMemberVO login(TestMemberVO vo) {
		return memberDAO.login(vo);
	}

	public int signUp(TestMemberVO vo) {
		return memberDAO.signUp(vo);
	}

	public int idcheck(String id) {
		return memberDAO.idcheck(id);
	}

	public TestMemberVO logout(TestMemberVO vo) {
		return memberDAO.logout(vo);
	}

}
