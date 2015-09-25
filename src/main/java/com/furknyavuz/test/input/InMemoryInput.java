package com.furknyavuz.test.input;

import com.furknyavuz.test.cube.Piece;

/**
 * In memory input class
 * Created by furkan on 08.09.2015.
 */
public final class InMemoryInput implements Input{

    private Piece[] pieces = new Piece[6];

    public InMemoryInput() {

        boolean[][] rows1 = {{false, false, true, false, false}, {false, true, true, true, false}, {true, true, true, true, true},
                {false, true, true, true, false}, {false, false, true, false, false}};

        boolean[][] rows2 = {{true, false, true, false, true}, {true, true, true, true, true}, {false, true, true, true, false},
                {true, true, true, true, true}, {true, false, true, false, true}};

        boolean[][] rows3 = {{false, false, true, false, false}, {false, true, true, true, true}, {true, true, true, true, false},
                {false, true, true, true, true}, {false, false, true, false, false}};

        boolean[][] rows4 = {{false, true, false, true, false}, {true, true, true, true, false}, {false, true, true, true, true},
                {true, true, true, true, false}, {true, true, false, true, false}};

        boolean[][] rows5 = {{false, true, false, true, false}, {true, true, true, true, true}, {false, true, true, true, false},
                {true, true, true, true, true}, {true, false, true, false, false}};

        boolean[][] rows6 = {{false, true, false, true, false}, {false, true, true, true, true}, {true, true, true, true, false},
                {false, true, true, true, true}, {true, true, false, true, true}};

        pieces[0] = new Piece(rows1);
        pieces[1] = new Piece(rows2);
        pieces[2] = new Piece(rows3);
        pieces[3] = new Piece(rows4);
        pieces[4] = new Piece(rows5);
        pieces[5] = new Piece(rows6);
    }

    public Piece[] getPieces() {
        return pieces;
    }
}
