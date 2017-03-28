package edu.utep.cs.cs4330.puzzle;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Board board;
    private BoardView boardView;
    private Button newGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView numMoves = (TextView) findViewById(R.id.moves);

        // Button Reference
        newGameButton = (Button) findViewById(R.id.newButton);
        newGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View newGameButton) {
                Board.count = 0;
                board.setGrid();
                board.setBoard();
                numMoves.setText(String.format("Number of Movements: %s", String.valueOf(Board.count)));
            }
        });

        board = new Board(4);
        board.setBoard();
        boardView = (BoardView) findViewById(R.id.boardView);
        boardView.setBoard(board);
        boardView.setxyValues();
        boardView.addBoardTouchListener(new BoardView.BoardTouchListener() {
            @Override
            public void onTouch(int x, int y) {
                board.setTouch(x, y);
                board.updateBoard();
                numMoves.setText(String.format("Number of Movements: %s", String.valueOf(Board.count)));
                Point p = new Point();
                p.x = x;
                p.y = y;
                BoardView.xyValues.add(p);

                toast(String.format("Touched: %d, %d", x, y));
            }
        });
    }

    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
