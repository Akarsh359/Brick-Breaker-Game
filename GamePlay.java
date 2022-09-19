import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
private boolean play = false;
private int score = 0;
private int totalbricks = 60;
private Timer Timer;
private int delay = 8;
private int playerX = 310;

private int ballpositionX = 120;
private int ballpositionY = 400;

private int ballXdirection = -1;
private int ballYdirection = -2;
private MapBrickGenerator Map;
public GamePlay() {
Map = new MapBrickGenerator(6, 10);
addKeyListener(this);
setFocusable(true);
setFocusTraversalKeysEnabled(false);
Timer = new Timer(delay, this);
Timer.start();
}
public void paint(Graphics g) {
g.setColor(Color.black);
g.fillRect(1, 1, 692, 592);
Map.draw((Graphics2D) g);
g.setColor(Color.yellow);
g.fillRect(0, 0, 3, 592);
g.fillRect(0, 0, 692, 3);
g.fillRect(691, 0, 3, 592);
g.setColor(Color.white);
g.setFont(new Font("serif", Font.BOLD, 25));
g.drawString("" + score, 590, 30);
g.setColor(Color.yellow);
g.fillRect(playerX, 550, 100, 8);
//ball
g.setColor(Color.GREEN);
g.fillOval(ballpositionX, ballpositionY, 20, 20);
if (ballpositionY > 570) {
play = false;
ballXdirection = 0;
ballYdirection = 0;
g.setColor(Color.white);
g.setFont(new Font("serif", Font.BOLD, 30));

g.drawString(" Game Over Score: " + score, 190, 300);
g.setFont(new Font("serif", Font.BOLD, 30));
g.drawString(" Press Enter to Restart", 190, 340);
}
if(totalbricks == 0){

play = false;
ballYdirection = -2;
ballXdirection = -1;
g.setColor(Color.white);
g.setFont(new Font("serif",Font.BOLD,30));
g.drawString(" You Won !!! "+score,190,300);
g.setFont(new Font("serif", Font.BOLD, 30));
g.drawString(" Press Enter to Restart", 190, 340);

}
g.dispose();

}
@Override
public void actionPerformed(ActionEvent e) {
Timer.start();
if (play) {
if (new Rectangle(ballpositionX, ballpositionY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
ballYdirection = -ballYdirection;
}
Loop:
for (int i = 0; i < Map.map.length; i++) {
for (int j = 0; j < Map.map[0].length; j++) {
if (Map.map[i][j] > 0) {
int brickX = j * Map.bricksWidth + 40;
int brickY = i * Map.bricksHeight + 25;
int bricksWidth = Map.bricksWidth;
int bricksHeight = Map.bricksHeight;
Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
Rectangle ballrect = new Rectangle(ballpositionX, ballpositionY, 20, 20);
Rectangle brickrect = rect;
if (ballrect.intersects(brickrect)) {
Map.setBricksValue(0, i, j);
totalbricks--;
score += 5;

break Loop;
}

}

}
}

ballpositionX += ballXdirection;
ballpositionY += ballYdirection;
if (ballpositionX < 0) {
ballXdirection = -ballXdirection;
}
if (ballpositionY < 0) {
ballYdirection = -ballYdirection;
}
if (ballpositionX > 670) {
ballXdirection = -ballXdirection;
}
}
repaint();
}
@Override
public void keyTyped(KeyEvent e) {
}

@Override
public void keyReleased(KeyEvent e) {
}
@Override

public void keyPressed(KeyEvent e) {
if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
if (playerX >= 600) {
playerX = 600;
} else {
moveRight();
}
}
if (e.getKeyCode() == KeyEvent.VK_LEFT) {
if (playerX < 10) {
playerX = 10;
} else {
moveLeft();
}

}
if (e.getKeyCode() == KeyEvent.VK_ENTER) {
if (!play) {
ballpositionX = 120;
ballpositionY = 350;
ballXdirection = -1;
ballYdirection = -2;
score = 0;
playerX = 310;
totalbricks = 60;
Map = new MapBrickGenerator(6, 10);
repaint();
}
}

}
public void moveRight ()
{
play = true;
playerX += 20;
}
public void moveLeft ()
{
play = true;
playerX -= 20;
}
}