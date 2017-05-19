package com.delayTest;

import java.util.concurrent.ExecutorService;

public class EndExam extends Student {
	private ExecutorService exec;

	public EndExam(int submitTime, ExecutorService exec) {
		super(null, submitTime);
		this.exec = exec;
	}

	@Override
	public void run() {
		exec.shutdownNow();
	}
}
