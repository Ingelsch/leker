package AlgDat.Oppgaver;

public class Oppgave1_1_6
{
  /* 3. Lag metoden public static double harmonisk(int n). Metoden skal ved
  hjelp av en lokke regne ut (og returnere) det n-te harmoniske tallet. */
	
	public static double harmonisk (int n)
	{
		if (n <= 0) throw new IllegalArgumentException("argumentet må være positivt");
    double sum = 0;
    while (n > 0) {
        sum += 1d / n--;
    }
    return sum;
	}
	
	/* 4. Lag metoden public static double euler(int n). Den skal returnere
  differansen mellom Hn og log(n). I Java gir Math.log(n) oss den naturlige
  logaritmen til n. Hvor stor må n være for at euler(n) skal returnere et
  tall som starter med 0,577 som de tre første desimalene? */
	
	public static double euler (int n)
	{
		return harmonisk(n) - Math.log(n);
	}
  // n må være minst 638
  private static int eulertest(double min, double max) {
      int n = 0;
      double e;
      do e = euler(++n); while (! (min <= e && e < max) );
      return n;
  }
	
	
	public static void main(String[] args)
	{

    System.out.println("n må være minst: " + eulertest(0.577d, 0.578d) + "\n");
    
    /* 5. Lag en programbit som gir resultatene i Tabell 1.1.6:
    n	10	100	1.000	10.000	100.000	1.000.000   10.000.000
    gj.ant.	1,9	4,2	6,5	8,8	11,1	13,4        15,7
    */
    System.out.println("n:\t\tgjennomstnittlig antall:");
    int n;
    double avg;
    
    for (int i = 1; i <= 7; i++) {
        n = (int) Math.pow(10, i);
        avg = Math.log(n) - 0.423d;
        System.out.printf("%d\t\t%.1f\n", n, avg);
    }
	}

}
