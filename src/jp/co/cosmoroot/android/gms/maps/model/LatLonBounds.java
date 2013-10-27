package jp.co.cosmoroot.android.gms.maps.model;

import java.util.Arrays;
import java.util.List;

public class LatLonBounds {
	public final double north;
	public final double east;
	public final double south;
	public final double west;
	
	public LatLonBounds(double north, double west, double south, double east) {
		this.east = east;
		this.north = north;
		this.south = south;
		this.west = west;
	}

	public LatLonBounds(LatLon southWest, LatLon northEast) {
		this.east = northEast.lon;
		this.north = northEast.lat;
		this.south = southWest.lat;
		this.west = southWest.lon;
	}

	public List<LatLon> toRing() {
		return Arrays.asList(
				new LatLon(south, west),
				new LatLon(north, west),
				new LatLon(north, east),
				new LatLon(south, east),
				new LatLon(south, west));
	}

	public LatLonBounds inflateBy(float rateLat, float rateLon) {
		double halfLat = ((north - south) * (rateLat - 1d)) / 2d; 
		double halfLon = ((east - west) * (rateLat - 1d)) / 2d; 
		
		return new LatLonBounds(north + halfLat, west - halfLon, south - halfLat, east + halfLon);
	}
	
	public LatLon getCenter() {
		return new LatLon(south + ((north - south) / 2d), west + ((east - west) / 2d));
	}
}
