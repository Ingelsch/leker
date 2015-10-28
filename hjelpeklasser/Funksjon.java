package hjelpeklasser;

/**
 * Created by inge on 28.10.2015.
 */
public interface Funksjon<T,R>    // T for argumenttype, R for returtype
{
	R anvend(T t);
}