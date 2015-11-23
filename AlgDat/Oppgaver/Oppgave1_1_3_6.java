package AlgDat.Oppgaver;

public class Oppgave1_1_3_6
{	
    /* Utrykket n! betyr n fakultet og er gitt ved n! = n · (n-1) ·  ·  · 2 · 1
  Lag en metode long fak(int n) som regner ut n! */
  
  public static long fak(int n) 
  {
    if (n < 0) // n-1
        throw new IllegalArgumentException("Kan ikke ta fakultet av negativt tall"); //x
    long f = 1; // 1
    while (n > 1) // n-1
        f *= n--; // n-1 multiplikasjoner mens n > 1
    return f; // 1
  }

} // Hvor mange multiplikasjoner utføres i metoden? n-1 mens n > 1
// 3(n-1)
