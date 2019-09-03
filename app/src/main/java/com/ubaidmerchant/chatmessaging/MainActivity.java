package com.ubaidmerchant.chatmessaging;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ubaidmerchant.chatmessaging.adapter.MessageAdapter;
import com.ubaidmerchant.chatmessaging.models.MemberDataModel;
import com.ubaidmerchant.chatmessaging.models.MessageModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener, MessageAdapter.MessageInteraction {

  private EditText edtComposeMessage;
  private RecyclerView messagesView;
  private ImageView ivAddIcon;
  private ImageView ivVoiceIcon;
  private ImageView ivCameraIcon;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = findViewById(R.id.toolbar);
    edtComposeMessage = findViewById(R.id.edtComposeMessage);
    messagesView = findViewById(R.id.rvMessages);
    ivAddIcon = findViewById(R.id.ivAddIcon);
    ivVoiceIcon = findViewById(R.id.ivVoiceIcon);
    ivCameraIcon = findViewById(R.id.ivCameraIcon);

    setSupportActionBar(toolbar);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    initOnClickListener();
    setMessagesData();
  }

  private void initOnClickListener() {
    ivAddIcon.setOnClickListener(this);
    ivVoiceIcon.setOnClickListener(this);
    ivCameraIcon.setOnClickListener(this);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.videoAction:
        showToastMessage("Video Action Clicked!");
        break;
      case R.id.callAction:
        showToastMessage("Call Action Clicked!");
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  private void setMessagesData() {
    ArrayList<MessageModel> messages = new ArrayList<>();
    String messageRecipient = getString(R.string.dummy_message_recipient);
    String messageRecipientInitials = getString(R.string.dummy_message_recipient_initials);

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    String messageTime = sdf.format(Calendar.getInstance().getTime());

    MessageModel myMsg = new MessageModel(getString(R.string.my_test_message),
        new MemberDataModel(messageRecipient, getRandomColor()), true, messageTime,
        messageRecipientInitials);
    MessageModel theirMsg = new MessageModel(getString(R.string.their_test_message),
        new MemberDataModel(messageRecipient, getRandomColor()), false, messageTime,
        messageRecipientInitials);
    MessageModel myMsg1 = new MessageModel(getString(R.string.my_test_message),
        new MemberDataModel(messageRecipient, getRandomColor()), true, messageTime,
        messageRecipientInitials);
    MessageModel theirMsg1 = new MessageModel(getString(R.string.their_test_message_1),
        new MemberDataModel(messageRecipient, getRandomColor()), false, messageTime,
        messageRecipientInitials);

    messages.add(myMsg);
    messages.add(theirMsg);
    messages.add(myMsg1);
    messages.add(theirMsg1);

    MessageAdapter messageAdapter = new MessageAdapter(this, messages, this);
    messagesView.setLayoutManager(new LinearLayoutManager(this));
    messagesView.setAdapter(messageAdapter);
  }

  public void sendMessage(View view) {
    String message = edtComposeMessage.getText().toString();
    if (message.length() > 0) {
      edtComposeMessage.getText().clear();
    }
  }

  private String getRandomColor() {
    Random r = new Random();
    StringBuilder sb = new StringBuilder("#");
    while (sb.length() < 7) {
      sb.append(Integer.toHexString(r.nextInt()));
    }
    return sb.toString().substring(0, 7);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.ivAddIcon:
        showToastMessage("Add Action Clicked!");
        break;
      case R.id.ivVoiceIcon:
        showToastMessage("Audio Action Clicked!");
        break;
      case R.id.ivCameraIcon:
        showToastMessage("Camera Action Clicked!");
        break;
    }
  }

  private void showToastMessage(String message) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }

  @Override public void onSmileyClicked(int position) {
    showToastMessage("Smiley Clicked!");
  }
}

