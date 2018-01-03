package Cy.WeChatSpringboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ErrorInfo {
	private int errcode;
	private String errmsg;

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public int getErrcode() {
		return errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}
}
