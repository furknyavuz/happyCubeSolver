package com.furknyavuz.test;

import com.furknyavuz.test.algorithm.BruteForceSolver;
import com.furknyavuz.test.input.InMemoryInput;
import com.furknyavuz.test.input.Input;
import com.furknyavuz.test.algorithm.Solver;

import java.io.*;

/**
 * Happy Cube Puzzle
 * Created by furkan on 06.09.2015.
 */
public class HappyCubePuzzle {

    public static void main( String[] args ) {

        try {
            // Create input object
            Input input = new InMemoryInput();

            // Initialize solver with input
            Solver solver = new BruteForceSolver(input.getPieces());

            // Solve the problem
            String result = solver.execute();

            // Print the result
            printToFile(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printToFile(String input) throws IOException {
        File file = new File("result.txt");
        file.createNewFile();
        PrintWriter out = new PrintWriter(file);
        String newInput = input.replaceAll("\n", System.lineSeparator());
        out.println(newInput);
        out.close();
    }
}
