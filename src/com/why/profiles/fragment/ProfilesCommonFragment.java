package com.why.profiles.fragment;

import com.why.profiles.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfilesCommonFragment  extends Fragment{

	private View mView;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_schedule,container, false);
		return mView;
	}
}
