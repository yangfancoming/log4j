

package org.apache.log4j.util;

import org.apache.oro.text.perl.Perl5Util;

public class RelativeTimeFilter implements Filter {

  Perl5Util util = new Perl5Util();
  
  public 
  String filter(String in) {
    String pat = "/"+Filter.RELATIVE_TIME_PAT+"/";

    if(util.match(pat, in)) {    
      //System.out.println("Removing relative time from line ["+in+"]");
      return util.substitute("s/"+Filter.RELATIVE_TIME_PAT+"//", in);
    } else {
      return in;
    }
  }
}
