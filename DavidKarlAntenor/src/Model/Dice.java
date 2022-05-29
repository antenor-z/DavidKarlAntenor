package Model;

import java.util.Random;

public abstract class Dice {
	static int[] roll()
	{
		Random r = new Random();
		int[] ret = {0, 0};
		ret[0] = r.nextInt(6) + 1;
		ret[1] = r.nextInt(6) + 1;
		ret[0] = 5;
		ret[1] = 5;
		return ret;
	}
}
