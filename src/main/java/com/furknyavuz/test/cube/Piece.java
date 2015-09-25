package com.furknyavuz.test.cube;

import java.util.Arrays;

/**
 * Created by furkan on 06.09.2015.
 *
 * Represents piece in 2D array
 * e.g.
 *   O
 *  OOO
 * OOOOO
 *  OOO
 *   O
 */
public class Piece {

    // Final values
    private static final int CUBE_SIZE = 5;
    private static final int EDGE_SIZE = 3;

    // Row representation
    boolean[][] rows = new boolean[CUBE_SIZE][CUBE_SIZE];

    /**
     * Constructor - copies input rows array
     * @param rows
     */
    public Piece(boolean[][] rows) {
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length; j++) {
                this.rows[i][j] = rows[i][j];
            }
        }
    }

    /**
     * Copy constructor
     * @param piece
     */
    public Piece(Piece piece) {
        for (int i = 0; i < piece.getRows().length; i++) {
            for (int j = 0; j < piece.getRows()[i].length; j++) {
                this.rows[i][j] = piece.getRows()[i][j];
            }
        }
    }

    // Corner getting functions
    public boolean upLeftCorner() {
        return rows[0][0];
    }
    public boolean upRightCorner() {
        return rows[0][CUBE_SIZE - 1];
    }
    public boolean downLeftCorner() {
        return rows[CUBE_SIZE - 1][0];
    }
    public boolean downRightCorner() {
        return rows[CUBE_SIZE - 1][CUBE_SIZE - 1];
    }

    // Edge getting functions
    public boolean[] upEdge() {
        boolean[] side = new boolean[EDGE_SIZE];
        for (int i = 1; i < CUBE_SIZE - 1; i++) {
            side[i-1] = rows[0][i];
        }
        return side;
    }
    public boolean[] rightEdge() {
        boolean[] side = new boolean[EDGE_SIZE];
        for (int i = 1; i < CUBE_SIZE - 1; i++) {
            side[i-1] = rows[i][CUBE_SIZE - 1];
        }
        return side;
    }
    public boolean[] downEdge() {
        boolean[] side = new boolean[EDGE_SIZE];
        for (int i = 1; i < CUBE_SIZE - 1; i++) {
            side[i-1] = rows[CUBE_SIZE - 1][i];
        }
        return side;
    }
    public boolean[] leftEdge() {
        boolean[] side = new boolean[EDGE_SIZE];
        for (int i = 1; i < CUBE_SIZE - 1; i++) {
            side[i-1] = rows[i][0];
        }
        return side;
    }
    public boolean[] upEdgeReverse() {
        boolean[] side = new boolean[EDGE_SIZE];
        for (int i = 1; i < CUBE_SIZE - 1; i++) {
            side[EDGE_SIZE - i] = rows[0][i];
        }
        return side;
    }
    public boolean[] rightEdgeReverse() {
        boolean[] side = new boolean[EDGE_SIZE];
        for (int i = 1; i < CUBE_SIZE - 1; i++) {
            side[EDGE_SIZE - i] = rows[i][CUBE_SIZE - 1];
        }
        return side;
    }
    public boolean[] downEdgeReverse() {
        boolean[] side = new boolean[EDGE_SIZE];
        for (int i = 1; i < CUBE_SIZE - 1; i++) {
            side[EDGE_SIZE - i] = rows[CUBE_SIZE - 1][i];
        }
        return side;
    }
    public boolean[] leftEdgeReverse() {
        boolean[] side = new boolean[EDGE_SIZE];
        for (int i = 1; i < CUBE_SIZE - 1; i++) {
            side[EDGE_SIZE - i] = rows[i][0];
        }
        return side;
    }

    /**
     * Rotate values of rows array clockwise
     */
    public void rotateClockwise() {
        boolean[][] rotated = new boolean[CUBE_SIZE][CUBE_SIZE];
        for (int i = 0; i < CUBE_SIZE; i++) {
            for (int j = 0; j < CUBE_SIZE; j++) {
                rotated[j][CUBE_SIZE-1-i] = rows[i][j];
            }
        }
        rows = rotated;
    }

    /**
     * Flip rows
     */
    public void flip() {
        for(int i = 0; i < (rows.length / 2); i++) {
            boolean[] temp = rows[i];
            rows[i] = rows[rows.length - i - 1];
            rows[rows.length - i - 1] = temp;
        }
    }

    @Override
    public String toString() {
        return "Piece{" +
                "rows=" + Arrays.toString(rows) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;

        Piece piece = (Piece) o;

        return Arrays.deepEquals(rows, piece.rows);

    }

    @Override
    public int hashCode() {
        return rows != null ? Arrays.deepHashCode(rows) : 0;
    }

    public boolean[][] getRows() {
        return rows;
    }
}
