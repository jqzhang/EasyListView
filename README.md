# EasyListView
每次用到 ListView 时候都需要写一个Adapter；令人恶心的是需要在getView() 方法中添加一堆代码，无论是使用还是写代码都非常不便。现在只需要三部就可以实现一个Adapter。
注意：项目中用到 butterknife

## 单一样式
1. 实现 Adapter
		class SingleTypeAdapter extends EasyBaseAdapter {

		List<Result> list = new ArrayList<>();

		@Override
		protected List getList() {

			if (list.size() > 0) {
				return list;
			}
			// 构造数据
			for (int i = 0; i < 60; i++) {
				SingleTypeActivity.Result result = new SingleTypeActivity.Result();

				list.add(result);
			}
			return list;
		}

		// 构造 Item ViewHolder
		@Override
		protected EasyBaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
			// 创建 ItemView
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_1, null);
			return new SingleTypeViewHolder(view);
		}

	}
	
2. 实现ViewHolder
	
	class SingleTypeViewHolder extends EasyBaseAdapter.ViewHolder<Result>  {

		@Bind(R.id.textView)
		TextView textView;

		public SingleTypeViewHolder(View view) {
			super(view);
		}


		@Override
		protected void updateView(View convertView, Result data, int position) {
			// 更新View 
			textView.setText(data.txt + position + "  " + (null == convertView));
		}
	}

3. 完成
	listView.setAdapter(new SingleTypeAdapter());
	
## 多样式
1. 实现Adapter
	
	class MutiTypeAdapter<Result> extends EasyBaseAdapter {
	
		// 一定要实现该方法
		@Override
		public int getViewTypeCount() {
			return 2;
		}

		// 一定要实现该方法
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
			// 构造数据
			for (int i = 0; i < 60; i++) {
				MutiTypeActivity.Result result = new MutiTypeActivity.Result();

				list.add(result);
			}
			return list;
		}

		// 构造 Item ViewHolder
		@Override
		protected EasyBaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
			// 根据 ViewType 创建 ItemView
			if (0 == getItemViewType(position)) {
				View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_1, null);
				return new MutiTypeViewHolder1(view);
			} else {
				View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_2, null);
				return new MutiTypeViewHolder2(view);
			}
		}

	}
	
2. 实现ViewHolder
	
	class MutiTypeViewHolder1 extends EasyBaseAdapter.ViewHolder<Result> {

		@Bind(R.id.textView)
		TextView textView;

		public MutiTypeViewHolder1(View view) {
			super(view);
		}


		@Override
		protected void updateView(View convertView, Result data, int position) {
			// 更新View 
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
			// 更新View 
			imageView.setImageResource(R.mipmap.rr);
		}
	}

3. 完成
	listView.setAdapter(new SingleTypeAdapter());
	
	注意：多样式ListView 的 Adapter 中一定要实现 getViewTypeCount() 和 getItemViewType()这两个方法。其余照搬就好。



