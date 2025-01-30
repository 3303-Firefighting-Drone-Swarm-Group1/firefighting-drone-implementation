/**
 * Zone represents a zone in which fires may start.
 * @author Lucas Warburton, 101276823
 */

import java.awt.Point;

public class Zone {
    private Point start;
    private Point end;
    private int id;

    public Zone(int id, int startx, int starty, int endx, int endy){
        this.id = id;
        start = new Point(Math.min(startx, endx), Math.min(starty, endy));
        end = new Point(Math.max(startx, endx), Math.max(starty, endy));
    }

    public Zone(int id, Point start, Point end){
        this.id = id;
        this.start = start;
        this.end = end;
    }
    
    /**
     * Gets the x coordinate of the start of the zone.
     * @return the x coordinate
     */
    public int getStartX(){
        return (int)start.getX();
    }

    /**
     * Gets the y coordinate of the start of the zone.
     * @return the y coordinate
     */
    public int getStartY(){
        return (int)start.getY();
    }

    /**
     * Gets the x coordinate of the end of the zone.
     * @return the x coordinate
     */
    public int getEndX(){
        return (int)end.getX();
    }

    /**
     * Gets the y coordinate of the end of the zone.
     * @return the y coordinate
     */
    public int getEndY(){
        return (int)end.getY();
    }

    /**
     * Gets the id of the zone
     * @return the id
     */
    public int getID(){
        return id;
    }

    /**
     * Gets a point which is the closest point to a given set of coordinates.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the closest point
     */
    public Point getClosestPoint(int x, int y){
        int closestX, closestY;
        if (x >= getStartX() && x <= getEndX()) closestX = x;
        else if (x < getStartX()) closestX = getStartX();
        else closestX = getEndX();

        if (y >= getStartY() && y <= getEndY()) closestY = y;
        else if (y < getStartY()) closestY = getStartY();
        else closestY = getEndY();

        return new Point(closestX, closestY);
    }
}
