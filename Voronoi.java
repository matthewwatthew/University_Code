//Matthew Jordan (mrj3dd)

import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.image.DataBufferInt;

public class Voronoi{	
	private static Point[] tenThousand;
	private static ColorPoint[] colors;
	private static BufferedImage image;
	private static int numPoints;
	
	public static void main(String[] args) {
		numPoints = 3000;
		File originalPicture = new File("Images\\eric.jpg");
		BlackNWhite(originalPicture);
		File blackAndWhitePicture = new File("Images\\voronoi_step1.jpg");
		int height = heightGenerator(blackAndWhitePicture);
		int width = widthGenerator(blackAndWhitePicture);
		Point[] blackPoints = blackPoints(blackAndWhitePicture);
		List<Point> lister = Arrays.asList(blackPoints);
		Collections.shuffle(lister);
		lister.toArray(blackPoints);
		tenThousand = new Point[numPoints];
		for(int i = 0; i<numPoints; i++) {
			tenThousand[i] = blackPoints[i];
		}
		BufferedImage pointeillism = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphic = pointeillism.createGraphics();
		graphic.setColor(new Color(255,255,255));
		graphic.fillRect(0,0,width, height);
		graphic.setColor(new Color(0,0,0));
		
		
		for(int i = 0; i < numPoints; i++) {
			graphic.drawLine(tenThousand[i].x, tenThousand[i].y, tenThousand[i].x, tenThousand[i].y);
		}
		File fortune = new File("Images\\voronoi_step2.png");
		try {
			ImageIO.write(pointeillism, "PNG", fortune);
		} catch (IOException e) {
			System.out.println("Error Reading File: Voronoi_step2");
		}
		
		BufferedImage finalImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);	
		
		int indexC = 0;
		int[] xPoints = new int[numPoints];
		int[] yPoints = new int[numPoints];
		for(Point p: tenThousand) {
			xPoints[indexC] = p.getX();
			yPoints[indexC] = p.getY();
			indexC+=1;
		}
		colors = new ColorPoint[numPoints];
	    for (int zero = 0; zero <numPoints; zero++) {
	    	Color c = new Color(image.getRGB(tenThousand[zero].getX(), tenThousand[zero].getY()));
            colors[zero] = new ColorPoint(c.getRed(),c.getBlue(),c.getGreen());
	    }
	       int index;
	        int[] imagePixelData = ((DataBufferInt) finalImage.getRaster().getDataBuffer()).getData();
	        for (int y = 0; y < height; ++y) {
	            for (int x = 0; x < width; ++x) {
	                index = findClosestPoint(new Point(x, y));
	                imagePixelData[y * width + x] = (colors[index].getR() <<16) | (colors[index].getB() <<8) | colors[index].getG();
	        
	            }
	        }
//	        Graphics2D graphic2 = finalImage.createGraphics();
//			graphic2.setColor(new Color(0,0,0));
//			
//			for(int i = 0; i < numPoints; i++) {
//				graphic2.drawLine(tenThousand[i].x, tenThousand[i].y, tenThousand[i].x, tenThousand[i].y);
//			}		
		File fame = new File("Images\\Voronoi_step3.png");
		try {
			ImageIO.write(finalImage,"png", fame);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("DONE!");
	}

	public static void BlackNWhite(File f) {
		try {
		image = ImageIO.read(f);
		int height = image.getHeight();
		int width = image.getWidth();
		BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D image3 = image2.createGraphics();
		image3.drawImage(image, 0, 0, Color.WHITE, null);
		image3.dispose();
		File bnw = new File("Images\\voronoi_step1.jpg");
		ImageIO.write(image2, "jpg", bnw);
		}
		catch(IOException e) {
			System.out.println("File Not Found: Black and White");
		}
	}
	
	
	public static Point[] blackPoints(File f) {
		try {
			BufferedImage image = ImageIO.read(f);
			int height = image.getHeight();
			int width = image.getWidth();
			int hxw = height * width;
			Point blackPoints[] = new Point[hxw];
			int arrayPosition = 0;
			for(int x = 0; x <= width -1 ; x++) {
				for(int y = 0; y <= height -1; y++) {
					Color pixelColor = new Color(image.getRGB(x, y));
					if(pixelColor.getRed() == 0 && pixelColor.getGreen() == 0 && pixelColor.getBlue() == 0) {
						Point p = new Point(x,y);
						blackPoints[arrayPosition] = p;
						arrayPosition +=1;
					}
				}
			}
			Point returnArray[] = new Point[arrayPosition];
			for(int i = 0; i < arrayPosition; i++) {
				returnArray[i] = blackPoints[i];
			}
			return returnArray;
		}
		catch(IOException e) {
			System.out.println("Error Reading File: BlackPoints");
		}
		return null;
	}
	
	public static int heightGenerator(File f) {
		try {
			BufferedImage image = ImageIO.read(f);
			int height = image.getHeight();
			return height;
		} catch (IOException e) {
			System.out.println("WRONG");
			e.printStackTrace();
		}
		return -numPoints;
	}
	
	public static int widthGenerator(File f) {
		try {
			BufferedImage image = ImageIO.read(f);
			int width = image.getWidth();
			return width;
		} catch (IOException e) {
			System.out.println("WRONG");
			e.printStackTrace();
		}
		return -numPoints;
	}
	
		
	public static double distancePoints(Point point1, Point point2) {
		return Math.abs(point1.getX() - point2.getX()) + Math.abs(point1.getY() - point2.getY());
	}
	
	private static int findClosestPoint(Point point) {
		   int index = 0;
	        double minDistance = distancePoints(point, tenThousand[index]);
	        double currentDistance;
	        for (int i = 1; i < numPoints; ++i) {
	            currentDistance = distancePoints(point, tenThousand[i]);
	            if (currentDistance < minDistance) {
	                minDistance = currentDistance;
	                index = i;
	            }
	        }

		return index;
	}
}