package edu.chalmers.brawlbuddies.services.factories;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class ShapeFactory {
	
	public static Shape create(String shape, String parameters) {
		return create(shape, parameters, 0, 0);
	}
	
	public static Shape create(String shape, String parameters, float x, float y) {
		if (shape.equalsIgnoreCase("rectangle")) {
			String[] params = parameters.split(",");
			if (params.length == 2) {
				return new Rectangle(x, y, Float.parseFloat(params[0])
						,Float.parseFloat(params[1]));				
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
		}
		return null;
	}
}
