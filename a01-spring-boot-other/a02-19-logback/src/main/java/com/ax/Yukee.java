package com.ax;

import lombok.CustomLog;

class Yukee {
	private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Yukee.class);

	public static org.slf4j.Logger getLogger(String name) {
		return log;
	}
}


