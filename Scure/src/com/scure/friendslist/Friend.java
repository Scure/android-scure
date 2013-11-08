package com.scure.bluetooth;

import java.util.UUID;

public class Friend {
	private UUID mId;
	private String mName;
	private boolean mFinished;

	public Friend() {
		// Generate unique identifier
		mId = UUID.randomUUID();
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public UUID getId() {
		return mId;
	}
	
	// is friend task finished
	public boolean isFinished() {
		return mFinished;
	}

	// set task finished - true or false
	public void setFinished(boolean finished) {
		mFinished = finished;
	}
}
