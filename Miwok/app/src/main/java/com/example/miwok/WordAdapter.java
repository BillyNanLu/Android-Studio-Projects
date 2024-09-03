package com.example.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorResourceId;
    public WordAdapter(Activity context, ArrayList<Word> words, int mColorResourceId) {
        // 第二个参数是当ArrayAdapter被单个TextView填充时用到的自定义适配器包含两个TextView和1个ImageView，所以第二个参数可以是任意值，在此使用0
        super(context, 0, words);
        this.mColorResourceId = mColorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 检查是否有回收视图可用，如果没有则新创建一个
        View listitemView = convertView;
        if (listitemView == null)
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        // 定位到列表中的某个Word对象
        Word currentWord = getItem(position);

        // 找到布局文件list_item.xml中的id为miwork_text_view的TextView
        TextView miwokTextView = (TextView) listitemView.findViewById(R.id.miwok_text_view);
        // 从当前的Word对象缺省翻译，并显示在miwokTextView中
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // 找到布局文件list_item.xml中的id为default_text_view的TextView
        TextView defaultTextView = (TextView) listitemView.findViewById(R.id.default_text_view);
        // 从当前的Word对象缺省翻译，并显示在defaultTextView中
        defaultTextView.setText(currentWord.getDefaultTranslation());


        // 找到布局文件list_item.xml中的id为list_item_icon的ImageView
        ImageView iconView = (ImageView) listitemView.findViewById(R.id.imageView);
        if (currentWord.hasImage()) {
            // 从当前的AndroidFlavor对象获取图像资源，并将图像作为iconView的图像来源
            iconView.setImageResource(currentWord.getMimageResourceld());
            iconView.setVisibility(View.VISIBLE);
        } else {
            iconView.setVisibility(View.GONE);
        }


        // 找到布局文件list_item.xml中的id为text_linearLayout的LinearLayout
        LinearLayout textLinearLayout = (LinearLayout) listitemView.findViewById(R.id.text_linearLayout);
        // 找到mColorResourceId对应的颜色值
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // 设置文本容器的背景颜色
        textLinearLayout.setBackgroundColor(color);

        // 返回包含2个TextView的整个列表项布局
        return listitemView;
    }
}
