package com.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class FileUploader {
	/**
	 * Take 2 uploaded files and return an image based on them
	 * 
	 * @param uploadImage
	 *            The uploaded image
	 * @param uploadFile
	 *            The uploaded file
	 * @param color
	 *            The selected color
	 * @return A mangled image based on the 2 uploaded files
	 */
	public BufferedImage uploadFiles(BufferedImage uploadImage) {
		System.out.println("upload ok");
		uploadImage = scaleToSize(uploadImage);
		return uploadImage;
	}

	/**
	 * Voodoo to scale the image to 200x200
	 * 
	 * @param uploadImage
	 *            The image to work on
	 * @return The altered image
	 */
	private BufferedImage scaleToSize(BufferedImage uploadImage) {
		double width = 200D;
		double height = 200D;
		AffineTransform atx = new AffineTransform();
		atx.scale(width / uploadImage.getWidth(), height / uploadImage.getHeight());
		AffineTransformOp afop = new AffineTransformOp(atx, AffineTransformOp.TYPE_BILINEAR);
		uploadImage = afop.filter(uploadImage, null);
		return uploadImage;
	}
}
