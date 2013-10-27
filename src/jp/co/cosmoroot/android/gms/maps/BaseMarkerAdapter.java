package jp.co.cosmoroot.android.gms.maps;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMarkerAdapter<T> implements MarkerAdapter<T> {
	private final List<T> _items = new ArrayList<T>();
	private DataSetObserver _observer;

	public abstract MarkerSchema getMarkerSchema(int position);
	
	public int getCount() {
		return _items.size();
	}
	
	public T getItem(int position) {
		return _items.get(position); 
	}

	public boolean isEmpty() { 
		return _items.size() == 0;
	}
	
	public void registerDataSetObserver(DataSetObserver observer) {
		_observer = observer;
	}
	
	public void unregisterDataSetObserver(DataSetObserver observer) {
		if (observer != null && observer == _observer) {
			_observer = null;
		}
	}
	
	public void add(T item) {
		_items.add(item);
	}
	
	public void clear() {
		_items.clear();
	}
	
	public void notifyDataSetChanged() {
		if (_observer != null) {
			_observer.onChanged();
		}
	}

	public void notifyDataSetInvalidated() {
		if (_observer != null) {
			_observer.onInvalidated();
		}
	}
}
