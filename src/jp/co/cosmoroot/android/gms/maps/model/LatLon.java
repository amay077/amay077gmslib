package jp.co.cosmoroot.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LatLon implements Parcelable {

	public final double lat;
	public final double lon;
	
	public LatLon(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(this.lat);
		dest.writeDouble(this.lon);
	}
	
	public static final Parcelable.Creator<LatLon> CREATOR = new Parcelable.Creator<LatLon>() {

		public LatLon createFromParcel(Parcel in) {
			return new LatLon(in);
		}

		public LatLon[] newArray(int size) {
			return new LatLon[size];
		}
	};

	private LatLon(Parcel in) {
		this.lat = in.readDouble();
		this.lon = in.readDouble();
	}
	
}
