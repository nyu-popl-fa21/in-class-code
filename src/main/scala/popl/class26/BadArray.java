package popl.class26;

public class BadArray {
  public static void main(String[] args) {
	String[] a = {"banana"};
	Object[] b = a; // a and b are now aliased - 
		            // OK because of covariant subtyping of arrays
	b[0] = new Object(); // a[0] is now Object but no longer String
	a[0].charAt(0); // Bzzzzt!!!
  }
}
