package com.example.virtualshop;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ItemsActivity extends Activity {
	ListView lista;
	ArrayList<String> nume;
	ArrayAdapter<String> adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_activity);
        
        lista = (ListView) findViewById(android.R.id.list);
        nume = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nume);
        
        HashMap<String, String> params = new HashMap<String, String>();
        ServerConnection.sharedInstance().getRequest("/list/", params, new AsyncHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(String js) 
                    {
                        super.onSuccess(js);
                        System.out.println("LIST Post Request Succesfull");

                        JSONArray jsonList;
                        try 
                        {
                            jsonList = new JSONArray(js);
                                for(int i = 0; i < jsonList.length(); i++) {
                                    JSONObject jsonElem = (JSONObject) jsonList.get(i);
                                    System.out.println(jsonElem.toString());
                                    }
                        }  
                        catch (JSONException e) {
                            System.out.println(e);
                        }       
                    }

                    @SuppressWarnings("deprecation")
                    @Override
                    public void onFailure(Throwable arg0) {
                        System.out.println("LIST Post Request Failed " + arg0.getMessage());
                        super.onFailure(arg0);
                    }
                });


        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
