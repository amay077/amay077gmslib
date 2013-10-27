package jp.co.cosmoroot.android.gms.maps;

public interface MarkerAdapter<T> {
	public interface DataSetObserver {
		void onChanged();
		void onInvalidated();
	}

	MarkerSchema getMarkerSchema(int position);
	int	 getCount();
	T getItem(int position);
	boolean	isEmpty();
	
	void registerDataSetObserver(DataSetObserver observer);
	void unregisterDataSetObserver(DataSetObserver observer);
}
