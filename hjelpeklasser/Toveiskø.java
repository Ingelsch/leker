package hjelpeklasser;

/**
 * Created by inge on 18.11.2015.
 */
public interface Toveisk�<T>          // eng: Deque
{
	public void leggInnF�rst(T verdi);  // legger inn f�rst i k�en
	public void leggInnSist(T verdi);   // legger inn sist i k�en
	public T kikkF�rst();               // ser p� den f�rste
	public T kikkSist();                // ser p� den siste
	public T taUtF�rst();               // tar ut den f�rste
	public T taUtSist();                // tar ut den siste
	public boolean tom();               // er k�en tom?
	public int antall();                // antall i k�en
	public void nullstill();            // nullstiller k�en

} // interface Toveisk�
