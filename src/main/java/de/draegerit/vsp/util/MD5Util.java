package de.draegerit.vsp.util;

import de.draegerit.vsp.VSPServerConstants;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MD5Util {

  private static Logger logger = LoggerFactory.getLogger("MD5Util.class");

  private MD5Util() {
  }

  public static String getHash(String value) {
    try {
      String valueToDecode = value.concat(VSPServerConstants.SALT).concat(String.valueOf(System.currentTimeMillis()));

      MessageDigest messageDigest = MessageDigest.getInstance(VSPServerConstants.MD5_INST);
      messageDigest.reset();
      messageDigest.update(valueToDecode.getBytes(Charset.forName(VSPServerConstants.CHARSET_UTF8)));
      final byte[] resultByte = messageDigest.digest();
      return getShortHashCode(Hex.encodeHexString(resultByte));
    } catch (NoSuchAlgorithmException e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }

  private static String getShortHashCode(String encodeHexString) {
    String[] v = encodeHexString.split(VSPServerConstants.EMPTY);

    String s1 = v[VSPServerConstants.ZERO];
    String s2 = v[VSPServerConstants.FIVE];
    String s3 = v[VSPServerConstants.NINE];
    String s4 = v[VSPServerConstants.FIVETEEN];
    String s5 = v[VSPServerConstants.THREE];
    String s6 = v[VSPServerConstants.TWENTYFOUR];

    return String.format("%s%s%s%s%s%s", s1, s2, s3, s4, s5, s6);
  }
}
