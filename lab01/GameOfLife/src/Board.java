import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

/**
 * Board with Points that may be expanded (with automatic change of cell
 * number) with mouse event listener
 */

public class Board extends JComponent implements MouseInputListener, ComponentListener {
    private static final long serialVersionUID = 1L;
    private Point[][] points;
    private int size = 14;
    private String rule = "default";


    public Board(int length, int height) {
        addMouseListener(this);
        addComponentListener(this);
        addMouseMotionListener(this);
        setBackground(Color.WHITE);
        setOpaque(true);
    }

    public void setRule(String rule){
        this.rule = rule;
    }

    // single iteration
    public void iteration() {
        for (int x = 0; x < points.length; ++x)
            for (int y = 0; y < points[x].length; ++y)
                points[x][y].calculateNewState(this.rule);

        for (int x = 0; x < points.length; ++x)
            for (int y = 0; y < points[x].length; ++y)
                points[x][y].changeState();
        this.repaint();
    }

    // clearing board
    public void clear() {
        for (int x = 0; x < points.length; ++x)
            for (int y = 0; y < points[x].length; ++y) {
                points[x][y].setState(0);
            }
        this.repaint();
    }

    private int[] calculateNewPoints(int x, int y, int i, int j) {
        int new_x = x + i;
        int new_y = y + j;
        if (new_x < 0) {
            new_x = points.length - 1;
        }
        if (new_x > points.length - 1) {
            new_x = 0;
        }
        if (new_y < 0) {
            new_y = points[x].length - 1;
        }
        if (new_y > points[x].length - 1) {
            new_y = 0;
        }

        return new int[]{new_x, new_y};

    }

    private void generateNeighborhood(int[] vectors, int x, int y) {
        for (int i : vectors) {
            for (int j : vectors) {
                int[] new_points = calculateNewPoints(x, y, i, j);
                if (i != 0 || j != 0) {
                    points[x][y].addNeighbor(points[new_points[0]][new_points[1]]);
                }
            }
        }
    }

    private void initialize(int length, int height) {
        points = new Point[length][height];
        int[] vectors = {-1, 0, 1};

        for (int x = 0; x < points.length; ++x)
            for (int y = 0; y < points[x].length; ++y)
                points[x][y] = new Point();

        for (int x = 0; x < points.length; ++x) {
            for (int y = 0; y < points[x].length; ++y) {
                generateNeighborhood(vectors, x, y);
            }
        }
    }


    //paint background and separators between cells
    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        g.setColor(Color.GRAY);
        drawNetting(g, size);
    }

    // draws the background netting
    private void drawNetting(Graphics g, int gridSpace) {
        Insets insets = getInsets();
        int firstX = insets.left;
        int firstY = insets.top;
        int lastX = this.getWidth() - insets.right;
        int lastY = this.getHeight() - insets.bottom;

        int x = firstX;
        while (x < lastX) {
            g.drawLine(x, firstY, x, lastY);
            x += gridSpace;
        }

        int y = firstY;
        while (y < lastY) {
            g.drawLine(firstX, y, lastX, y);
            y += gridSpace;
        }

        for (x = 0; x < points.length; ++x) {
            for (y = 0; y < points[x].length; ++y) {
                if (points[x][y].getState() != 0) {
                    switch (points[x][y].getState()) {
                        case 1:
                            g.setColor(new Color(0x0000ff));
                            break;
                        case 2:
                            g.setColor(new Color(0x00ff00));
                            break;
                        case 3:
                            g.setColor(new Color(0xff0000));
                            break;
                        case 4:
                            g.setColor(new Color(0x000000));
                            break;
                        case 5:
                            g.setColor(new Color(0x444444));
                            break;
                        case 6:
                            g.setColor(new Color(0xffffff));
                            break;
                    }
                    g.fillRect((x * size) + 1, (y * size) + 1, (size - 1), (size - 1));
                }
            }
        }

    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / size;
        int y = e.getY() / size;
        if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
            points[x][y].clicked();
            this.repaint();
        }
    }

    public void componentResized(ComponentEvent e) {
        int dlugosc = (this.getWidth() / size) + 1;
        int wysokosc = (this.getHeight() / size) + 1;
        initialize(dlugosc, wysokosc);
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX() / size;
        int y = e.getY() / size;
        if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
            points[x][y].setState(1);
            this.repaint();
        }
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

}
