package hjelpeklasser;

/**
 * Created by inge on 18.11.2015.
 */
public interface Deque<T>
{
	public void push(T t);           // legger inn øverst
	public T peek();                 // ser på den øverste
	public T pop();                  // tar ut den øverste
	public int size();               // antallet
	public boolean isEmpty();        // er det tomt?
	public void clear();             // nullstiller

	// + mange flere metoder

} // interface Deque
