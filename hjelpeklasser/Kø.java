package hjelpeklasser;

/**
 * Created by inge on 28.10.2015.
 */
public interface K�<T>              // eng: Queue
//Programkode 4.2.1 a)
{
	public void leggInn(T verdi);  // eng: offer/push  legger inn bakerst
	public T kikk();                  // eng: peek        ser p� det som er f�rst
	public T taUt();                  // eng: poll/pop    tar ut det som er f�rst
	public int antall();              // eng: size        antall i k�en
	public boolean tom();             // eng: isEmpty     er k�en tom?
	public void nullstill();          // eng: clear       t�mmer k�en

} // interface K�


