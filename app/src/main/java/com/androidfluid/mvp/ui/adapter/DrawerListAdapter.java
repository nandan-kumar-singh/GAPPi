package com.androidfluid.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidfluid.mvp.R;
import com.androidfluid.mvp.model.Contact;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by singh on 23/10/17.
 */

public class DrawerListAdapter extends RecyclerView.Adapter<DrawerListAdapter.ViewHolder> {
    private Context context;
    private List<Contact> userList;
    private OnClickListener onClickListener;

    public DrawerListAdapter(Context context, List<Contact> userList, OnClickListener onClickListener) {
        this.context = context;
        this.userList = userList;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Contact contact = userList.get(position);

        holder.tvUserName.setText(contact.name);
        holder.tvContactNo.setText(contact.mobileNumber);
        Glide.with(context).asBitmap().load(contact.photoURI).into(holder.icon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(contact, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (userList == null)
            return 0;
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.firstLine)
        TextView tvUserName;
        @BindView(R.id.secondLine)
        TextView tvContactNo;
        @BindView(R.id.icon)
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickListener {

        void onClick(Contact contact, int position);
    }
}
