package CentralPoint;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by jvdwi on 16-3-2016.
 */
public class Material {
    private int id;
    private String name, sort;
    private Point2D location;
    private boolean onLocation;
    private double distance;
    private ArrayList<Integer> missionIds;

    /**
     * constructor material with id
     * @param id id
     * @param name name
     * @param sort sort
     * @param location location
     * @param onLocation onLocation
     */
    public Material(int id, String name, String sort, Point2D location, boolean onLocation) {
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.location = location;
        this.onLocation = onLocation;
        this.missionIds = new ArrayList();
    }

    /**
     * constructor material without id
     * @param name name
     * @param sort sort
     * @param location location
     * @param onLocation onLocation
     */
    public Material(String name, String sort, Point2D location, boolean onLocation) {
        this.name = name;
        this.sort = sort;
        this.location = location;
        this.onLocation = onLocation;
        this.missionIds = new ArrayList();
    }

    /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * sets the sort
     * @param sort sort
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     *
     * @return location
     */
    public Point2D getLocation() {
        return location;
    }

    /**
     * sets the location
     * @param location location
     */
    public void setLocation(Point2D location) {
        this.location = location;
    }

    /**
     * @return location as string
     */
    public String getLocationString() {
        return new BigDecimal(location.getX()).setScale(2, RoundingMode.HALF_UP).doubleValue() + "; " + new BigDecimal(location.getY()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public String getDistance() {
        return new BigDecimal(distance).setScale(2, RoundingMode.HALF_UP).doubleValue() + " km";
    }

    public void setDistance(Point2D otherLocation) {
        distance = distance(location, otherLocation, "K");
    }

    public ArrayList<Integer> getMissionIds() {
        return missionIds;
    }

    public void setMissionIds(ArrayList<Integer> missionIds) {
        this.missionIds = missionIds;
    }

    public void addMissionId(int id) {
        this.missionIds.add(id);
    }

    /**
     *
     * @return onLocation
     */
    public boolean isOnLocation() {
        return onLocation;
    }

    /**
     * sets the onLocation
     * @param onLocation onLocation
     */
    public void setOnLocation(boolean onLocation) {
        this.onLocation = onLocation;
    }

    private double distance(Point2D loc1, Point2D loc2, String unit) {
        double theta = loc1.getY() - loc2.getY();
        double dist = Math.sin(deg2rad(loc1.getX())) * Math.sin(deg2rad(loc2.getX())) + Math.cos(deg2rad(loc1.getX())) * Math.cos(deg2rad(loc2.getX())) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
