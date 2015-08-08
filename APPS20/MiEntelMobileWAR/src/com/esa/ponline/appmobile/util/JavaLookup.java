package com.esa.ponline.appmobile.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * An "nslookup" clone in Java.
 * 
 * @author Elliot Rusty Harold, O'Reilly & Associates
 */
public class JavaLookup {

  public static String lookup(String s) {
	String lookup="";
    InetAddress thisComputer;
    byte[] address;

    // get the bytes of the IP address
    try {
      thisComputer = InetAddress.getByName(s);
      address = thisComputer.getAddress();
    } catch (UnknownHostException ue) {
      lookup="Cannot find host " + s;
      return lookup;
    }

    if (isHostname(s)) {
      // Print the IP address
      for (int i = 0; i < address.length; i++) {
        int unsignedByte = address[i] < 0 ? address[i] + 256
            : address[i];
        lookup+=String.valueOf(unsignedByte) + ".";
      }
    } else { // this is an IP address
      try {
    	  lookup=InetAddress.getByName(s).toString();
      } catch (UnknownHostException e) {
    	  lookup="Could not lookup the address " + s;
      }
    }
    return lookup;
  } // end lookup

  private static boolean isHostname(String s) {

    char[] ca = s.toCharArray();
    // if we see a character that is neither a digit nor a period
    // then s is probably a hostname
    for (int i = 0; i < ca.length; i++) {
      if (!Character.isDigit(ca[i])) {
        if (ca[i] != '.') {
          return true;
        }
      }
    }

    // Everything was either a digit or a period
    // so s looks like an IP address in dotted quad format
    return false;

  } // end isHostName

} // end javalookup
