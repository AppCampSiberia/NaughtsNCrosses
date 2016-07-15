package ru.appcampsiberia.noughtsncrosses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = пусто, 1 = крестик, 2 = нолик
    private int[] field = new int[9];

    // кто ходит следующим (1 = крестики, 2 = нолики)
    private int next = 1;

    // кто выиграл (0, если никто)
    private int won = 0;

    private ImageView[] cells = new ImageView[6];

    private TextView infoLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoLabel = (TextView) findViewById(R.id.infoLabel);

        cells[0] = (ImageView) findViewById(R.id.cell0);
        cells[1] = (ImageView) findViewById(R.id.cell1);
        cells[2] = (ImageView) findViewById(R.id.cell2);
        cells[3] = (ImageView) findViewById(R.id.cell3);
        cells[4] = (ImageView) findViewById(R.id.cell4);
        cells[5] = (ImageView) findViewById(R.id.cell5);

        for (int i = 0; i < cells.length; i++) {
            final int idx = i;
            cells[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    makeMove(idx);
                }
            });
        }

        render();
    }

    private void makeMove(int c) {
        if (won != 0) {
            for (int i = 0; i < field.length; i++) {
                field[i] = 0;
            }
            won = 0;
            next = 1;
        }

        field[c] = next;

        if (next == 1) {
            next = 2;
        } else {
            next = 1;
        }

        checkWin();

        render();
    }

    private void checkWin() {
        if (field[0] != 0 && field[0] == field[1] && field[0] == field[2]) {
            won = field[0];
        }
        if (field[3] != 0 && field[3] == field[4] && field[3] == field[5]) {
            won = field[3];
        }
    }

    private void render() {
        if (won == 0) {
            if (next == 1) {
                infoLabel.setText("Ход крестиков");
            } else {
                infoLabel.setText("Ход ноликов");
            }
        } else if (won == 1) {
            infoLabel.setText("Выиграли крестики");
        } else if (won == 2) {
            infoLabel.setText("Выиграли нолики");
        }

        for (int i = 0; i < cells.length; i++) {
            if (field[i] == 0) {
                cells[i].setImageDrawable(null);
            } else if (field[i] == 1) {
                cells[i].setImageDrawable(getResources().getDrawable(R.drawable.x));
            } else if (field[i] == 2) {
                cells[i].setImageDrawable(getResources().getDrawable(R.drawable.o));
            }
        }
    }

}
