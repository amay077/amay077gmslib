package jp.co.cosmoroot.android.gms.maps;

import android.graphics.Bitmap;

import com.amay077.android.types.Asset;
import com.amay077.android.types.ResId;

public class IconDescriptor {
	public enum Type {
		Default,
		Asset,
		Bitmap,
		Resource
	}
	
	public static IconDescriptor defaultMarker() {
		return new IconDescriptor(0);
	};
	
	public static IconDescriptor defaultMarker(float hue) {
		return new IconDescriptor(hue);
	};
	
	public static IconDescriptor fromAsset(Asset asset) {
		return new IconDescriptor(asset);
	};
	
	public static IconDescriptor fromBitmap(Bitmap image) {
		return new IconDescriptor(image);
	};

	public static IconDescriptor fromResource(ResId resId) {
		return new IconDescriptor(resId);
	};

	private final Type _type;
	private float _hue;
	private Asset _asset;
	private Bitmap _image;
	private ResId _resId;
	
	private IconDescriptor(float hue) {
		_hue = hue;
		_type = Type.Default;
	}

	private IconDescriptor(Asset asset) {
		_asset = asset;
		_type = Type.Asset;
	}

	public IconDescriptor(Bitmap image) {
		_image = image;
		_type = Type.Bitmap;
	}
	
	public IconDescriptor(ResId resId) {
		_resId = resId;
		_type = Type.Resource;
	}

	public Type getType() {
		return _type;
	}
	
	public float getHue() {
		return _hue;
	}
	
	public Asset getAsset() {
		return _asset;
	}
	
	public Bitmap getBitmap() {
		return _image;
	}
	
	public ResId getResource() {
		return _resId;
	}

}
