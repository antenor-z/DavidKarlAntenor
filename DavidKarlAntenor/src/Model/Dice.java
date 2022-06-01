package Model;

import java.util.Random;

abstract class Dice {
	static int[] roll(int[] dicesPreset)
	{
		Random r = new Random();
		int[] ret = {0, 0};
		for(int i = 0; i < 2; i++)
		{
			if(dicesPreset[i] > 0 && dicesPreset[i] <= 6)
			{
				ret[i] = dicesPreset[i];
			}
			else
			{
				ret[i] = r.nextInt(6) + 1;
			}
		}
		return ret;
	}
}
