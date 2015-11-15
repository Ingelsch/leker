package AlgDat.Oblig3;

import java.util.Comparator;

/**
 * Created by inge on 04.11.2015.
 */

public class Main
{
	public static void main(String... args)
	{
		ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());
		char[] verdier = "GSAHQNRBFCJDOEPKIML".toCharArray();
		for (char c : verdier)
		{
			tre.leggInn(c);
		}

		System.out.println(verdier);
	}
}
