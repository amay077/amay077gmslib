package jp.co.cosmoroot.android.gms.maps;

import jp.co.cosmoroot.android.gms.maps.model.LatLon;

public class MarkerSchema {
	private String _id;
	private LatLon _position;
	private IconDescriptor _icon;
	private String _title;
	private String _snippet;
	private float _anchorU = 0.5f;
	private float _anchorV = 1.0f;
	private boolean _infoWindowVisible = false;
	
	public MarkerSchema id(String id) {
		_id = id;
		return this;
	}
	public String getId() {
		return _id;
	}
	
	public MarkerSchema position(LatLon pos) {
		_position = pos;
		return this;
	}
	public LatLon getPosition() {
		return _position;
	}

	public MarkerSchema icon(IconDescriptor icon) {
		_icon = icon;
		return this;
	}
	public IconDescriptor getIcon() {
		return _icon;
	}

	public MarkerSchema title(String title) {
		_title = title;
		return this;
	}
	public String getTitle() {
		return _title;
	}

	public MarkerSchema snippet(String snippet) {
		_snippet = snippet;
		return this;
	}
	public String getSnippet() {
		return _snippet;
	}
	
	public MarkerSchema anchor(float u, float v) {
		_anchorU = u;
		_anchorV = v;
		return this;
	}
	public float getAnchorU() {
		return _anchorU;
	}
	public float getAnchorV() {
		return _anchorV;
	}

	public MarkerSchema infoWindowVisible(boolean visible) {
		_infoWindowVisible = visible;
		return this;
	}
	public boolean isInfoWindowVisible() {
		return _infoWindowVisible;
	}
}
