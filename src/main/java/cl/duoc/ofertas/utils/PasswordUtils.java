/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.utils;

import java.security.MessageDigest;
import org.apache.log4j.Logger;

/**
 *
 * @author Esteban Perez
 */
public class PasswordUtils {

    private final static Logger logger = Logger.getLogger(PasswordUtils.class);

    public static String getHash(String txt, String hashType) {
        try {
            MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            logger.error("Error grave cifrando texto.", e);
            throw new RuntimeException(e);
        }
    }
}
