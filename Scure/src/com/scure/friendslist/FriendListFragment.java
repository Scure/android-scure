package com.scure.friendslist;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FriendListFragment extends ListFragment {
	private ArrayList<Friend> mFriends;
	private static final String TAG = "FriendListFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.friends_name);
		mFriends = FriendsInfo.get(getActivity()).getFriends();

		FriendAdapter adapter = new FriendAdapter(mFriends);
		setListAdapter(adapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		((FriendAdapter) getListAdapter()).notifyDataSetChanged();
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Friend f = ((FriendAdapter) getListAdapter()).getItem(position);

		// Star FriendActivity
		Intent i = new Intent(getActivity(), FriendActivity.class);
		i.putExtra(FriendFragment.EXTRA_FRIEND_ID, f.getId());
		startActivity(i);
	}

	private class FriendAdapter extends ArrayAdapter<Friend> {
		public FriendAdapter(ArrayList<Friend> friends) {
			super(getActivity(), 0, friends);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// If we weren't given a view, inflate one
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.list_friends, null);
			}

			// Configure the view for this friend
			Friend f = getItem(position);

			TextView nameTextView = (TextView) convertView
					.findViewById(R.id.friend_list_item_titleTextView);
			nameTextView.setText(f.getName());

			return convertView;

		}
	}
}
