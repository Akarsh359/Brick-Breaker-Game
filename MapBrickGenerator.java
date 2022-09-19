import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
public class MapBrickGenerator {
public int map[][];
public int bricksWidth;
public int bricksHeight;
private Random random = new Random();
public MapBrickGenerator(int row ,int column){
map = new int[row][column];
for (int[] map1 : map) {

for (int j = 0; j < map[0].length; j++) {
map1[j] = 1;
}
}
bricksWidth = 610/column;
bricksHeight = 300/row;
}
public void draw(Graphics2D g) {
for (int i = 0; i < map.length; i++) {
for (int j = 0; j < map[0].length; j++) {
if (map[i][j] > 0) {
g.setColor(new Color(random.nextInt(128)+128, random.nextInt(128) + 128, random.nextInt(128) + 128));

g.fillRect(j * bricksWidth + 40, i * bricksHeight + 25, bricksWidth, bricksHeight);
g.setStroke(new BasicStroke(2));
g.setColor(Color.black);
g.drawRect(j * bricksWidth + 40, i * bricksHeight + 25, bricksWidth, bricksHeight);
}
}
}
}
public void setBricksValue(int value,int row,int column)
{
map[row][column] = value;
}
}