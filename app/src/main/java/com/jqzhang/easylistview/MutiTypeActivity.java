package com.jqzhang.easylistview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;

/**
 *
 * 多布局
 *
 */
public class MutiTypeActivity extends AppCompatActivity {

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_muti_type);

		listView = (ListView) findViewById(R.id.listView);

		listView.setAdapter(new MutiTypeAdapter());
	}

	class MutiTypeAdapter<Result> extends EasyBaseAdapter {

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		@Override
		public int getItemViewType(int position) {
			if (0 == position % 2) {
				return 0;
			} else {
				return 1;
			}
		}

		List<MutiTypeActivity.Result> list = new ArrayList<>();

		@Override
		protected List<MutiTypeActivity.Result> getList() {

			if (list.size() > 0) {
				return list;
			}
			for (int i = 0; i < 60; i++) {
				MutiTypeActivity.Result result = new MutiTypeActivity.Result();

				list.add(result);
			}
			return list;
		}

		@Override
		protected EasyBaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
			if (0 == getItemViewType(position)) {
				View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_1, null);
				return new MutiTypeViewHolder1(view);
			} else {
				View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_2, null);
				return new MutiTypeViewHolder2(view);
			}
		}

	}

	class MutiTypeViewHolder1 extends EasyBaseAdapter.ViewHolder<Result> {

		@Bind(R.id.textView)
		TextView textView;

		public MutiTypeViewHolder1(View view) {
			super(view);
		}


		@Override
		protected void updateView(View convertView, Result data, int position) {
			textView.setText("刺激 + " + position + "   " + (null == convertView));
		}
	}

	class MutiTypeViewHolder2 extends EasyBaseAdapter.ViewHolder<Result> {

		@Bind(R.id.imageView)
		ImageView imageView;

		public MutiTypeViewHolder2(View view) {
			super(view);
		}


		@Override
		protected void updateView(View convertView, Result data, int position) {
			imageView.setImageResource(R.mipmap.rr);
		}
	}

	class Result {

	}
}
