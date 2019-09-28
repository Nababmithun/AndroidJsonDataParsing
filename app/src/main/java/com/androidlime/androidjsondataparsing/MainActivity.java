package com.androidlime.androidjsondataparsing;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView= (ListView) findViewById(R.id.ll);



        JsonTask jsonTask=new JsonTask();
        jsonTask.execute();
    }


    public  class JsonTask extends AsyncTask<String,String,List<DemoStudent>>{


        @Override
        protected List<DemoStudent> doInBackground(String... params) {


            HttpsURLConnection httpsURLConnection=null;
            BufferedReader bufferedReader=null;

            String JsonFile;

            try {
                URL url =new URL("https://api.myjson.com/bins/cnl1l");
                httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.connect();
                InputStream inputStream=httpsURLConnection.getInputStream();
                bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer=new StringBuffer();
                String line=" ";
                while ((line=bufferedReader.readLine())!=null){


                    stringBuffer.append(line);



                }


                JsonFile =stringBuffer.toString();

                JSONObject mainObject=new JSONObject(JsonFile);

                JSONArray studentArray=mainObject.getJSONArray("student");

                List<DemoStudent>  demoStudentList=new ArrayList<>();


                for (int i=0;i<studentArray.length();i++)
                {

                   JSONObject ArrayObject=studentArray.getJSONObject(i);

                    DemoStudent demoStudent =new DemoStudent();

                    demoStudent.setName(ArrayObject.getString("name"));
                    demoStudent.setDept(ArrayObject.getString("Dept"));

                    demoStudent.setCountry(ArrayObject.getString("Country"));
                    demoStudentList.add(demoStudent);

                }


              //  return stringBuffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }finally {
                try {
                    httpsURLConnection.disconnect();

                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<DemoStudent> s) {
            super.onPostExecute(s);


            CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),R.layout.sample,s);


            listView.setAdapter(customAdapter);
        }
    }
}
