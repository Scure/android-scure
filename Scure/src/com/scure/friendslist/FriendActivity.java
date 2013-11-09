package com.scure.friendslist;

import java.util.UUID;

import android.support.v4.app.Fragment;

public class FriendActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		UUID friendId = (UUID)getIntent()
				.getSerializableExtra(FriendFragment.EXTRA_FRIEND_ID);
		
		return FriendFragment.newInstance(friendId);
	}

}
