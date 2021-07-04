import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScorePitBottom extends JPanel {
    private final static String IMG_FILE = "files/bottomScore.jpg";
    private static BufferedImage img;
    private int numberOfMarbles;
    private LinkedList<Marble> listOfMarbles;

    public ScorePitBottom() {

        setPreferredSize(new Dimension(100, 100));

        // image creation
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        listOfMarbles = new LinkedList<Marble>();
    }

    public void setNumberOfMarbles(int n) {
        numberOfMarbles = n;
    }

    // generate random coordinate in panel to draw marbles
    public int randomXCoord() {
        return (int) (Math.random() * (60 - 10 + 1) + 10);
    }

    public int randomYCoord() {
        return (int) (Math.random() * (60 - 0 + 1) + 0);
    }

    public void resetMarbleList() {
        numberOfMarbles = 0;
        listOfMarbles = new LinkedList<Marble>();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 100, 100, null);

        for (int j = numberOfMarbles; j < listOfMarbles.size(); j++) {
            listOfMarbles.remove();
        }

        int i = 0;
        while (i < listOfMarbles.size()) {
            listOfMarbles.get(i).draw(g);
            i++;
        }

        while (i < numberOfMarbles) {
            int x = randomXCoord();
            int y = randomYCoord();
            Marble newMarble = new Marble(0, 0, x, y, 800, 300, Color.BLUE);
            listOfMarbles.add(newMarble);
            newMarble.draw(g);
            i++;
        }
    }
}
