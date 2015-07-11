
public class Oppgave1_1_3_6
{	
    /* Utrykket n! betyr n fakultet og er gitt ved n! = n · (n-1) ·  ·  · 2 · 1
  Lag en metode long fak(int n) som regner ut n! */
  
  public static long fak(int n) 
  {
    if (n < 0)
        throw new IllegalArgumentException("Kan ikke ta fakultet av negativt tall");
    long f = 1;
    while (n > 1)
        f *= n--;
    return f;
  }


	public static void main(String[] args)
	{
		int n = 5;
		System.out.println("Fakultetet av " + n + " blir: \n" + fak(n));
	}

}
