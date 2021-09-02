package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleAsyncTask extends AsyncTask<Integer,Integer,String>{

    private WeakReference<TextView> mTextView;
    private ProgressBar progressBar;

    private static final String LOG_TAG = SimpleAsyncTask.class.getSimpleName();

    SimpleAsyncTask(ProgressBar progressBar, TextView tv) {
        mTextView = new WeakReference<>(tv);
        this.progressBar = progressBar;
    }

    @Override
    protected String doInBackground(Integer... values) {

        // Generate a random number between 0 and 10.
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running.
        int s = n * 200;
        int counter=0;
        int progress=0;
        int purse = (int)100 /n;
        // Sleep for the random amount of time.
        for (;counter<=n;counter++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progress +=purse;
            publishProgress(progress);


        }
        // Return a String result
        return "Awake at last after sleeping for " + n + " seconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
        Log.d("prgress", String.valueOf(values[0]));

    }

    @Override
    protected  void onPostExecute(String result){
        mTextView.get().setText(result);
    }
}
