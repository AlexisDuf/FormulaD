/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tools;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author wasson
 */
public class TokenGenerator {
   

  public static String getNextToken() {
    SecureRandom random = new SecureRandom();
    return new BigInteger(130, random).toString(32);
  }
}
