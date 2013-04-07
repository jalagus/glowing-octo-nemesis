package zyklon;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HighScoreLoader {

    public HighScore[] highscores() {
        HighScore[] scores;

        try {
            FileInputStream fstream = new FileInputStream("highscores.txt");

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            int i = 0;

            HighScore[] tempScores = new HighScore[10];

            while ((strLine = br.readLine()) != null && i < 10) {
                String[] temp = strLine.split(";");

                tempScores[i] = new HighScore();

                tempScores[i].name = temp[0];
                tempScores[i].score = Integer.parseInt(temp[1]);

                i++;
            }

            scores = new HighScore[i];
            for (int a = 0; a < i; a++) {
                scores[a] = new HighScore();
                scores[a] = tempScores[a];
            }

            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }

        Arrays.sort(scores);
        
        return scores;
    }
}
