package model;

import java.util.Objects;
import java.util.Random;

public class Coordinates {
    private static final double BASE_LAT = 42.887690;
    private static final double BASE_LON = -78.879370;

    private double latitude;
    private double longitude;

    public Coordinates() {
        latitude = BASE_LAT;
        longitude = BASE_LON;
    }

    private Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates that)) return false;
        return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    public void resetCoordinates() {
        latitude = BASE_LAT;
        longitude = BASE_LON;
    }

    public Coordinates prepareRandomCoordinates() {
        double randLat = getRandom();
        double randLon = getRandom();
        return new Coordinates(latitude+randLat, longitude+randLon);
    }

    private static double getRandom() {
        Random rand = new Random();
        double result = (rand.nextInt(21) - 10) / 100.0;
        return result;
    }

    @Override
    public String toString() {
        String latitudePosition = "N";
        String longitudePosition = "W";
        if (latitude <= 0) {
            latitudePosition = "S";
        }
        if (latitude > 0) {
            longitudePosition = "E";
        }
        return latitudePosition + " " + Math.abs(latitude) + " " + longitudePosition + " " + Math.abs(longitude);
    }
}
