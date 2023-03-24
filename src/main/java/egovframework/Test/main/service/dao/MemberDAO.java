package egovframework.Test.main.service.dao;

import egovframework.Test.main.service.TestMemberVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("memberDAO")
public interface MemberDAO {
	// 로그인
	public TestMemberVO login(TestMemberVO vo);
	// 회원가입
	public int signUp(TestMemberVO vo);
	// 회원가입에서 아이디 중복체크
	public int idcheck(String id);
	// 로그아웃
	public TestMemberVO logout(TestMemberVO vo);
	// ip 체크
	public String admin_ipCheck(TestMemberVO vo);
}