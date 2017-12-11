package com.example.cadtc.androidwithmysqlphpsqlite;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



public class BlankFragment extends Fragment implements View.OnClickListener {

    ImageView computerSelection, userScissors, userStone, userPaper;
    TextView status;
    TextView winCount, peaceCount, loseCount;
    int win, peace, lose;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //填滿此片段的佈局
        return inflater.inflate(R.layout.main1, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        computerSelection = (ImageView) getView().findViewById(R.id.computerSelection);
        status = (TextView) getView().findViewById(R.id.status);
        userScissors = (ImageView) getView().findViewById(R.id.usera1);
        userStone = (ImageView) getView().findViewById(R.id.usera2);
        userPaper = (ImageView) getView().findViewById(R.id.usera3);
        userScissors.setOnClickListener(this);
        userStone.setOnClickListener(this);
        userPaper.setOnClickListener(this);

        winCount = (TextView) getActivity().findViewById(R.id.winCount);
        peaceCount = (TextView) getActivity().findViewById(R.id.peaceCount);
        loseCount = (TextView) getActivity().findViewById(R.id.loseCount);

    }

    //電腦亂數出拳
    @Override
    public void onClick(View V) {
        String computer= (((int)(Math.random()*3))+1)+"";
        if(computer.equals("1"))
            computerSelection.setImageResource(R.drawable.a1);
        else if(computer.equals("2"))
            computerSelection.setImageResource(R.drawable.a2);
        else
            computerSelection.setImageResource(R.drawable.a3);


        //假設結果
        if(V.getId()== R.id.usera1){
            if(computer.equals("1")) {
                status.setText("平手");
                peace++;
            }
            else if(computer.equals("2")) {
                status.setText("輸了");
                lose++;
            }
            else {
                status.setText("贏了");
                win++;
            }
        }
        else if(V.getId()== R.id.usera2){
            if(computer.equals("1")) {
                status.setText("贏了");
                win++;
            }
            else if(computer.equals("2")) {
                status.setText("平手");
                peace++;
            }
            else {
                status.setText("輸了");
                lose++;
            }
        }
        else{
            if(computer.equals("1")) {
                status.setText("輸了");
                lose++;
            }
            else if(computer.equals("2")) {
                status.setText("贏了");
                win++;
            }
            else {
                status.setText("平手");
                peace++;
            }
        }
    //計算輸贏平手次數
        winCount.setText("贏了:"+ win +"次");
        peaceCount.setText("平手:"+ peace +"次");
        loseCount.setText("輸了:"+ lose +"次");
    }
}
