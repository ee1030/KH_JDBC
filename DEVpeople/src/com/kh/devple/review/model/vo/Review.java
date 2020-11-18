package com.kh.devple.review.model.vo;

import java.util.Date;

/**  진선
 * @author user1
 *
 */
public class Review {

   private int revwNo; //후기 번호
   private String title; //후기 제목
   private String content; //후기 내용
   private Date createAt; //작성일
   private int devNo; //회원번호
   
   public Review() {}

   public Review(int revwNo, String title, String content, Date createAt, int devNo) {
      super();
      this.revwNo = revwNo;
      this.title = title;
      this.content = content;
      this.createAt = createAt;
      this.devNo = devNo;
   }

   public int getRevwNo() {
      return revwNo;
   }

   public void setRevwNo(int revwNo) {
      this.revwNo = revwNo;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public Date getCreateAt() {
      return createAt;
   }

   public void setCreateAt(Date createAt) {
      this.createAt = createAt;
   }

   public int getDevNo() {
      return devNo;
   }

   public void setDevNo(int devNo) {
      this.devNo = devNo;
   }

   @Override
   public String toString() {
      return "Review [revwNo=" + revwNo + ", title=" + title + ", content=" + content + ", createAt=" + createAt
            + ", devNo=" + devNo + "]";
   }

}