package Packaging;

import java.io.Serializable;

public class StuInfo implements Serializable{
	
	String Stuid;
	public String getStuid() {
		return Stuid;
	}
	public void setStuid(String stuid) {
		Stuid = stuid;
	}
	public String getStuname() {
		return Stuname;
	}
	public void setStuname(String stuname) {
		Stuname = stuname;
	}
	public String getUnipassword() {
		return Unipassword;
	}
	public void setUnipassword(String unipassword) {
		Unipassword = unipassword;
	}
	public String getJwcpassword() {
		return jwcpassword;
	}
	public void setJwcpassword(String jwcpassword) {
		this.jwcpassword = jwcpassword;
	}
	public String getJnupasssword() {
		return jnupasssword;
	}
	public void setJnupasssword(String jnupasssword) {
		this.jnupasssword = jnupasssword;
	}
	public String getDormpassword() {
		return dormpassword;
	}
	public void setDormpassword(String dormpassword) {
		this.dormpassword = dormpassword;
	}
	public String getDormnum() {
		return dormnum;
	}
	public void setDormnum(String dormnum) {
		this.dormnum = dormnum;
	}
	public String getCardnum() {
		return cardnum;
	}
	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}
	public String getForumaccount() {
		return forumaccount;
	}
	public void setForumaccount(String forumaccount) {
		this.forumaccount = forumaccount;
	}
	public String getFornumpassword() {
		return fornumpassword;
	}
	public void setFornumpassword(String fornumpassword) {
		this.fornumpassword = fornumpassword;
	}
	String Stuname;
	String Unipassword;
	String jwcpassword;
	String jnupasssword;
	String dormpassword;
	String dormnum;
	String cardnum;
	String forumaccount;
	String fornumpassword;
	
	//判断个人信息是否填齐的bool量
		Boolean info_complete=false;
		public Boolean getInfo_complete() {
			return info_complete;
		}
		public void setInfo_complete(Boolean info_complete) {
			this.info_complete = info_complete;
		}
	
}
