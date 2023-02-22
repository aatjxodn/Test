package egovframework.Test.main.service;

public class TestCommentVO {
	private int seq;
	private int paymentId;
	private int rNum;
	private String comment;
	private String id;
	private int depth;
	private int refSeq;
	private int groupNum;
	private int deleteSeq;
	private int groupOrder;
	private int limit;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getRefSeq() {
		return refSeq;
	}
	public void setRefSeq(int refSeq) {
		this.refSeq = refSeq;
	}
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	public int getDeleteSeq() {
		return deleteSeq;
	}
	public void setDeleteSeq(int deleteSeq) {
		this.deleteSeq = deleteSeq;
	}
	public int getGroupOrder() {
		return groupOrder;
	}
	public void setGroupOrder(int groupOrder) {
		this.groupOrder = groupOrder;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	@Override
	public String toString() {
		return "CommentVO [seq=" + seq + ", paymentId=" + paymentId + ", rNum="
				+ rNum + ", comment=" + comment + ", id=" + id + ", depth="
				+ depth + ", refSeq=" + refSeq + ", groupNum=" + groupNum
				+ ", deleteSeq=" + deleteSeq + ", groupOrder=" + groupOrder
				+ ", limit=" + limit + "]";
	}
	
	
}
