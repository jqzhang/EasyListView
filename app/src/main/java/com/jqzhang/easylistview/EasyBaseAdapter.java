package com.jqzhang.easylistview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import butterknife.ButterKnife;

/**
 * EasyBaseAdapter
 *
 * @author jqZhang
 * @date 16/4/26-下午4:59
 * @desc Adapter 基类,所有ListView 的 Adapter 都需要继承此类
 */
public abstract class EasyBaseAdapter extends BaseAdapter {

	protected abstract List getList();

	protected ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
		return null;
	}

	@Override
	public int getViewTypeCount() {
		return super.getViewTypeCount();
	}

	public int getItemViewType(int position) {
		return super.getItemViewType(position);
	}

	@Override
	public int getCount() {
		return ((null == getList()) ? 0 : getList().size());
	}

	@Override
	public Object getItem(int position) {
		if (null == getList() || getList().size() <= position) {
			return null;
		}

		return getList().get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder vh;

		if (null == convertView) {
			vh = onCreateViewHolder(parent, position);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		if (null == vh) {
			return null;
		}

		vh.updateView(vh.getConvertView(), getItem(position), position);

		return vh.getConvertView();
	}

	public static abstract class ViewHolder<T> {

		private View convertView;

		protected abstract void updateView(View convertView, T data, int position);

		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
			this.convertView = view;
			setTag();
		}

		private void setTag() {
			this.convertView.setTag(this);
		}

		protected View getConvertView() {
			return convertView;
		}
	}

}
