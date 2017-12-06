package de.draegerit.vsp.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public final class MD5Util {

  private static final int TWENTYFOUR = 24;
  private static final int THREE = 3;
  private static final int FIVETEEN = 15;
  private static final int NINE = 9;
  private static final int FIVE = 5;
  private static final int ZERO = 0;
  private static final String SALT = "HelloVSPServer26.10.2017";

  private MD5Util() {
  }

  public static String getHash(String value) {
    try {
      String valueToDecode = value.concat(SALT).concat(String.valueOf(System.currentTimeMillis()));

      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      messageDigest.reset();
      messageDigest.update(valueToDecode.getBytes(Charset.forName("UTF8")));
      final byte[] resultByte = messageDigest.digest();
      return getShortHashCode(Hex.encodeHexString(resultByte));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static String getShortHashCode(String encodeHexString) {
    String[] v = encodeHexString.split("");
    return String.format("%s%s%s%s%s%s", v[ZERO], v[FIVE], v[NINE], v[FIVETEEN], v[THREE], v[TWENTYFOUR]);
  }
}
