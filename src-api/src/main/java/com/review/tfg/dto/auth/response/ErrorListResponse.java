package com.review.tfg.dto.auth.response;

import java.util.Date;
import java.util.List;

public class ErrorListResponse {
	private Date timestamp;
    private List<String> errores;

    public ErrorListResponse(Date timestamp, List<String> errores) {
        this.timestamp = timestamp;
        this.errores = errores;
    }

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<String> getErrores() {
		return errores;
	}

	public void setErrores(List<String> errores) {
		this.errores = errores;
	}
}
