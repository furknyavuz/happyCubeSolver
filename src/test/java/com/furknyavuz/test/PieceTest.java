package com.furknyavuz.test;

import com.furknyavuz.test.cube.Piece;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for Piece class
 * Created by furkan on 08.09.2015.
 */
public class PieceTest {
    private boolean[][] testRows = {{false, true, false, true, false}, {true, true, true, true, false}, {false, true, true, true, true},
            {true, true, true, true, false}, {true, true, false, true, false}};

    @Test
    public void testUpLeftCorner() {
        Piece testPiece = new Piece(testRows);
        assertEquals(testRows[0][0], testPiece.upLeftCorner());
    }
    @Test
    public void testUpRightCorner() {
        Piece testPiece = new Piece(testRows);
        assertEquals(testRows[0][4], testPiece.upRightCorner());
    }
    @Test
    public void testDownLeftCorner() {
        Piece testPiece = new Piece(testRows);
        assertEquals(testRows[4][0], testPiece.downLeftCorner());
    }
    @Test
    public void testDownRightCorner() {
        Piece testPiece = new Piece(testRows);
        assertEquals(testRows[4][4], testPiece.downRightCorner());
    }

    @Test
    public void testUpEdge() {
        Piece testPiece = new Piece(testRows);
        assertEquals(testRows[0][1], testPiece.upEdge()[0]);
        assertEquals(testRows[0][2], testPiece.upEdge()[1]);
        assertEquals(testRows[0][3], testPiece.upEdge()[2]);
    }
    @Test
    public void testDownEdge() {
        Piece testPiece = new Piece(testRows);
        assertEquals(testRows[4][1], testPiece.downEdge()[0]);
        assertEquals(testRows[4][2], testPiece.downEdge()[1]);
        assertEquals(testRows[4][3], testPiece.downEdge()[2]);
    }
    @Test
    public void testLeftEdge() {
        Piece testPiece = new Piece(testRows);
        assertEquals(testRows[1][0], testPiece.leftEdge()[0]);
        assertEquals(testRows[2][0], testPiece.leftEdge()[1]);
        assertEquals(testRows[3][0], testPiece.leftEdge()[2]);
    }
    @Test
    public void testRightEdge() {
        Piece testPiece = new Piece(testRows);
        assertEquals(testRows[1][4], testPiece.rightEdge()[0]);
        assertEquals(testRows[2][4], testPiece.rightEdge()[1]);
        assertEquals(testRows[3][4], testPiece.rightEdge()[2]);
    }

    @Test
    public void testUpEdgeReverse() {
        Piece testPiece = new Piece(testRows);
        assertEquals(testRows[0][1], testPiece.upEdgeReverse()[2]);
        assertEquals(testRows[0][2], testPiece.upEdgeReverse()[1]);
        assertEquals(testRows[0][3], testPiece.upEdgeReverse()[0]);
    }
    @Test
    public void testDownEdgeReverse() {
        Piece testPiece = new Piece(testRows);
        assertEquals(testRows[4][1], testPiece.downEdgeReverse()[2]);
        assertEquals(testRows[4][2], testPiece.downEdgeReverse()[1]);
        assertEquals(testRows[4][3], testPiece.downEdgeReverse()[0]);
    }
    @Test
    public void testLeftEdgeReverse() {
        Piece testPiece = new Piece(testRows);
        assertEquals(testRows[1][0], testPiece.leftEdgeReverse()[2]);
        assertEquals(testRows[2][0], testPiece.leftEdgeReverse()[1]);
        assertEquals(testRows[3][0], testPiece.leftEdgeReverse()[0]);
    }
    @Test
    public void testRightEdgeReverse() {
        Piece testPiece = new Piece(testRows);
        assertEquals(testRows[1][4], testPiece.rightEdgeReverse()[2]);
        assertEquals(testRows[2][4], testPiece.rightEdgeReverse()[1]);
        assertEquals(testRows[3][4], testPiece.rightEdgeReverse()[0]);
    }

    @Test
    public void testRotateClockwise() {
        boolean[][] rotatedRows = {{true, true, false, true, false}, {true, true, true, true, true}, {false, true, true, true, false},
                {true, true, true, true, true}, {false, false, true, false, false}};
        Piece rotatedPiece = new Piece(rotatedRows);

        Piece testPiece = new Piece(testRows);
        testPiece.rotateClockwise();

        assertEquals(rotatedPiece, testPiece);
    }

    @Test
    public void testFlip() {
        boolean[][] flippedRows = {{true, true, false, true, false}, {true, true, true, true, false}, {false, true, true, true, true},
                {true, true, true, true, false}, {false, true, false, true, false}};
        Piece flippedPiece = new Piece(flippedRows);

        Piece testPiece = new Piece(testRows);
        testPiece.flip();

        assertEquals(flippedPiece, testPiece);
    }
}
