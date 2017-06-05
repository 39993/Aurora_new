package com.aorora.app.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aorora.app.R;

/**
 * author : Administrator on 2017/5/27.
 * time : 2017/06/01
 * fileNmae : ${fileName}
 * desc : 会员中心  功能板块列表适配器
 */
public class MemberCentrAdapter extends BaseAdapter{

	private Context context;
	private Integer icon [];
	private String title [];
	
	public MemberCentrAdapter(Context context, Integer[] icon, String[] title) {
		super();
		this.context = context;
		this.icon = icon;
		this.title = title;
	}

	@Override
	public int getCount() {

		return title.length;
	}

	@Override
	public Object getItem(int position) {

		return position;
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		
		ViewHolder holder = null;

		if (convertView == null) {

			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.membercenter_item, null);

			holder.icon = (ImageView) convertView.findViewById(R.id.membercenter_iv);
			holder.title = (TextView) convertView.findViewById(R.id.membercenter_title);
			
			convertView.setTag(holder);
			
		}else {
			
			holder = (ViewHolder) convertView.getTag();
			
		}
			
		holder.icon.setImageResource(icon[position]);
//		holder.icon.setBackgroundResource(icon[position]);
		holder.title.setText(title[position]);
		
		return convertView;
	}

	static class ViewHolder{
		
		ImageView icon;
		TextView title;
		
	}
	
}
