//
// Sample Thread subclass to implement scalar
// multiplication of an array
//

public class SampleThread extends java.lang.Thread {
  int lo; // will control array access
  int hi; // will control array access
  int smallest; // smallest element
  int[] array;

  //
  // Constructor to initialize low and high indices to be
  // manipulated by this thread, as well as array and factor
  // to be multiplied.
  //
  public SampleThread(int[] a, int l, int h) {
    array = a;
    lo = l;
    hi = h;
  }

  //
  // Finds the smallest value in an array
  //
  public void run() // override must have this type
  {
   for (int i = lo; i < hi; i++) {
      smallest = array[lo];
      if(array[i] < smallest){
      smallest = array[i];
      } 
    }
    System.out.println("From " + lo + " to " + hi + " the smallest value is: " + smallest);

  }
}
