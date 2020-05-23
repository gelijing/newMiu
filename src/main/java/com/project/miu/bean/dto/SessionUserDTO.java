package com.project.miu.bean.dto;

/**
 * 公共平台用户信息类
 */
public class SessionUserDTO {

	private String memberId;

	private String nickName;

	private String loginName;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}
