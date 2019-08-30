
package org.apache.log4j.spi;

import junit.framework.TestCase;

/**
 * Tests for LocationInfo.
 */
public class LocationInfoTest extends TestCase {

    /**
     * Tests four parameter constructor.
     */
    public void testFourParamConstructor() {
        final String className = LocationInfoTest.class.getName();
        final String methodName = "testFourParamConstructor";
        final String fileName = "LocationInfoTest.java";
        final String lineNumber = "41";
        LocationInfo li = new LocationInfo(fileName,
                className, methodName, lineNumber);
        assertEquals(className, li.getClassName());
        assertEquals(methodName, li.getMethodName());
        assertEquals(fileName, li.getFileName());
        assertEquals(lineNumber, li.getLineNumber());
        assertEquals(className + "."  + methodName
                + "(" + fileName + ":" + lineNumber + ")",
                li.fullInfo);
    }


    /**
     * Class with name that is a substring of its caller.
     */
    private static class NameSubstring {
        /**
         * Construct a LocationInfo.  Location should be immediate caller of this method.
         * @return location info.
         */
        public static LocationInfo getInfo() {
            return new LocationInfo(new Throwable(), NameSubstring.class.getName());

        }
    }

    /**
     * Class whose name is contains the name of the class that obtains the LocationInfo.
     */
    private static class NameSubstringCaller {
        /**
         * Construct a locationInfo.  Location should be this location.
         * @return location info.
         */
        public static LocationInfo getInfo() {
            return NameSubstring.getInfo();
        }

    }

    /**
     * Tests creation of location info when the logger class name
     * is a substring of one of the other classes in the stack trace.
     * See bug 44888.
     */
     public void testLocationInfo() {
         LocationInfo li = NameSubstringCaller.getInfo();
         assertEquals(NameSubstringCaller.class.getName(), li.getClassName());
         assertEquals("getInfo", li.getMethodName());
     }

}
