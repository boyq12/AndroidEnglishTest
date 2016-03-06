package com.quanglh.englishtest.ultils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.quanglh.englishtest.R;

import java.util.List;

/**
 * Created by Dell7548 on 3/3/2016.
 */
public class CustomMenuListAdapter extends BaseAdapter {
    private List<MainMenuElement> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomMenuListAdapter(Context aContext, List<MainMenuElement> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.main_menulist_item_layout, null);
            holder = new ViewHolder();
            holder.iconView = (ImageView) convertView.findViewById(R.id.image_icon_mainlist);
            holder.nameView = (TextView) convertView.findViewById(R.id.text_mainlistView_name);
            holder.noteView = (TextView) convertView.findViewById(R.id.text_mainlistView_note);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MainMenuElement mainMenuElement = this.listData.get(position);
        holder.nameView.setText(mainMenuElement.getMenuName());
        holder.noteView.setText("Note: " + mainMenuElement.getNote());

        int imageId = this.getMipmapResIdByName(mainMenuElement.getIconName());

        holder.iconView.setImageResource(imageId);

        return convertView;
    }

    static class ViewHolder {
        TextView nameView;
        TextView noteView;
        ImageView iconView;
        //ProgressBar progress;
        int position;
    }

    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        //Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
}
