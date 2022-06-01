package Model;

@SuppressWarnings("serial")
public class GameException extends Exception{
	GameException(String m) {
		super(m);
	}
	GameException() {
		super();
	}
}
