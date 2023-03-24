package egovframework.Test.main.service;

import org.springframework.web.multipart.MultipartFile;

public class TestBoardServiceVO {
	private int paymentId, staffId, customerId, rentalId, rNum, rNum2;
	private String paymentDate, firstName, id, search;
	private int viewCnt;
	private int likeCnt;
	private int keyWord;
	private String idx;
	
	// 파일 업로드 
	private String fileName;
	private MultipartFile uploadFile;
	private String testTitle;
	private String testContent;
	
	
	public String getTestTitle() {
		return testTitle;
	}
	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}
	public String getTestContent() {
		return testContent;
	}
	public void setTestContent(String testContent) {
		this.testContent = testContent;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public int getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(int keyWord) {
		this.keyWord = keyWord;
	}
	public int getrNum2() {
		return rNum2;
	}
	public void setrNum2(int rNum2) {
		this.rNum2 = rNum2;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getRentalId() {
		return rentalId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	
	
	@Override
	public String toString() {
		return "BoardVO [paymentId=" + paymentId + ", staffId=" + staffId
				+ ", customerId=" + customerId + ", rentalId=" + rentalId
				+ ", rNum=" + rNum + ", paymentDate=" + paymentDate
				+ ", firstName=" + firstName + ", id=" + id + ", viewCnt="
				+ viewCnt + ", likeCnt=" + likeCnt + "]";
	}
	
	
	
	
}
