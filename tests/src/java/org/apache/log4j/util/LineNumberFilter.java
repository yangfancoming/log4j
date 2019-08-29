

package org.apache.log4j.util;

import org.apache.oro.text.perl.Perl5Util;

public class LineNumberFilter implements Filter {

  Perl5Util util = new Perl5Util();

  public 
  String filter(String in) {
    if(util.match("/\\(.*:\\d{1,4}\\)/", in)) {
      return util.substitute("s/:\\d{1,4}\\)/:XXX)/", in);
    } else {
      if(in.indexOf(", Compiled Code") >= 0) {
         return util.substitute("s/, Compiled Code/:XXX/", in);
      }
      return in;
    }
  }
}
