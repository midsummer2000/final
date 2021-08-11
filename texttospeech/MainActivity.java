package com.example.text_to_speech;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import static android.media.AudioManager.STREAM_MUSIC;

public class MainActivity extends AppCompatActivity {
    TextToSpeech t1;
    EditText ed1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.editText);
        b1=(Button)findViewById(R.id.speech);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    int result = t1.setLanguage(Locale.UK);
                    if(result == TextToSpeech.LANG_NOT_SUPPORTED||result == TextToSpeech.LANG_MISSING_DATA){
                        Toast.makeText(getApplicationContext(),"Language is not supported", Toast.LENGTH_SHORT).show();
                    }else{
                        b1.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onClick(View v) {
                                String tospeak = ed1.getText().toString();
                                speak_text(tospeak);
                            }
                        });
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"INTI is failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void speak_text(String str){
        Bundle bundle = new Bundle();
        bundle.putInt(TextToSpeech.Engine.KEY_PARAM_STREAM, AudioManager.STREAM_MUSIC);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            t1.speak(str,TextToSpeech.QUEUE_FLUSH, bundle, null);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(t1!=null){
            t1.stop();
            t1.shutdown();
        }
    }

}