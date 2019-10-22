package com.ptk.elearning.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

public class PasswordUtil {

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    protected static final Logger logger = Logger.getLogger(PasswordUtil.class);

    /**
     * (	# Start of group (?=.*\d)	# must contains one digit from 0-9
     * (?=.*[a-z])	# must contains one lowercase characters (?=.*[A-Z])	# must
     * contains one uppercase characters (?=.*[!@#$%^&*])	# must contains one
     * special symbols in the list "!@#$%^&*" .	# match anything with previous
     * condition checking {8,30} # length at least 8 characters and maximum of
     * 30 )	# End of group
     */
//    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,30})";
    private static final String PASSWORD_PATTERN = "(?=.*?[A-Za-z])(?=.*?[0-9]).{8,32}";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    /**
     *
     * @param stringLength
     * @param option: 1: digits only; 2: digits + a..z; 3: digits + a..z + A..Z
     * @return
     */
    public static String generateRandomString(int stringLength, int option) {

        // Store character for generate string
        String strAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String strCapitalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String strDigits = "0123456789";
        String specialCharacters = "!@#$%^&*";
        // Store string to generate
        String strValid;

        switch (option) {
            case 1:
                strValid = strDigits;
                break;
            case 2:
                strValid = strAlphabet + strDigits + specialCharacters;
                break;
            case 3:
                strValid = strAlphabet + strCapitalAlphabet + strDigits + specialCharacters;
                break;
            default:
                strValid = strAlphabet + strCapitalAlphabet + strDigits + specialCharacters;
                break;
        }

        String stringRamdom = "";

        Random random = new Random();

        for (int i = 0; i < stringLength; i++) {
            int randnum = random.nextInt(strValid.length());
            stringRamdom = stringRamdom + strValid.charAt(randnum);
        }

        return stringRamdom;
    }

    public static String hashPassword(String password, String salt) {
        if (salt == null) {
            salt = "";
        }
        String str = password + salt;
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] passBytes = str.getBytes();
            byte[] passHash = sha256.digest(passBytes);
            char[] charHash = Hex.encodeHex(passHash);
            str = new String(charHash);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

        return str;
    }

    public static boolean validatePasswordStrength(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static String encrypt(String password, String salt) {
        String returnValue = null;
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

        returnValue = Base64.getEncoder().encodeToString(securePassword);

        return returnValue;
    }

    public static String getSalt(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt) {
        boolean returnValue = false;
        String newSecurePassword = encrypt(providedPassword, salt);
        returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);
        return returnValue;
    }

}
