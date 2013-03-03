package com.checkers;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
// Ex. "Field.java" class
public class Eyes {
	public static final int startX = 28;
	public static final int startY = 53;
	// private static final int endX = 412;
	// private static final int endY = 437;

	private static final int screenWeight = 1280;
	private static final int screenHight = 800;
	public static final int plateSize = 48;

	private static String[][] desk = new String[8][8];

	private Map<String, BufferedImage> database = new HashMap<String, BufferedImage>();
	public final static String[] getNameByNumber = { "dark", "white", "pink",
			"pink_q", "yellow", "yellow_q" };

	Eyes() throws IOException, AWTException {
		database.put("dark", ImageIO.read(new File("base//dark.png")));
		database.put("white", ImageIO.read(new File("base//white.png")));
		database.put("pink", ImageIO.read(new File("base//pink.png")));
		database.put("pink_q", ImageIO.read(new File("base//pink_q.png")));
		database.put("yellow", ImageIO.read(new File("base//yellow.png")));
		database.put("yellow_q", ImageIO.read(new File("base//yellow_q.png")));
	}

	private BufferedImage captureScreen() throws Exception {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		// ImageIO.write(image, "png", new File("1.png"));
		return image;
	}

	private BufferedImage cutPlate(BufferedImage image, int x, int y)
			throws FileNotFoundException, IOException {
		final BufferedImage dst = new BufferedImage(plateSize, plateSize,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = dst.createGraphics();

		g.drawImage(image, x, y, screenWeight, screenHight, null);
		g.dispose();
		// ImageIO.write(dst, "PNG", new FileOutputStream("plate.png"));
		return dst;

	}

	private String getPlateState(BufferedImage digit) {
		long[] percent = new long[6];
		for (int k = 0; k < percent.length; k++) {
			for (int i = 0; i < 48; i++) {
				for (int j = 0; j < 48; j++) {
					percent[k] += Math.abs(digit.getRGB(j, i)
							- database.get(getNameByNumber[k]).getRGB(j, i));
				}
			}
		}
		return getNameByNumber[min(percent)];
	}

	private int min(long[] array) {
		long minValue = array[0];
		int minIndex = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < minValue) {
				minValue = array[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	private BufferedImage cutPlateByIndex(BufferedImage screen, int i, int j)
			throws FileNotFoundException, IOException {
		return cutPlate(screen, -startX - 1 - (plateSize) * i, -startY - 1
				- (plateSize) * j);
	}

	public String[][] getCurrentField() throws FileNotFoundException,
			IOException, Exception {
		Thread.sleep(100);
		BufferedImage screen = captureScreen();

		for (int i = 0; i < desk.length; i++) {
			for (int j = 0; j < desk[0].length; j++) {
				desk[i][j] = getPlateState(cutPlateByIndex(screen, i, j));
			}
		}
		return desk;
	}

	public static void main(String[] args) throws Exception {
		Thread.sleep(5000);
		Eyes mc = new Eyes();
		mc.getCurrentField();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(desk[j][i] + "\t");
			}
			System.out.println();
		}

	}

}
