

package org.apache.log4j.util;

import org.apache.oro.text.perl.Perl5Util;

public class XMLLineAttributeFilter implements Filter {

  Perl5Util util = new Perl5Util();

  public 
  String filter(String in) {
    if(util.match("/line=\"\\d{1,3}\"/", in)) {
      return util.substitute("s/line=\"\\d{1,3}\"/line=\"X\"/", in);
    } else if(util.match("/line=\"?\"/", in)) {
      return util.substitute("s/line=\"?\"/line=\"X\"/", in);
    } else {
      return in;
    }
  }
}
