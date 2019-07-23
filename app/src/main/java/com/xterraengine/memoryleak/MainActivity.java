package com.xterraengine.memoryleak;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private Hilo hilo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //generarMemoryLeak();

        noGeneraMemoryLeak();
    }


    private void noGeneraMemoryLeak() {


        hilo = new Hilo();
        hilo.start();

    }

    private void generarMemoryLeak() {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static class Hilo extends Thread {
        private boolean hiloActivivo = true;


        @Override
        public void run() {
            while (hiloActivivo) {
                try {
                    sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        private void close() {
            hiloActivivo = false;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hilo.close();
    }
}
