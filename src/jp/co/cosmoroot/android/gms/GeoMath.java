package jp.co.cosmoroot.android.gms;

import hu.akarnokd.reactive4java.base.Func1;

import java.util.List;

import jp.co.cosmoroot.android.gms.maps.model.LatLon;
import jp.co.cosmoroot.android.gms.maps.model.LatLonBounds;


import android.graphics.Point;
import android.location.Location;

import com.amay077.android.collections.Lambda;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;

public class GeoMath {

	public GeoMath() {
	}

	public static boolean contains(List<LatLon> latlonRing, double lat, double lon) {
		GeometryFactory gf = new GeometryFactory();
		
		Coordinate[] coords = new Coordinate[latlonRing.size()];
		for (int i = 0; i < coords.length; i++) {
			coords[i] = new Coordinate(latlonRing.get(i).lat, latlonRing.get(i).lon);
		}
		
		com.vividsolutions.jts.geom.Polygon poly = gf.createPolygon(gf.createLinearRing(coords), null);
		com.vividsolutions.jts.geom.Point p = gf.createPoint(new Coordinate(lat, lon));
		return poly.contains(p);
	}

	public List<LatLon> offset(
			final Iterable<LatLon> latlons,
			final double offsetLat, final double offsetLng) {
		
		return Lambda.map(latlons, new Func1<LatLon, LatLon>() {
			@Override
			public LatLon invoke(LatLon l) {
				return new LatLon(l.lat + offsetLat, l.lon + offsetLng);
			}
		});
	}

	public static double calcAngle(Point a, Point b, Point c) {
		
		Point A = new Point(a.x - b.x, a.y - b.y);
		Point B = new Point(c.x - b.x, c.y - b.y);
		
		// via http://www5d.biglobe.ne.jp/~noocyte/Programming/Geometry/RotationDirection.html#GetAngle
		int x = (A.x * B.x) + (A.y * B.y);
        int y = (A.x * B.y) - (A.y * B.x);
		
        return Math.atan2(y, x);
	}

	public static double distance(Point A, Point B) {
		return Math.sqrt(Math.pow(B.x - A.x, 2d) + Math.pow(B.y - A.y ,2d));
	}
	
	public static double distanceAsGeodesic(LatLon a, LatLon b) {
		float[] results = new float[2];
		Location.distanceBetween(a.lat, a.lon, b.lat, b.lon, results);
		
		return results[0];
	}

	public static LatLonBounds getBounds(List<LatLon> ring) {
		GeometryFactory gf = new GeometryFactory();
		
		Coordinate[] coords = new Coordinate[ring.size()];
		for (int i = 0; i < coords.length; i++) {
			coords[i] = new Coordinate(ring.get(i).lon, ring.get(i).lat);
		}
		
		LineString lineString = gf.createLineString(coords);
		Envelope env = lineString.getEnvelopeInternal();
		return new LatLonBounds(env.getMaxY(), env.getMinX(), env.getMinY(), env.getMaxX());
	}

	public static List<LatLon> union(List<LatLon> dispRing, List<LatLon> searchRing) {
		GeometryFactory gf = new GeometryFactory();
		
		com.vividsolutions.jts.geom.Polygon dispPolygon = toPolygon(gf, dispRing);
		com.vividsolutions.jts.geom.Polygon searchPolygon = toPolygon(gf, searchRing);
		
		Geometry intersectioned = dispPolygon.intersection(searchPolygon);

		return Lambda.map(intersectioned.getCoordinates(), new Func1<Coordinate, LatLon>() {
			@Override
			public LatLon invoke(Coordinate c) {
				return new LatLon(c.x, c.y);
			}
		});
	}
	
	private static com.vividsolutions.jts.geom.Polygon toPolygon(
			GeometryFactory gf, List<LatLon> ring) {
		Coordinate[] coords = new Coordinate[ring.size()];
		for (int i = 0; i < coords.length; i++) {
			coords[i] = new Coordinate(ring.get(i).lat, ring.get(i).lon);
		}
		
		return gf.createPolygon(gf.createLinearRing(coords), null);
	}
	
	public static LatLon toLatLon(Location l) {
		return new LatLon(l.getLatitude(), l.getLongitude());
	}

}
