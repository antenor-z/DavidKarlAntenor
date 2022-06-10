package Model;

public interface Observed {
	void addObserver(Observer o);
	void remObserver(Observer o);
	Observer getObserver(int i);
}
