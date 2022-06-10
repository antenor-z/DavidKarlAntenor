package Model;

import java.util.ArrayList;

public interface Observed {
	void addObserver(Observer o);
	void remObserver(Observer o);
	Object getObserver(int i);
}
