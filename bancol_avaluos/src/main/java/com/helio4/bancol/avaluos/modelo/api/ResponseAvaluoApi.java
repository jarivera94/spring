package com.helio4.bancol.avaluos.modelo.api;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

public class ResponseAvaluoApi {

	private Long codeResponse;
	private String message;
	private String detail;

	public ResponseAvaluoApi() {
		// TODO Auto-generated constructor stub
	}

	public Long getCodeResponse() {
		return codeResponse;
	}

	public void setCodeResponse(Long codeResponse) {
		this.codeResponse = codeResponse;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
