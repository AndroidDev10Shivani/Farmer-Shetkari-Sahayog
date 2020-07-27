package com.sample.shetkarisahayogfarmer.DashboardActivities;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.shetkarisahayogfarmer.R;

public class AggregatorViewHolder extends RecyclerView.ViewHolder {

    TextView textViewName, textViewAppId, textViewMobileNo, textViewAddress;
    TextView textViewDemandName, textViewDemandAggId, textViewDemandFarmProduct, textViewDemandQuantity, textViewDemandAddress, textViewDemandDate;
    TextView textViewDealName, textViewDealAggId, textViewDealFarmProduct, textViewDealQuantity, textViewDealAddress, textViewDealDate;
    Button buttonAccept;

    public AggregatorViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.aggName);
        textViewAppId = itemView.findViewById(R.id.aggId);
        textViewMobileNo = itemView.findViewById(R.id.aggMobileNo);
        textViewAddress = itemView.findViewById(R.id.aggAddress);

        textViewDemandName = itemView.findViewById(R.id.aggDemandName);
        textViewDemandAggId = itemView.findViewById(R.id.aggDemandId);
        textViewDemandFarmProduct = itemView.findViewById(R.id.aggDemandedCrop);
        textViewDemandQuantity = itemView.findViewById(R.id.aggDemandQuantityReq);
        textViewDemandAddress = itemView.findViewById(R.id.aggDemandAddress);
        textViewDemandDate = itemView.findViewById(R.id.aggDemandDate);
        buttonAccept = itemView.findViewById(R.id.button_accept_deal);

        textViewDealName = itemView.findViewById(R.id.dealName);
        textViewDealAggId = itemView.findViewById(R.id.dealId);
        textViewDealFarmProduct = itemView.findViewById(R.id.dealCrop);
        textViewDealQuantity = itemView.findViewById(R.id.dealQuantityReq);
        textViewDealDate = itemView.findViewById(R.id.dealDate);
    }
}
