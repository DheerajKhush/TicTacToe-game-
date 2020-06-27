package com.example.tictactoe;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        TextView txtPlayer1,txtPlayer2;
        Button ResetBut;
        Button [][]but= new Button[3][3];
        int player1Score, player2Score;
        boolean player1Turn= true;
        int roundCount=0;
        Button extbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPlayer1 = findViewById(R.id.txtply1);
        txtPlayer2 = findViewById(R.id.txtply2);
        ResetBut = findViewById(R.id.butReset);
        extbut=findViewById(R.id.button_exit);



        but[0][0] = findViewById(R.id.button_00);
        but[0][1] = findViewById(R.id.button_01);
        but[0][2] = findViewById(R.id.button_02);
        but[1][0] = findViewById(R.id.button_10);
        but[1][1] = findViewById(R.id.button_11);
        but[1][2] = findViewById(R.id.button_12);
        but[2][0] = findViewById(R.id.button_20);
        but[2][1] = findViewById(R.id.button_21);
        but[2][2] = findViewById(R.id.button_22);

        // set on click listener to all buttons
        for (int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++){
                but[i][j].setOnClickListener(this);
            }
        }
//        // initialize buttons with id
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                String str = "button_" + i + j;
//                int resID = getResources().getIdentifier(str, "str", getPackageName());
//                but[i][j] = (Button)findViewById(resID);
//                but[i][j].setOnClickListener(this);
//            }
//        }
//
        //Reset button on click working
    }
            public void ResetOnClick(View v) {

                Toast.makeText(this,"reset",Toast.LENGTH_SHORT).show();
                player1Score=0;
                player2Score=0;
                txtPlayer1.setText("Player 1 :: "+player1Score);
                txtPlayer2.setText("Player 2 :: "+player2Score);
                resetBoard();
            }
        @Override
        public void onClick(View v) {
            if(!((Button)v).getText().toString().equals("")) {
                return;
            }
            {
                if (player1Turn) {
                    ((Button) v).setText("X");
                } else {
                    ((Button) v).setText("O");
                }

               roundCount++;
            }
             if(checkForWin()){

                  if(player1Turn)
                {
                    player1Win();
                }
                else{

                      player2Win();
                 }
              }
               else if(roundCount==9){

                    draw();
               }
              else{

                  player1Turn= !player1Turn;
               }
           }


    private boolean checkForWin(){
         String[][] butntxt =new String[3][3];
         for (int i=0; i<3; i++){
             for(int j=0;j<3; j++){
                 butntxt[i][j]= but[i][j].getText().toString();
            }
        }

        //to check for same rows row1 all same
        //row2 all same
        //row3 all same
        for( int i=0;i<3;i++)
        {
            if(butntxt[i][0].equals(butntxt[i][1])&&
                butntxt[i][0].equals(butntxt[i][2])&&
                !butntxt[i][0].equals("")){
                return true;
            }
        }

        //to chekc for same value in column0
        //column1 all same
        //column2 all same
        for (int i=0;i<3;i++)
        {
            if(butntxt[0][i].equals(butntxt[1][i])&&
                    butntxt[0][i].equals(butntxt[2][i])&&
                     !butntxt[0][i].equals("")){
                return true;
            }
        }

        // Check for left to right up to down diagonal to be same
        if(butntxt[0][0].equals(butntxt[1][1])&&
                butntxt[0][0].equals(butntxt[2][2])&&
                        !butntxt[0][0].equals(""))
        {
            return true;

        }

        // check for left to right down to up diagonal to be same
        if (butntxt[0][2].equals(butntxt[1][1])&&
            butntxt[0][2].equals(butntxt[2][0])&&
            !butntxt[0][2].equals("")){
            return true;
        }

        return false;

    }
    private void player1Win(){
        player1Score++;
        roundCount=0;
        txtPlayer1.setText("Player 1 :: "+ player1Score);
        resetBoard();

    }
    private void player2Win() {
        player2Score++;
        roundCount=0;
        txtPlayer2.setText("Player 2 :: " + player2Score);
        resetBoard();
    }

    // to set text of button to empty
    private void resetBoard(){
            for(int i=0;i<3;i++){
                for (int j=0;j<3;j++)
                {
                    but[i][j].setText("");
                }
            }
    }
        private void draw(){
        roundCount=0;
            resetBoard();
    }
    // Exit button functionality and dialog box
    public void extDialog(View v){
        ViewGroup viewGroup =findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.exitdialogbox,viewGroup,false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog =builder.create();
        alertDialog.show();

        Button NoBut =findViewById(R.id.nobutton);
        NoBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void exitApp(View v)
    {
        MainActivity.this.finish();
        System.exit(0);

    }

}
