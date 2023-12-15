package com.example.mp_project;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GroundAdapter extends RecyclerView.Adapter<GroundAdapter.ViewHolder> {

    private List<Ground> groundList;

    public GroundAdapter(List<Ground> groundList) {
        this.groundList = groundList;
        // 디버깅 메시지를 통해 데이터 유무 확인
        if (groundList == null || groundList.isEmpty()) {
            Log.d("GroundAdapter", "Data list is empty or null");
        } else {
            Log.d("GroundAdapter", "Data list size: " + groundList.size());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.ground_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ground ground = groundList.get(position);
        // 디버깅 메시지를 통해 데이터 설정 확인
        Log.d("GroundAdapter", "Setting data for position " + position +
                ": FacilityName=" + ground.getFacilityName() +
                ", PlotArea=" + ground.getPlotArea());
        holder.tvFacilityName.setText(ground.getFacilityName());
        holder.tvPlotArea.setText(ground.getPlotArea());
        holder.tvBottomName.setText(ground.getBOTM_MATRL_NM());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFacilityName;
        public TextView tvPlotArea;
        public TextView tvBottomName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFacilityName = itemView.findViewById(R.id.textView);
            tvPlotArea = itemView.findViewById(R.id.textView2);
            tvBottomName = itemView.findViewById((R.id.textView3));

            // 로그 추가
            Log.d("ViewHolder", "ViewHolder created");
        }

        public void setItem(Ground ground) {
            tvFacilityName.setText(ground.FACLT_NM);
            Log.d("영화명 : ", tvFacilityName.getText().toString());
            tvPlotArea.setText(ground.PLOT_AR + "제곱미터");
            tvBottomName.setText("바닥재료" + ground.BOTM_MATRL_NM);
        }
    }


    @Override
    public int getItemCount() {
        return groundList.size();
    }
}