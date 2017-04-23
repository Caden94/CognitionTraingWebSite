package com.caden.cognitionTraining2.dip;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadImagDIPService implements DIPService {
	
	private final Path tempLocaiton;
	private final Path rootLocation;

	@Autowired
	public UploadImagDIPService(DIPServiceProperties dipServiceProperties) {
		this.rootLocation = Paths.get(dipServiceProperties.getRootLocation());
		this.tempLocaiton = Paths.get(dipServiceProperties.getTempLocation());
	}

	@Override
	public void process(int scaledWidth, int scaledHeight, String filename) {
		// TODO Auto-generated method stub
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(tempLocaiton + "/" + filename));
			System.out.println("======== Read ========");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Resize
		System.out.println("======== Start Resize ========");
		BufferedImage rImage = resizeImage(image, scaledWidth, scaledHeight);
		System.out.println("======== Finish Resize ========");

		String dpath = generatePath(rootLocation.toString(), filename);

		// Write
		System.out.println("======== Start Write ========");
		writeImage(rImage, dpath);
		System.out.println("======== Finish Write ========");
	}

	private String generatePath(String sPath, String filename) {
		// TODO Auto-generated method stub
		return sPath + "/" + filename;
	}

	private void writeImage(BufferedImage image, String path) {
		// TODO Auto-generated method stub
		if (image == null) return;
		try {
//			System.out.println("======== Write " + path + " ========");
			ImageIO.write(image, "jpg", new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// generate destination paths
	private static String[] generatePaths(String sPath, int n) {
		// TODO Auto-generated method stub
		String[] paths = new String[n * n];
		for (int i = 0; i < n * n; i++) {
			paths[i] = sPath + "/" + (i + 1) + ".jpg";
		}
		return paths;
	}

	// resize image
	private static BufferedImage resizeImage(BufferedImage image, int width, int height) {
		if (image == null) {
			System.err.println("======== Image not Found ========");
			return image;
		}
		BufferedImage rImage = new BufferedImage(width, height, 1);
		Graphics2D graphics2d = rImage.createGraphics();
		graphics2d.drawImage(image, 0, 0, width, height, null);
		graphics2d.dispose();
		return rImage;
	}

	// crop images
	private static List<BufferedImage> cropImage(BufferedImage image, int n) {
		List<BufferedImage> results = new ArrayList<>();
		if (image == null) {
			System.err.println("======== Image not Found ========");
			return results;
		}
		int width = image.getWidth() / n;
		int heigth = image.getHeight() / n;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				BufferedImage cImage = image.getSubimage(width * (j - 1), heigth * (i - 1), width, heigth);
				results.add(cImage);
			}
		}
		return results;
	}

	// write images
	private static void writeImages(List<BufferedImage> images, String[] paths) {
		if (images == null || images.size() == 0)
			return;
		for (int i = 0; i < images.size(); i++) {
			try {
				System.out.println("======== Write " + (i + 1) + " ========");
				ImageIO.write(images.get(i), "jpg", new File(paths[i]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
