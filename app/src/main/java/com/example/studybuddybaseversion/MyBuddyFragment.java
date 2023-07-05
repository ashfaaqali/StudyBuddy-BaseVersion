package com.example.studybuddybaseversion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studybuddybaseversion.MyBuddy.Message;
import com.example.studybuddybaseversion.MyBuddy.MessageAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyBuddyFragment extends Fragment {
    RecyclerView  recyclerView;
    LinearLayout examples;
    EditText messageEditText;
    Button sendButton;
    List<Message> msgList;
    MessageAdapter messageAdapter;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_buddy_fragment, null);
        recyclerView = (RecyclerView) v.findViewById(R.id.chat_messages_recycler_view);
        examples = (LinearLayout) v.findViewById(R.id.examples);
        messageEditText = (EditText) v.findViewById(R.id.message_input_edit_text);
        sendButton = (Button) v.findViewById(R.id.send_message_button);
        msgList = new ArrayList<>();

        //set up Recycler View
        messageAdapter = new MessageAdapter(msgList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager LLM = new LinearLayoutManager(getContext());
        LLM.setStackFromEnd(true);
        recyclerView.setLayoutManager(LLM);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = messageEditText.getText().toString().trim();
                AddToChat(question, Message.SENT_BY_ME);
                messageEditText.setText("");
                CallAPI(question);
                examples.setVisibility(View.GONE);
            }
        });
        return v;
    }
    void AddToChat (String msg, String sentBy){
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                msgList.add(new Message(msg, sentBy));
                messageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });
    }

    void AddResponse(String response){
        msgList.remove(msgList.size()-1);
        AddToChat(response, Message.SENT_BY_BOT);
    }

//    Calling Using OKHTTP
    void CallAPI(String question){
        msgList.add(new Message("Typing...", Message.SENT_BY_BOT));

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("model", "text-davinci-003");
            jsonObject.put("max_tokens", 4000);
            jsonObject.put("prompt", question);
            jsonObject.put("temperature", 0);
            jsonObject.put("n", 1);
            jsonObject.put("stream", false);
            jsonObject.put("logprobs", null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(jsonObject.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .header("Authorization", "Bearer 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")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                AddResponse("Failed to load the response due to"+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        AddResponse(result.trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    AddResponse("Failed to load the response due to"+response.body().toString());
                }
            }
        });


    }
}
