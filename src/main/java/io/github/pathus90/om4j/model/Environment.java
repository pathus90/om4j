package io.github.pathus90.om4j.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Environment {
	DEV("dev"), GUINEA("gn"), CAMEROON("cm"), IVORY_COAST("ci");

	private final String value;

}
