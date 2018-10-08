package com.huawei.devcloud.monitor.utils;

import java.text.Normalizer;

public abstract class BaseException extends Exception {
	private static final long serialVersionUID = 67960164130637149L;
	private String errorCode;
	  private String errorMessage;
	  public BaseException() {
	    super();
	  }

	  public BaseException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  public BaseException(Throwable cause) {
	    super(cause);
	  }

	  public BaseException(String errorCode, String message) {
	    this.errorCode = Normalizer.normalize(errorCode, Normalizer.Form.NFC);
	    this.errorMessage = Normalizer.normalize(message, Normalizer.Form.NFC);;
	  }

	  public BaseException(String errorCode) {
	    super();
	    this.errorCode = Normalizer.normalize(errorCode, Normalizer.Form.NFC);
	  }

	  public String getErrorCode() {
	    return this.errorCode;
	  }

	  public void setErrorCode(String errorCode) {
	    this.errorCode = Normalizer.normalize(errorCode, Normalizer.Form.NFC);
	  }

	public String getErrorMessage() {
		return errorMessage;
	}
}
