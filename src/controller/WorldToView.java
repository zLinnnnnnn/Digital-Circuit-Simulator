package controller;

import drawingAdapter.WorldToViewI;

public class WorldToView implements WorldToViewI {
	
	double world_x,world_y;
	double view_x,view_y;
	
	/** Convert a world x coordinate to a view x coordinate.
	 * 
	 * @param world_x
	 * @return
	 */
	public double convertX(double world_x) {
		view_x = world_x;
		return view_x;
	}

	/** Convert a world y coordinate to a view y  coordinate.
	 * 
	 * @param world_y
	 * @return
	 */
	public double convertY(double world_y) {
		view_y = world_y;
		return view_y;
	}
	/** Convert a view x coordinate to a world x coordinate.
	 * 
	 * @param view_x
	 * @return
	 */
	public double invertX(double view_x) {
		world_x = view_x;
		return world_x;
	}
	
	/** Convert a view y coordinate to a world y coordinate.
	 * 
	 * @param view_y
	 * @return
	 */
	public double invertY(double view_y) {
		world_y = view_y;
		return view_y;
	}

}
