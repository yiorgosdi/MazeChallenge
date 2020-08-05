/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package maze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import static maze.FileReaderMaze.getPattern;

/**
 *
 * @author Yiorgos
 */
public class View extends JFrame {

    /* 
      Values: 
      0 = not-visited node, white  
      1 = wall/blocked, black 
      2 = visited node,  
      9 = target node,  
    
     starting position at (1, 1) 
     ending defined as 9   
     */
    
    String userDir = System.getProperty("user.home");
    File filename = new File(userDir + "/Desktop/MAZE_INPUT.txt");
    int[][] maze = getPattern(filename);

    private final List<Integer> path = new ArrayList<>(); // List<Integer> to be passed as parameter in searchPath() method. 
    private int pathIndex;

    public View() throws FileNotFoundException, IOException {
        setTitle("MAZE GAME! Left & Right Arrow Keys Move The Ball 🎱");
        setSize(490, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // checking the first and last item of list, starting position is (1, 1)
        // SearchPath.searchPath(maze, WIDTH, WIDTH, path); 
        SearchPath.searchPath(maze, 1, 1, path); 
        pathIndex = path.size() - 2;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.translate(50, 50);

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Color color;
                switch (maze[row][col]) {
                    case 1:
                        color = Color.BLACK;
                        break;
                    case 9:
                        color = Color.RED;
                        break;
                    default:
                        color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);
            }
        }

        // the path list, the green route.   
        for (int p = 0; p < path.size(); p += 2) {
            int pathX = path.get(p);
            int pathY = path.get(p + 1);
            g.setColor(Color.GREEN);
            g.fillRect(pathX * 30, pathY * 30, 30, 30);
        }

        // the ball on path 
        int pathX = path.get(pathIndex);
        int pathY = path.get(pathIndex + 1);
        g.setColor(Color.RED);
        g.fillOval(pathX * 30, pathY * 30, 30, 30);

    }

    @Override
    protected void processKeyEvent(KeyEvent ke) {
        if (ke.getID() != KeyEvent.KEY_PRESSED) {
            return;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            pathIndex -= 2;
            if (pathIndex < 0) {
                pathIndex = 0;
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            pathIndex += 2;
            if (pathIndex > path.size() - 2) {
                pathIndex = path.size() - 2;
            }
        }
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new RunnableImpl());
    }

    private static class RunnableImpl implements Runnable {

        public RunnableImpl() {
        }

        @Override
        public void run() {
            try {
                new View().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
