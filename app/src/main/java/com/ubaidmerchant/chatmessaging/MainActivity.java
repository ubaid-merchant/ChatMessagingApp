package com.ubaidmerchant.chatmessaging;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
  private RecyclerView rvMessages;
  private ImageView ivAddIcon;
  private ImageView ivVoiceIcon;
  private ImageView ivCameraIcon;
  private ArrayList<MessageModel> messagesList = new ArrayList<>();
  private String messageRecipient;
  private String messageRecipientInitials;
  private SimpleDateFormat sdFormat;
  private String messageTime;
  private MessageAdapter messageAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = findViewById(R.id.toolbar);
    edtComposeMessage = findViewById(R.id.edtComposeMessage);
    rvMessages = findViewById(R.id.rvMessages);
    ivAddIcon = findViewById(R.id.ivAddIcon);
    ivVoiceIcon = findViewById(R.id.ivVoiceIcon);
    ivCameraIcon = findViewById(R.id.ivCameraIcon);

    setSupportActionBar(toolbar);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    initListener();
    setMessagesData();
  }

  private void initListener() {
    ivAddIcon.setOnClickListener(this);
    ivVoiceIcon.setOnClickListener(this);
    ivCameraIcon.setOnClickListener(this);

    edtComposeMessage.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
          sendMessage();
        }
        return false;
      }
    });
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
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  private void setMessagesData() {
    messageRecipient = getString(R.string.dummy_message_recipient);
    messageRecipientInitials = getString(R.string.dummy_message_recipient_initials);

    sdFormat = new SimpleDateFormat("HH:mm");
    messageTime = sdFormat.format(Calendar.getInstance().getTime());

    MessageModel myMsg = new MessageModel(getString(R.string.my_test_message_dummy),
        new MemberDataModel(messageRecipient, getRandomColor()), true,
        messageTime, messageRecipientInitials);
    MessageModel theirMsg = new MessageModel(getString(R.string.their_test_message_dummy),
        new MemberDataModel(messageRecipient, getRandomColor()), false,
        messageTime, messageRecipientInitials);
    MessageModel theirMsg1 = new MessageModel(getString(R.string.their_test_message_dummy_1),
        new MemberDataModel(messageRecipient, getRandomColor()), false,
        messageTime, messageRecipientInitials);

    messagesList.add(myMsg);
    messagesList.add(theirMsg);
    messagesList.add(myMsg);
    messagesList.add(theirMsg1);

    messageAdapter = new MessageAdapter(this, messagesList, this);
    rvMessages.setAdapter(messageAdapter);
  }

  private void sendMessage() {
    String message = edtComposeMessage.getText().toString();
    if (message.length() > 0) {
      messageTime = sdFormat.format(Calendar.getInstance().getTime());

      MessageModel myMsg = new MessageModel(message,
          new MemberDataModel(messageRecipient, getRandomColor()), true,
          messageTime, messageRecipientInitials);
      messagesList.add(myMsg);
      messageAdapter.notifyDataSetChanged();
      edtComposeMessage.getText().clear();
      rvMessages.scrollToPosition(messagesList.size() - 1);
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

  @Override public void onBackPressed() {
    super.onBackPressed();
  }

  private void showToastMessage(String message) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }

  @Override public void onMessageSmileyClicked(int position) {
    showToastMessage("Message Smiley Clicked!");
  }
}

