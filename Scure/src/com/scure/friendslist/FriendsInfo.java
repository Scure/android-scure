package com.scure.bluetooth;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class FriendsInfo {
	private ArrayList<Friend> mFriends;
	private final static String[] FriendName = { "Steve Wozniak", "Elon Musk",
			"Richard Feynman", "Bruce Wayne", "Tony Stark" };

	private static FriendsInfo sFriendInfo;
	private Context mAppContext;

	private FriendsInfo(Context appContext) {
		mAppContext = appContext;
		mFriends = new ArrayList<Friend>();
		for (int i = 0; i < FriendName.length; i++) {
			Friend f = new Friend();
			f.setName(FriendName[i]);

			mFriends.add(f);
		}
	}

	public static FriendsInfo get(Context c) {
		if (sFriendInfo == null) {
			sFriendInfo = new FriendsInfo(c.getApplicationContext());
		}
		return sFriendInfo;
	}

	public ArrayList<Friend> getFriends() {
		return mFriends;
	}

	public Friend getFriend(UUID id) {
		for (Friend f : mFriends) {
			if (f.getId().equals(id))
				return f;
		}
		return null;
	}
}
