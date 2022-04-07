package io.github.pathus90.om4j.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MobileMoneyException extends RuntimeException {

	private final String message;
	private final int code;
	private final String description;

	public MobileMoneyException(String message) {
		super(message);
		this.message = message;
	}

	public MobileMoneyException(int code, String message, String description) {
		super(message);
		this.message = message;
		this.code = code;
		this.description = description;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
