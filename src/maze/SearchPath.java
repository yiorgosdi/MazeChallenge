/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.util.List;

/**
 *
 * @author Yiorgos
 */
public class SearchPath {

    /* If a path is found returns true and is filled green 
    * like this: {xn, xy, ..., x2, y2, x1, y1}
    * so the order is inverted and x and y are the start searching position.  
    */
    public static boolean searchPath(int[][] maze, int x, int y,
            List<Integer> path) {

        // finally, checking if the target node is reached.
        if (maze[y][x] == 9) {
            path.add(x);
            path.add(y);
            return true;
        }

        // when (x and y) is a non-visited node (0), 
        // then is marked as visited(2).   
        if (maze[y][x] == 0) {
            maze[y][x] = 2;

            // then visit all neighbor nodes recursively. 
            // if path was found is filled with current position. 
            int dx = -1;
            int dy = 0;
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }

            dx = 1;
            dy = 0;
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }

            dx = 0;
            dy = -1;
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }

            dx = 0;
            dy = 1;
            if (searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }
        }
        return false;
    }
}
