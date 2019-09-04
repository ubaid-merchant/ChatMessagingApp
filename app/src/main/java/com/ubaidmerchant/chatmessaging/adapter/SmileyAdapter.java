package com.ubaidmerchant.chatmessaging.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ubaidmerchant.chatmessaging.R;
import java.util.List;

public class SmileyAdapter extends RecyclerView.Adapter<SmileyAdapter.SmileyViewHolder> {
  private List<Drawable> smileyList;
  private Context context;
  private SmileyInteraction interaction;

  public SmileyAdapter(Context context, List<Drawable> list, SmileyInteraction interaction) {
    this.context = context;
    this.smileyList = list;
    this.interaction = interaction;
  }

  @NonNull @Override
  public SmileyAdapter.SmileyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    View view = LayoutInflater.from(context)
        .inflate(R.layout.item_smileys_list, parent, false);
    return new SmileyAdapter.SmileyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull SmileyAdapter.SmileyViewHolder viewHolder, int position) {
    viewHolder.ivSmiley.setImageDrawable(smileyList.get(position));
  }

  @Override public int getItemCount() {
    return smileyList.size();
  }

  class SmileyViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivSmiley;

    SmileyViewHolder(@NonNull View itemView) {
      super(itemView);
      ivSmiley = itemView.findViewById(R.id.ivSmiley);
      ivSmiley.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          interaction.onSmileyClicked(getAdapterPosition());
        }
      });
    }
  }

  public interface SmileyInteraction {
    void onSmileyClicked(int position);
  }
}