import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/* Ryan Owens, Post-APCS Data Structures, provides a sequence of methods that can be used to utilize the 
ArrayList created at birth of class */

public class TileManager {

    private ArrayList<Tile> list;

    // no argument constructor

    public TileManager() {
        list = new ArrayList<Tile>();
    }

    // adds the Tile parameter into the list

    public void addTile(Tile rect) {
        list.add(rect);
    }

    // draws all Tiles using Drawing Panel

    public void drawAll(Graphics g) {
        for (Tile t : list) {
            t.draw(g);
        }
    }

    // takes the top most tile the user selects and moves it all the way to the front of list

    public void raise(int x, int y) {
        Tile t = doesTouch(x, y);
        if (t != null) {
            list.add(t);   
        }
    }

    // takes the top most tile the user selects and moves it all the way to the back of list

    public void lower(int x, int y) {
        Tile t = doesTouch(x,y);
        if (t != null) {
            list.add(0, t);
        }   
    }

    // takes the top most tile the user selects and removes it from list

    public void delete(int x, int y) {
        doesTouch(x,y);       
    }

    // removes all tiles that are on the pixel of the user's cursor

    public void deleteAll(int x, int y) {
        Boolean b = true;
        while (b) {
            Tile t = doesTouch(x, y);
            if (t == null) {
                b = false;
            }
        }
    }

    // shuffles list order using Collections and randomly changes XY position

    public void shuffle(int width, int height) {
        Random rand = new Random();
        Collections.shuffle(list);
        for (Tile t : list) {
            t.setX(rand.nextInt(width - t.getWidth() - 1));
            t.setY(rand.nextInt(height - t.getHeight() - 1));
        } 
    }

    // checks to see if user's cursor is on a tile and removes such tile from list and returns it

    private Tile doesTouch(int x, int y) {
        for (int i = list.size() - 1; i >= 0; i--) {
            Tile t = list.get(i);
            if (x >= t.getX() && x <= t.getX() + t.getWidth() && y >= t.getY() && y <= t.getY() + t.getHeight()) {
                list.remove(t);
                return t;
            }
        }
        return null;
    }
}