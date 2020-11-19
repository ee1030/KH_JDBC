package com.kh.devple.member.model.vo;

/**
 * @author 박희도
 *
 */
public class Member {
   
   // vo
   private int memNo;
   private String memId;
   private String memPw;
   private String memNm;
   private String phone;
   private String email;
   private int career;
   private String spec;
   private char devYn;
   private char scsnFl;

   // 기본 생성자
   public Member() {}
   
   // 로그인 생성자
   public Member(int memNo, String memId, String memNm, String phone, String email, int career, String spec,
		char devYn) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memNm = memNm;
		this.phone = phone;
		this.email = email;
		this.career = career;
		this.spec = spec;
		this.devYn = devYn;
   }

   public Member(String memId, String memPw, String memNm, String phone, String email, int career, String spec,
			char devYn) {
		super();
		this.memId = memId;
		this.memPw = memPw;
		this.memNm = memNm;
		this.phone = phone;
		this.email = email;
		this.career = career;
		this.spec = spec;
		this.devYn = devYn;
   }

   // 매개변수 생성자
   public Member(int memNo, String memId, String memPw, String memNm, String phone, String email, int career,
         String spec, char devYn, char scsnFl) {
      super();
      this.memNo = memNo;
      this.memId = memId;
      this.memPw = memPw;
      this.memNm = memNm;
      this.phone = phone;
      this.email = email;
      this.career = career;
      this.spec = spec;
      this.devYn = devYn;
      this.scsnFl = scsnFl;
   }

   // getter / setter
   public int getMemNo() {
      return memNo;
   }

   public void setMemNo(int memNo) {
      this.memNo = memNo;
   }

   public String getMemId() {
      return memId;
   }

   public void setMemId(String memId) {
      this.memId = memId;
   }

   public String getMemPw() {
      return memPw;
   }

   public void setMemPw(String memPw) {
      this.memPw = memPw;
   }

   public String getMemNm() {
      return memNm;
   }

   public void setMemNm(String memNm) {
      this.memNm = memNm;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public int getCareer() {
      return career;
   }

   public void setCareer(int career) {
      this.career = career;
   }

   public String getSpec() {
      return spec;
   }

   public void setSpec(String spec) {
      this.spec = spec;
   }

   public char getDevYn() {
      return devYn;
   }

   public void setDevYn(char devYn) {
      this.devYn = devYn;
   }

   public char getScsnFl() {
      return scsnFl;
   }

   public void setScsnFl(char scsnFl) {
      this.scsnFl = scsnFl;
   }

   @Override
   public String toString() {
      return "Member [memNo=" + memNo + ", memId=" + memId + ", memPw=" + memPw + ", memNm=" + memNm + ", phone="
            + phone + ", email=" + email + ", career=" + career + ", spec=" + spec + ", devYn=" + devYn
            + ", scsnFl=" + scsnFl + "]";
   }
}