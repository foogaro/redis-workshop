package com.foogaro.data;

import lombok.Getter;
import lombok.Setter;

public class TheAlgorithm {

	@Getter @Setter
	private long defaultMaxValue = 100l;
	
	public long result(Long maxValue) {
		return (long)(Math.random() * (maxValue != null ? maxValue : defaultMaxValue) + 1);
	}
	
	public long result() {
		return this.result(null);
	}
}
