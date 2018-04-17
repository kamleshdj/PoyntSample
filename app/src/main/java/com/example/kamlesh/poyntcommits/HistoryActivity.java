package com.example.kamlesh.poyntcommits;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HistoryActivity extends AppCompatActivity {

    List<GitCommit> historyList = new ArrayList<GitCommit>();
    CommitRowAdapter adapter ;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        RestCalls restCalls = new RestCalls();
        restCalls.execute();

        //getCommitHistory();
        //CommitRowAdapter adapter = new CommitRowAdapter(historyList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recyclerView = findViewById(R.id.historyRecycleView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    protected void getCommitHistory() {
        historyList.add(new GitCommit("3333337", "User1", new Date()));
        historyList.add(new GitCommit("1232222", "User2", new Date()));
        historyList.add(new GitCommit("5556565", "User1", new Date()));
        historyList.add(new GitCommit("9990066", "User4", new Date()));
        historyList.add(new GitCommit("4466767", "User5", new Date()));
    }

    public class RestCalls extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            String endPoint = "https://api.github.com/repos/poynt/PoyntSamples/commits";
            Map<String, String> urlParams = new HashMap<String, String>();
            urlParams.put("per_page", "10");

            RestClient client = RestClient.getInstance();
            try {
                String response = client.get(endPoint, urlParams);
                return response;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String response) {
            Log.d("onPostExecute", response.substring(0, 100));

            if(response == null) return;

            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootArray = mapper.readTree(response);
                for(JsonNode root : rootArray) {
                    String id = root.path("sha").asText();

                    JsonNode author = root.path("commit").path("author");
                    String name = author.path("name").asText();

                    String dateString = author.path("date").asText();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Date date = dateFormat.parse(dateString);

                    historyList.add(new GitCommit(id, name, date));
                }

                adapter = new CommitRowAdapter(historyList);
                RecyclerView recyclerView = findViewById(R.id.historyRecycleView);
                recyclerView.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
