package com.example.jpr_dealear;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jpr_dealear.Activity.Add_DealearsActivity;
import com.example.jpr_dealear.Activity.Dealears_listActivity;

import java.util.List;

public class DealearsAdapter extends RecyclerView.Adapter<DealearsAdapter.dealearslistviewholder> {
    Dealears_listActivity dealears_listActivity;
    private List<DealerModel> dealerModelList;
    OnLongClickAction onLongClickAction;

    public DealearsAdapter(Dealears_listActivity dealears_listActivity, List<DealerModel> dealerModelList) {
        this.dealerModelList = dealerModelList;
        this.dealears_listActivity = dealears_listActivity;
    }

    @Override
    public dealearslistviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dealears_list, parent, false);
        return new dealearslistviewholder(view);
    }

    @Override
    public void onBindViewHolder(dealearslistviewholder holder, final int position) {
        final String dealearsname = dealerModelList.get(position).getDealearsname();
        final String phone = dealerModelList.get(position).getPhonenumber();
        final int d_id  = dealerModelList.get(position).getD_id();
        holder.dealearsname.setText(dealearsname);
        holder.phonenumber.setText(phone);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dealears_listActivity, Add_DealearsActivity.class);
                intent.putExtra("name", dealearsname);
                intent.putExtra("phone", phone);
                intent.putExtra("d_id", d_id);

                dealears_listActivity.startActivity(intent);
            }
        });


        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onLongClickAction.onLongClick(position);
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return dealerModelList.size();
    }

    public void setOnLongClick(OnLongClickAction onLongClickAction) {


    }

    public class dealearslistviewholder extends RecyclerView.ViewHolder {

        ImageView call;
        TextView dealearsname, phonenumber;
        View view;

        public dealearslistviewholder(View itemView) {
            super(itemView);

            view = itemView;

            call = itemView.findViewById(R.id.callicon);
            dealearsname = itemView.findViewById(R.id.name1);
            phonenumber = itemView.findViewById(R.id.number1);
        }
    }


    public interface OnLongClickAction {
        public void onLongClick(int position);
    }
}
