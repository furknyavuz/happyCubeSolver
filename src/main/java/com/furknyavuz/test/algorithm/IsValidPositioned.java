package com.furknyavuz.test.algorithm;

import com.furknyavuz.test.cube.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Callable Class that checking the position of inputs are valid or not
 * Created by furkan on 10.09.2015.
 */
public class IsValidPositioned implements Callable {

    private final int CUBE_SIZE = 5;

    // 2D array for printing the unfolded result
    private boolean[][] printingArea = new boolean[CUBE_SIZE * 4][CUBE_SIZE * 3];

    // X and Y positions of pieces on printing area
    private int[][]printingPositions = {{CUBE_SIZE, CUBE_SIZE}, {0, CUBE_SIZE}, {CUBE_SIZE, 0}, {CUBE_SIZE, CUBE_SIZE * 2}, {CUBE_SIZE * 2, CUBE_SIZE}, {CUBE_SIZE * 3, CUBE_SIZE}};

    private Piece[] result = new Piece[6];
    private ArrayList<Piece> pieces;

    public IsValidPositioned(ArrayList<Piece> result) {
        this.pieces = result;
    }

    public Object call() throws Exception {
        if(isValidPositioned(new ArrayList<Piece>(pieces.subList(0, 1)), new ArrayList<Piece>(pieces.subList(1, pieces.size())))) {
            return print();
        }
        return "";
    }

    /**
     * Recursive collision checking function
     * - Calls itself for each rotation of selected piece
     * - Checks edge and Corner collision at the base case
     * @param placedPieces
     * @param unplacedPieces
     * @return true if no collision exists else false
     */
    private boolean isValidPositioned(List<Piece> placedPieces, List<Piece> unplacedPieces) {
        // Base case
        if(unplacedPieces.size() == 0) {
            if(checkEdgeCollisions(placedPieces) || checkCornerCollisions(placedPieces)) {
                return false;
            }

            // If no collision exists set result
            for (int i = 0; i < placedPieces.size(); i++) {
                this.result[i] = placedPieces.get(i);
            }
            return true;
        }

        // Recursive case
        Piece rotatingPiece = unplacedPieces.get(0);
        for (int i = 0; i < 8; i++) {
            if(i == 4) {
                rotatingPiece.flip();
            }
            rotatingPiece.rotateClockwise();
            List<Piece> subPieces = new ArrayList<Piece>(placedPieces);
            subPieces.add(new Piece(rotatingPiece.getRows()));
            if(isValidPositioned(subPieces, new ArrayList<Piece>(unplacedPieces.subList(1, unplacedPieces.size())))) {
                return true;
            }
        }

        // No solution found for this permutation
        return false;
    }

    /**
     * Check collision of all corners
     * @param placedPieces
     * @return true if collision exists else false
     */
    private boolean checkCornerCollisions(List<Piece> placedPieces) {
        Piece front = placedPieces.get(PositionEnum.FRONT.ordinal());
        Piece up = placedPieces.get(PositionEnum.UP.ordinal());
        Piece left = placedPieces.get(PositionEnum.LEFT.ordinal());
        Piece right = placedPieces.get(PositionEnum.RIGHT.ordinal());
        Piece down = placedPieces.get(PositionEnum.DOWN.ordinal());
        Piece back = placedPieces.get(PositionEnum.BACK.ordinal());

        // 8 Corners exists
        if(!validCorner(front.upLeftCorner(), left.upRightCorner(), up.downLeftCorner()) ||
                !validCorner(front.upRightCorner(), right.upLeftCorner(), up.downRightCorner()) ||
                !validCorner(front.downLeftCorner(), left.downRightCorner(), down.upLeftCorner()) ||
                !validCorner(front.downRightCorner(), right.downLeftCorner(), down.upRightCorner()) ||
                !validCorner(back.upLeftCorner(), left.downLeftCorner(), down.downLeftCorner()) ||
                !validCorner(back.upRightCorner(), right.downRightCorner(), down.downRightCorner()) ||
                !validCorner(back.downLeftCorner(), left.upLeftCorner(), up.upLeftCorner()) ||
                !validCorner(back.downRightCorner(), right.upRightCorner(), up.upRightCorner())) {
            return true;
        }

        return false;
    }

    /**
     * Check validity of corner
     * @param a
     * @param b
     * @param c
     * @return true if only one of the input is filled else false
     */
    private boolean validCorner(boolean a, boolean b, boolean c) {
        int numberOfTrue = 0;
        if(a) { numberOfTrue++; }
        if(b) { numberOfTrue++; }
        if(c) { numberOfTrue++; }
        if(numberOfTrue != 1) {
            return false;
        }
        return true;
    }

    /**
     * Check collision of all edges
     * @param placedPieces
     * @return true if collision exists else false
     */
    private boolean checkEdgeCollisions(List<Piece> placedPieces) {
        Piece front = placedPieces.get(PositionEnum.FRONT.ordinal());
        Piece up = placedPieces.get(PositionEnum.UP.ordinal());
        Piece left = placedPieces.get(PositionEnum.LEFT.ordinal());
        Piece right = placedPieces.get(PositionEnum.RIGHT.ordinal());
        Piece down = placedPieces.get(PositionEnum.DOWN.ordinal());
        Piece back = placedPieces.get(PositionEnum.BACK.ordinal());

        // 12 Edges exists
        if(edgeCollisionExists(front.upEdge(), up.downEdge()) ||
                edgeCollisionExists(front.downEdge(), down.upEdge()) ||
                edgeCollisionExists(down.downEdge(), back.upEdge()) ||
                edgeCollisionExists(back.downEdge(), up.upEdge()) ||
                edgeCollisionExists(left.rightEdge(), front.leftEdge()) ||
                edgeCollisionExists(front.rightEdge(), right.leftEdge()) ||
                edgeCollisionExists(back.leftEdgeReverse(), left.leftEdge()) ||
                edgeCollisionExists(back.rightEdgeReverse(), right.rightEdge()) ||
                edgeCollisionExists(left.upEdge(), up.leftEdge()) ||
                edgeCollisionExists(right.upEdge(), up.rightEdgeReverse()) ||
                edgeCollisionExists(left.downEdge(), down.leftEdgeReverse()) ||
                edgeCollisionExists(right.downEdge(), down.rightEdge())) {
            return true;
        }
        return false;
    }

    /**
     * Check collision of 2 edges
     * @param edge1
     * @param edge2
     * @return true if collision exists else false
     */
    private boolean edgeCollisionExists(boolean[] edge1, boolean[] edge2) {
        for (int i = 0; i < 3; i++) {
            // 1-1 or 0-0 found
            if(!edge1[i] ^ edge2[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Print result to string
     * @return
     */
    private String print() {

        for (int i = 0; i < 6 ; i++) {
            printPiece(result[i], printingPositions[i]);
        }

        String result = "";

        for (int i = 0; i < printingArea.length; i++) {
            for (int j = 0; j < printingArea[i].length; j++) {
                result += (printingArea[i][j]) ? "O" : " ";
            }
            result += "\n";
        }
        return result;
    }

    /**
     * Print piece to given position
     * @param piece
     * @param position
     */
    private void printPiece(Piece piece, int[] position) {
        if(piece == null) {
            return;
        }

        for (int i = 0; i < piece.getRows().length; i++) {
            for (int j = 0; j < piece.getRows()[i].length; j++) {
                printingArea[position[0] + i][position[1] + j] = piece.getRows()[i][j];
            }
        }
    }
}
