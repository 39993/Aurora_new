package com.aorora.app.widget;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.aorora.app.R;
import com.aorora.app.bean.TypeClass;

import java.util.List;

/**
 * Created by Administrator on 2017/5/27.
 */
public class TypeWindow extends PopupWindow {


    private List<TypeClass> rules;
    private MyGridView typeview;
    private Activity context;
    private MyTypeClickLisetener listener;

    public TypeWindow(Activity context, List<TypeClass> rules,MyTypeClickLisetener listener) {
        super(context);
        this.rules = rules;
        this.context = context;
        this.listener = listener;

        init( rules);

    }

    private void init(List<TypeClass> rules) {

        View view = LayoutInflater.from(context).inflate(R.layout.type_popwindow,null);
        typeview = (MyGridView) view.findViewById(R.id.type_grideview);

        TypeAdapter adapter = new TypeAdapter(rules,context);
        typeview.setAdapter(adapter);
        typeview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(null!=listener){

                    listener.ItemClicklistener(view,position);

                }
            }
        });



        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wn = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wn.getDefaultDisplay().getMetrics(metric);

        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);

        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(width);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
//        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);


        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {

                WindowManager.LayoutParams lp =context.getWindow().getAttributes();
                lp.alpha = 1.0f;
                context.getWindow().setAttributes(lp);

            }
        });

    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
        } else {
            this.dismiss();
        }
    }

    public void dismissPopupWindow() {

        if(this.isShowing()){
            this.dismiss();
        }

    }


    public interface MyTypeClickLisetener{

        void ItemClicklistener(View view , int position);

    }

    class TypeAdapter extends BaseAdapter{

        private List<TypeClass> data;
        private Context context;

        public TypeAdapter(List<TypeClass> data, Context context) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size()>0?data.size():0;
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder ;
            if(convertView==null){
                holder=new ViewHolder();
                convertView=LayoutInflater.from(context).inflate(R.layout.type_item, null);
                holder.tvName=(TextView) convertView.findViewById(R.id.rules);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder) convertView.getTag();
            }
            holder.tvName.setText(data.get(position).name);

            return convertView;

        }

        private class ViewHolder{

            private TextView tvName;
        }

    }

}
