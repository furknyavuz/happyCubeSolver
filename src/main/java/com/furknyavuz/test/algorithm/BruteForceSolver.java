package com.furknyavuz.test.algorithm;

import com.furknyavuz.test.cube.Piece;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by furkan on 06.09.2015.
 *
 * Brute Force Happy Cube solver
 *   Outline
 * - First piece is always put into the Front position
 * - For each permutation of placing rest pieces (120 permutations)
 *   - For each rotations and flipped rotations of pieces (32.768 case)
 *      - Check edge and corner collisions
 *      - If collision not exists set the result
 */
public class BruteForceSolver extends AbstractSolver {

    private Piece[] inputs;

    public BruteForceSolver(Piece[] inputs) {
        this.inputs = inputs;
    }

    public String solve() throws Exception {
        if(inputs == null || inputs.length != 6) {
            throw new Exception("Number of pieces is not correct!");
        }

        // For each permutation of placing pieces
        List<List<Integer>> permutations = permute(new int[]{1, 2, 3, 4, 5});

        // Create thread pool
        ExecutorService pool = Executors.newFixedThreadPool(permutations.size());

        // Create future list
        List<Future<String>> futures = new ArrayList<Future<String>>();

        // Call IsValidPositioned for each permutation
        for (int i = 0; i < permutations.size(); i++) {
            Piece[] result = new Piece[6];

            // First piece is fixed
            result[0] = new Piece(inputs[0]);
            for (int j = 0; j < 5; j++) {
                result[j + 1] = new Piece(inputs[permutations.get(i).get(j)]);
            }

            Future<String> future = pool.submit(new IsValidPositioned(new ArrayList<Piece>(Arrays.asList(result))));
            futures.add(future);
        }

        // Get response values of futures
        for(Future<String> future : futures) {
            String result = future.get();
            if(result != null && !result.isEmpty()) {
                pool.shutdown();
                return result;
            }
        }

        pool.shutdown();
        return "No results found.";
    }

    /**
     * Find all permutations of input numbers
     * @param numbers
     * @return list of all permutations
     */
    public List<List<Integer>> permute(int[] numbers) {
        List<List<Integer>> permutations = new ArrayList<List<Integer>>();
        permutations.add(new ArrayList<Integer>());

        for ( int i = 0; i < numbers.length; i++ ) {
            List<List<Integer>> current = new ArrayList<List<Integer>>();
            for ( List<Integer> permutation : permutations ) {
                for ( int j = 0, n = permutation.size() + 1; j < n; j++ ) {
                    List<Integer> temp = new ArrayList<Integer>(permutation);
                    temp.add(j, numbers[i]);
                    current.add(temp);
                }
            }
            permutations = new ArrayList<List<Integer>>(current);
        }
        return permutations;
    }
}
