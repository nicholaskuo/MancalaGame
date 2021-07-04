import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("serial")
public class MarblePit extends JPanel {

    private final static String IMG_FILE = "files/pit.jpg";
    private static BufferedImage img;
    private int numberOfMarbles;
    private LinkedList<Marble> listOfMarbles;
    private boolean mouseIsInBox = false;

    public MarblePit() {
        setPreferredSize(new Dimension(100, 100));

        // image creation
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseIsInBox = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseIsInBox = false;
                repaint();
            }
        });

        numberOfMarbles = 0;

        // listOfMarble creation
        listOfMarbles = new LinkedList<Marble>();
    }

    public void setNumberOfMarbles(int n) {
        numberOfMarbles = n;
        if (numberOfMarbles < listOfMarbles.size()) {
            int difference = listOfMarbles.size() - numberOfMarbles;
            for (int i = 0; i < difference; i++) {
                listOfMarbles.remove();
            }
        }
        if (listOfMarbles.size() < numberOfMarbles) {
            int difference = numberOfMarbles - listOfMarbles.size();
            for (int i = 0; i < difference; i++) {
                int x = randomXCoord();
                int y = randomYCoord();
                Marble newMarble = new Marble(0, 0, x, y, 800, 300, Color.BLUE);
                listOfMarbles.add(newMarble);
            }
        }
    }

    // generate random coordinate in panel to draw marbles
    public int randomXCoord() {
        return (int) (Math.random() * (60 - 10 + 1) + 15);
    }

    public int randomYCoord() {
        return (int) (Math.random() * (60 - 20 + 1) + 20);
    }

    public void clearMarbleList() {
        numberOfMarbles = 0;
        listOfMarbles = new LinkedList<Marble>();
    }

    public int getNumberOfMarbles() {
        return numberOfMarbles;
    }

    public LinkedList<Marble> getMarbleList() {
        LinkedList<Marble> newList = new LinkedList<Marble>();
        for (Marble e : listOfMarbles) {
            newList.add(e);
        }
        return newList;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 100, 100, null);

        for (int j = 0; j < listOfMarbles.size(); j++) {
            listOfMarbles.get(j).draw(g);
        }

        g.setColor(Color.YELLOW);
        if (mouseIsInBox) {
            g.drawLine(0, 0, 100, 0);
            g.drawLine(0, 100, 100, 100);
            g.drawLine(100, 0, 100, 100);
            g.drawLine(0, 0, 0, 100);
        }

    }
}
