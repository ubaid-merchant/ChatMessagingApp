package com.ubaidmerchant.chatmessaging.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ubaidmerchant.chatmessaging.R;
import com.ubaidmerchant.chatmessaging.models.MessageModel;
import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int VIEW_TYPE_MY_MESSAGE = 1;
  private static final int VIEW_TYPE_THEIR_MESSAGE = 2;

  private List<MessageModel> messages;
  private Context context;
  private MessageInteraction interaction;

  public MessageAdapter(Context context, List<MessageModel> messagesList,
      MessageInteraction interaction) {
    this.context = context;
    this.messages = messagesList;
    this.interaction = interaction;
  }

  @Override
  public int getItemViewType(int position) {
    if (messages.get(position).isBelongsToCurrentUser()) {
      return VIEW_TYPE_MY_MESSAGE;
    } else {
      return VIEW_TYPE_THEIR_MESSAGE;
    }
  }

  @NonNull @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view;

    if (viewType == VIEW_TYPE_THEIR_MESSAGE) { // for their_message layout
      view = LayoutInflater.from(context)
          .inflate(R.layout.their_message, parent, false);

      return new TheirMessageViewHolder(view);
    } else { // for my_message layout
      view = LayoutInflater.from(context)
          .inflate(R.layout.my_message, parent, false);

      return new MyMessageViewHolder(view);
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
    if (getItemViewType(position) == VIEW_TYPE_THEIR_MESSAGE) {
      ((TheirMessageViewHolder) viewHolder).setTheirMessageDetails(messages.get(position));
    } else {
      ((MyMessageViewHolder) viewHolder).setMyMessageDetails(messages.get(position));
    }
  }

  @Override public int getItemCount() {
    return messages.size();
  }

  class MyMessageViewHolder extends RecyclerView.ViewHolder {
    private TextView messageBody;
    private TextView messageTime;

    MyMessageViewHolder(@NonNull View itemView) {
      super(itemView);
      messageBody = itemView.findViewById(R.id.tvMessageBody);
      messageTime = itemView.findViewById(R.id.tvMessageTime);
    }

    private void setMyMessageDetails(MessageModel message) {
      messageBody.setText(message.getText());
      messageTime.setText(message.getTime());
    }
  }

  class TheirMessageViewHolder extends RecyclerView.ViewHolder {
    private TextView initial;
    private TextView name;
    private TextView messageBody;
    private TextView messageTime;
    private ImageView ivMessageSmiley;
    private LinearLayout llSmileys;
    private RecyclerView rvSmileys;

    TheirMessageViewHolder(@NonNull View itemView) {
      super(itemView);
      initial = itemView.findViewById(R.id.tvInitials);
      name = itemView.findViewById(R.id.tvMessageRecipient);
      messageBody = itemView.findViewById(R.id.tvMessageBody);
      messageTime = itemView.findViewById(R.id.tvMessageTime);
      ivMessageSmiley = itemView.findViewById(R.id.ivMessageSmiley);
      llSmileys = itemView.findViewById(R.id.llSmileys);
      rvSmileys = itemView.findViewById(R.id.rvSmileys);
    }

    private void setSmileyData() {
      ArrayList<Drawable> smileyList = new ArrayList<>();
      smileyList.add(context.getDrawable(R.drawable.step_1));
      smileyList.add(context.getDrawable(R.drawable.step_2));
      smileyList.add(context.getDrawable(R.drawable.step_3));
      smileyList.add(context.getDrawable(R.drawable.step_4));
      smileyList.add(context.getDrawable(R.drawable.step_1));
      smileyList.add(context.getDrawable(R.drawable.step_2));
      smileyList.add(context.getDrawable(R.drawable.step_3));
      smileyList.add(context.getDrawable(R.drawable.step_4));

      SmileyAdapter smileyAdapter = new SmileyAdapter(context, smileyList);
      rvSmileys.setAdapter(smileyAdapter);
      llSmileys.setVisibility(View.VISIBLE);
    }

    private void setTheirMessageDetails(MessageModel message) {
      name.setText(message.getMemberData().getName());
      messageBody.setText(message.getText());
      messageTime.setText(message.getTime());
      initial.setText(message.getInitials());
      GradientDrawable drawable = (GradientDrawable) initial.getBackground();
      drawable.setColor(Color.parseColor(message.getMemberData().getColor()));

      ivMessageSmiley.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          setSmileyData();
          interaction.onSmileyClicked(getAdapterPosition());
        }
      });
    }
  }

  public interface MessageInteraction {
    void onSmileyClicked(int position);
  }
}