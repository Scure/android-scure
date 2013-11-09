package com.scure.friendslist;

import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class FriendFragment extends Fragment {
	public static final String EXTRA_FRIEND_ID = "com.scure.bluetooth.friend_id";

	private Friend mFriend;
	private EditText mTitleField;
	private CheckBox mSolvedCheckBox;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID friendId = (UUID) getArguments().getSerializable(EXTRA_FRIEND_ID);

		mFriend = FriendsInfo.get(getActivity()).getFriend(friendId);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_friend, parent, false);

		mTitleField = (EditText) v.findViewById(R.id.friend_title);
		mTitleField.setText(mFriend.getName());
		mTitleField.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before,
					int count) {
				mFriend.setName(c.toString());
			}

			public void beforeTextChanged(CharSequence c, int start, int count,
					int after) {
				// This space is intentionally left blank
			}

			public void afterTextChanged(Editable c) {
				// This space is intentionally left blank
			}
		});

		mSolvedCheckBox = (CheckBox) v.findViewById(R.id.task_finished);
		mSolvedCheckBox.setChecked(mFriend.isFinished());
		mSolvedCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// Set the task's finished property
						mFriend.setFinished(isChecked);
					}
				});

		return v;
	}

	public static FriendFragment newInstance(UUID friendId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_FRIEND_ID, friendId);

		FriendFragment fragment = new FriendFragment();
		fragment.setArguments(args);

		return fragment;
	}
}
