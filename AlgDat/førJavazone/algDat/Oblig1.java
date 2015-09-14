package AlgDat;
//oblig1 høst 15
//Inge L. Schiager, s198749, IT (3.år)

public class Oblig1
{
	public static int maks(int[] a)
	{
		int sist = a.length - 1;
		int m = 0;
		int maksverdi = a[0];
		int temp = a[sist];
		a[sist] = 0x7fffffff;

		for (int i = 0; ; i++)
			if (a[i] >= maksverdi)
			{
				if (i == sist)
				{
					a[sist] = temp;
					return temp >= maksverdi ? sist : m;
				}
				else
				{
					maksverdi = a[i];
					m = i;
				}
			}
	}
}
