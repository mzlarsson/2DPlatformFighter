package edu.chalmers.brawlbuddies.services.factories;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class ShapeFactory {
	
	/**
	 * Creates a Shape based on the given parameters.
	 * @param effectName The name of the Shape.
	 * @param parameters The parameters for the Shape.
	 * @return
	 */
	public static Shape create(String shape, String parameters) {
		return create(shape, parameters, 0, 0);
	}
	
	/**
	 * Creates a Shape based on the given parameters.
	 * @param effectName The name of the Shape.
	 * @param parameters The parameters for the Shape.
	 * @param x The x-location of the created Shape.
	 * @param y The y-location of the created Shape.
	 * @return
	 */
	public static Shape create(String shape, String parameters, float x, float y) {
		if (shape.equalsIgnoreCase("rectangle")) {
			String[] params = parameters.split(",");
			if (params.length == 2) {
				Rectangle rect = new Rectangle(0, 0, Float.parseFloat(params[0])
						,Float.parseFloat(params[1]));
				rect.setCenterX(x);
				rect.setCenterY(y);
				return rect;
			} else {
				throw new IllegalArgumentException("Rectangle with: " + params.length
						+ " parameters not supported");
			}
		} else if (shape.equalsIgnoreCase("circle")) {
			String[] params = parameters.split(",");
			if (params.length == 1) {
				return new Circle(x, y, Float.parseFloat(params[0]));				
			} else {
				throw new IllegalArgumentException("Circle with: " + params.length
						+ " parameters not supported");
			}
		} else if (shape.equalsIgnoreCase("ellipse")) {
			String[] params = parameters.split(",");
			if (params.length == 2) {
				return new Ellipse(x, y, Float.parseFloat(params[0])
						,Float.parseFloat(params[1]));				
			} else {
				throw new IllegalArgumentException("Ellipse with: " + params.length
						+ " parameters not supported");
			}
		} else {
			throw new IllegalArgumentException("The shape: \"" + shape
					+ "\" is not supported");
		}
	}
}
