package com.application.android.preschool.firstletter;

import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements TextToSpeech.OnInitListener {

    List<String> myWords = new ArrayList<String>();
    Button letterA, letterB, letterC, letterD, letterE;
    ImageButton btnspk;
    private String question = "";
    TextView q;
    private TextToSpeech tts;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        //Addding words to list

        myWords.add("APPLE");myWords.add("ANT");myWords.add("ALLIGATOR");myWords.add("ASPARAGUS");
        myWords.add("BALL");myWords.add("BELL");myWords.add("BALOON");myWords.add("BEST");

//        myWords.add("CAT");myWords.add("DOG");myWords.add("EGG");myWords.add("FISH");
//        myWords.add("GATE");myWords.add("HAT");


        //final String question = String.valueOf((R.string.APPLE));


        q = (TextView) root.findViewById(R.id.question);


        letterA = (Button) root.findViewById(R.id.alphabetA);
        letterB = (Button) root.findViewById(R.id.alphabetB);
        letterC = (Button) root.findViewById(R.id.alphabetC);
        letterD = (Button) root.findViewById(R.id.alphabetD);
        letterE = (Button) root.findViewById(R.id.alphabetE);
        btnspk = (ImageButton) root.findViewById(R.id.speakloud);

        Random randomizer = new Random();
        question = myWords.get(randomizer.nextInt(myWords.size()));

        //for(int i=0;i<10;i++) {

        //final String finalQuestion = question;

        tts = new TextToSpeech(getContext(), this);

        btnspk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });
        q.setText(question);
        letterA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (question.charAt(0) == 'A') {
                    Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();
                    updateWord();
                } else
                    Toast.makeText(getContext(), "InCorrect", Toast.LENGTH_SHORT).show();
            }
        });

        letterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (question.charAt(0) == 'B') {
                    Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();
                    updateWord();
                } else
                    Toast.makeText(getContext(), "InCorrect", Toast.LENGTH_SHORT).show();

            }
        });

        letterC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        letterD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        letterE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // }

        return root;
    }

    private void updateWord() {
        Random randomizer = new Random();
        question = myWords.get(randomizer.nextInt(myWords.size()));
        q.setText(question);
    }


    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);
            tts.setPitch((float)1.0);
            tts.setSpeechRate((float)0.2);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                btnspk.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    private void speakOut() {

        tts.speak(question, TextToSpeech.QUEUE_FLUSH, null,null);
    }
}
