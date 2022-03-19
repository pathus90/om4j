package io.github.pathus90.om4j.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

	SUCCESS(200), CREATED(201);

	private final int code;
}
