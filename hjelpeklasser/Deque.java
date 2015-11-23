package hjelpeklasser;

/**
 * Created by inge on 18.11.2015.
 */
public interface Deque<T>
{
	public void push(T t);           // legger inn �verst
	public T peek();                 // ser p� den �verste
	public T pop();                  // tar ut den �verste
	public int size();               // antallet
	public boolean isEmpty();        // er det tomt?
	public void clear();             // nullstiller

	// + mange flere metoder

} // interface Deque
