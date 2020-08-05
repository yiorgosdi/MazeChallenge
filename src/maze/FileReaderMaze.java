/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Yiorgos
 */ 
public class FileReaderMaze {

    public static int[][] getPattern(File file) throws FileNotFoundException, IOException {

        int[][] arr = new int[10][13];
        BufferedReader br = new BufferedReader(new FileReader(file));

        for (int i = 0; i < 10; i++) {
            String[] st = br.readLine().trim().split(" ");
            for (int j = 0; j < 13; j++) {
                arr[i][j] = Integer.parseInt(st[j]);
            }
        }
        return arr;
    }

}
