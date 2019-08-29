

package org.apache.log4j.performance;

/**
   Measures the time required to make a System.currentTimeMillis() and
   Thread.currentThread().getName() calls.

   <p>On an 233Mhz NT machine (JDK 1.1.7B) the
   System.currentTimeMillis() call takes under half a microsecond to
   complete whereas the Thread.currentThread().getName() call takes
   about 4 micro-seconds.

*/
public class SystemTime {

  static int RUN_LENGTH = 1000000;

  static
  public 
  void main(String[] args) {    
    double t = systemCurrentTimeLoop();
    System.out.println("Average System.currentTimeMillis() call took " + t);

    t = currentThreadNameloop();
    System.out.println("Average Thread.currentThread().getName() call took " 
		       + t);
    
  }

  static
  double systemCurrentTimeLoop() {
    long before = System.currentTimeMillis();
    for(int i = 0; i < RUN_LENGTH; i++) {
      System.currentTimeMillis();
    }
    return (System.currentTimeMillis() - before)*1000.0/RUN_LENGTH;    
  }

  static
  double currentThreadNameloop() {
    long before = System.currentTimeMillis();
    for(int i = 0; i < RUN_LENGTH; i++) {
      Thread.currentThread().getName();
    }
    return (System.currentTimeMillis() - before)*1000.0/RUN_LENGTH;    
  }  
}
