package com.jqzhang.easylistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 *
 * 单一布局
 *
 */
public class SingleTypeActivity extends AppCompatActivity {

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_type);
		listView = (ListView) findViewById(R.id.listView);

		listView.setAdapter(new SingleTypeAdapter());
	}

	class SingleTypeAdapter extends EasyBaseAdapter {

		List<Result> list = new ArrayList<>();

		@Override
		protected List getList() {

			if (list.size() > 0) {
				return list;
			}
			for (int i = 0; i < 60; i++) {
				SingleTypeActivity.Result result = new SingleTypeActivity.Result();

				list.add(result);
			}
			return list;
		}

		@Override
		protected EasyBaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_1, null);
			return new SingleTypeViewHolder(view);
		}

	}

	class SingleTypeViewHolder extends EasyBaseAdapter.ViewHolder<Result>  {

		@Bind(R.id.textView)
		TextView textView;

		public SingleTypeViewHolder(View view) {
			super(view);
		}


		@Override
		protected void updateView(View convertView, Result data, int position) {
			textView.setText(data.txt + position + "  " + (null == convertView));
		}
	}

	public static class Result {
		public String txt = "刺激 ";
	}
}
