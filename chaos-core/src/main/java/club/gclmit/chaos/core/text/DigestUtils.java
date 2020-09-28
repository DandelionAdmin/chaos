package club.gclmit.chaos.core.text;

import club.gclmit.chaos.core.collection.ArrayUtils;
import club.gclmit.chaos.core.lang.ConvertHandler;
import club.gclmit.chaos.core.util.ExceptionUtils;
import org.springframework.lang.Nullable;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 签名工具类
 * </p>
 *
 * @author gclm
 */
public class DigestUtils {

    /**
     * 将 long 转短字符串 为 62 进制
     *
     * @param i 数字
     * @return 短字符串
     */
    public static String to62Str(long i) {
        int radix = StringUtils.DIGITS.length;
        byte[] buf = new byte[65];
        int charPos = 64;
        i = -i;
        while (i <= -radix) {
            buf[charPos--] = StringUtils.DIGITS[(int) (-(i % radix))];
            i = i / radix;
        }
        buf[charPos] = StringUtils.DIGITS[(int) (-i)];
        return new String(buf, charPos, (65 - charPos), CharsetUtils.CHARSET_UTF_8);
    }

    /**
     * Calculates the MD5 digest.
     *
     * @param bytes Data to digest
     * @return MD5 digest as a hex array
     */
    public static byte[] md5(final byte[] bytes) {
        return digest("MD5", bytes);
    }

    /**
     * Calculates the MD5 digest.
     *
     * @param data Data to digest
     * @return MD5 digest as a hex array
     */
    public static byte[] md5(final String data) {
        return md5(ConvertHandler.toByteArray(data));
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex string.
     *
     * @param data Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(final String data) {
        return encodeHex(md5(data));
    }

    /**
     * Return a hexadecimal string representation of the MD5 digest of the given bytes.
     *
     * @param bytes the bytes to calculate the digest over
     * @return a hexadecimal digest string
     */
    public static String md5Hex(final byte[] bytes) {
        return encodeHex(md5(bytes));
    }

    /**
     * sha1
     *
     * @param data Data to digest
     * @return digest as a hex array
     */
    public static byte[] sha1(String data) {
        return sha1(ConvertHandler.toByteArray(data));
    }

    /**
     * sha1
     *
     * @param bytes Data to digest
     * @return digest as a hex array
     */
    public static byte[] sha1(final byte[] bytes) {
        return digest("SHA-1", bytes);
    }

    /**
     * sha1Hex
     *
     * @param data Data to digest
     * @return digest as a hex string
     */
    public static String sha1Hex(String data) {
        return encodeHex(sha1(ConvertHandler.toByteArray(data)));
    }

    /**
     * sha1Hex
     *
     * @param bytes Data to digest
     * @return digest as a hex string
     */
    public static String sha1Hex(final byte[] bytes) {
        return encodeHex(sha1(bytes));
    }

    /**
     * SHA224
     *
     * @param data Data to digest
     * @return digest as a byte array
     */
    public static byte[] sha224(String data) {
        return sha224(ConvertHandler.toByteArray(data));
    }

    /**
     * SHA224
     *
     * @param bytes Data to digest
     * @return digest as a byte array
     */
    public static byte[] sha224(final byte[] bytes) {
        return digest("SHA-224", bytes);
    }

    /**
     * SHA224Hex
     *
     * @param data Data to digest
     * @return digest as a hex string
     */
    public static String sha224Hex(String data) {
        return encodeHex(sha224(ConvertHandler.toByteArray(data)));
    }

    /**
     * SHA224Hex
     *
     * @param bytes Data to digest
     * @return digest as a hex string
     */
    public static String sha224Hex(final byte[] bytes) {
        return encodeHex(sha224(bytes));
    }

    /**
     * sha256Hex
     *
     * @param data Data to digest
     * @return digest as a byte array
     */
    public static byte[] sha256(String data) {
        return sha256(ConvertHandler.toByteArray(data));
    }

    /**
     * sha256Hex
     *
     * @param bytes Data to digest
     * @return digest as a byte array
     */
    public static byte[] sha256(final byte[] bytes) {
        return digest("SHA-256", bytes);
    }

    /**
     * sha256Hex
     *
     * @param data Data to digest
     * @return digest as a hex string
     */
    public static String sha256Hex(String data) {
        return encodeHex(sha256(ConvertHandler.toByteArray(data)));
    }

    /**
     * sha256Hex
     *
     * @param bytes Data to digest
     * @return digest as a hex string
     */
    public static String sha256Hex(final byte[] bytes) {
        return encodeHex(sha256(bytes));
    }

    /**
     * sha384
     *
     * @param data Data to digest
     * @return digest as a byte array
     */
    public static byte[] sha384(String data) {
        return sha384(ConvertHandler.toByteArray(data));
    }

    /**
     * sha384
     *
     * @param bytes Data to digest
     * @return digest as a byte array
     */
    public static byte[] sha384(final byte[] bytes) {
        return digest("SHA-384", bytes);
    }

    /**
     * sha384Hex
     *
     * @param data Data to digest
     * @return digest as a hex string
     */
    public static String sha384Hex(String data) {
        return encodeHex(sha384(ConvertHandler.toByteArray(data)));
    }

    /**
     * sha384Hex
     *
     * @param bytes Data to digest
     * @return digest as a hex string
     */
    public static String sha384Hex(final byte[] bytes) {
        return encodeHex(sha384(bytes));
    }

    /**
     * sha512Hex
     *
     * @param data Data to digest
     * @return digest as a byte array
     */
    public static byte[] sha512(String data) {
        return sha512(ConvertHandler.toByteArray(data));
    }

    /**
     * sha512Hex
     *
     * @param bytes Data to digest
     * @return digest as a byte array
     */
    public static byte[] sha512(final byte[] bytes) {
        return digest("SHA-512", bytes);
    }

    /**
     * sha512Hex
     *
     * @param data Data to digest
     * @return digest as a hex string
     */
    public static String sha512Hex(String data) {
        return encodeHex(sha512(ConvertHandler.toByteArray(data)));
    }

    /**
     * sha512Hex
     *
     * @param bytes Data to digest
     * @return digest as a hex string
     */
    public static String sha512Hex(final byte[] bytes) {
        return encodeHex(sha512(bytes));
    }

    /**
     * digest
     *
     * @param algorithm 算法
     * @param bytes     Data to digest
     * @return digest byte array
     */
    public static byte[] digest(String algorithm, byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            return md.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw ExceptionUtils.unchecked(e);
        }
    }

    /**
     * digest Hex
     *
     * @param algorithm 算法
     * @param bytes     Data to digest
     * @return digest as a hex string
     */
    public static String digestHex(String algorithm, byte[] bytes) {
        return encodeHex(digest(algorithm, bytes));
    }

    /**
     * hmacMd5
     *
     * @param data Data to digest
     * @param key  key
     * @return digest as a byte array
     */
    public static byte[] hmacMd5(String data, String key) {
        return hmacMd5(ConvertHandler.toByteArray(data), key);
    }

    /**
     * hmacMd5
     *
     * @param bytes Data to digest
     * @param key   key
     * @return digest as a byte array
     */
    public static byte[] hmacMd5(final byte[] bytes, String key) {
        return digestHmac("HmacMD5", bytes, key);
    }

    /**
     * hmacMd5 Hex
     *
     * @param data Data to digest
     * @param key  key
     * @return digest as a hex string
     */
    public static String hmacMd5Hex(String data, String key) {
        return encodeHex(hmacMd5(ConvertHandler.toByteArray(data), key));
    }

    /**
     * hmacMd5 Hex
     *
     * @param bytes Data to digest
     * @param key   key
     * @return digest as a hex string
     */
    public static String hmacMd5Hex(final byte[] bytes, String key) {
        return encodeHex(hmacMd5(bytes, key));
    }

    /**
     * hmacSha1
     *
     * @param data Data to digest
     * @param key  key
     * @return digest as a byte array
     */
    public static byte[] hmacSha1(String data, String key) {
        return hmacSha1(ConvertHandler.toByteArray(data), key);
    }

    /**
     * hmacSha1
     *
     * @param bytes Data to digest
     * @param key   key
     * @return digest as a byte array
     */
    public static byte[] hmacSha1(final byte[] bytes, String key) {
        return digestHmac("HmacSHA1", bytes, key);
    }

    /**
     * hmacSha1 Hex
     *
     * @param data Data to digest
     * @param key  key
     * @return digest as a hex string
     */
    public static String hmacSha1Hex(String data, String key) {
        return encodeHex(hmacSha1(ConvertHandler.toByteArray(data), key));
    }

    /**
     * hmacSha1 Hex
     *
     * @param bytes Data to digest
     * @param key   key
     * @return digest as a hex string
     */
    public static String hmacSha1Hex(final byte[] bytes, String key) {
        return encodeHex(hmacSha1(bytes, key));
    }

    /**
     * hmacSha224
     *
     * @param data Data to digest
     * @param key  key
     * @return digest as a hex string
     */
    public static byte[] hmacSha224(String data, String key) {
        return hmacSha224(ConvertHandler.toByteArray(data), key);
    }

    /**
     * hmacSha224
     *
     * @param bytes Data to digest
     * @param key   key
     * @return digest as a hex string
     */
    public static byte[] hmacSha224(final byte[] bytes, String key) {
        return digestHmac("HmacSHA224", bytes, key);
    }

    /**
     * hmacSha224 Hex
     *
     * @param data Data to digest
     * @param key  key
     * @return digest as a hex string
     */
    public static String hmacSha224Hex(String data, String key) {
        return encodeHex(hmacSha224(ConvertHandler.toByteArray(data), key));
    }

    /**
     * hmacSha224 Hex
     *
     * @param bytes Data to digest
     * @param key   key
     * @return digest as a hex string
     */
    public static String hmacSha224Hex(final byte[] bytes, String key) {
        return encodeHex(hmacSha224(bytes, key));
    }

    /**
     * hmacSha256
     *
     * @param data Data to digest
     * @param key  key
     * @return digest as a hex string
     */
    public static byte[] hmacSha256(String data, String key) {
        return hmacSha256(ConvertHandler.toByteArray(data), key);
    }

    /**
     * hmacSha256
     *
     * @param bytes Data to digest
     * @param key   key
     * @return digest as a byte array
     */
    public static byte[] hmacSha256(final byte[] bytes, String key) {
        return digestHmac("HmacSHA256", bytes, key);
    }

    /**
     * hmacSha256 Hex
     *
     * @param data Data to digest
     * @param key  key
     * @return digest as a byte array
     */
    public static String hmacSha256Hex(String data, String key) {
        return encodeHex(hmacSha256(ConvertHandler.toByteArray(data), key));
    }

    /**
     * hmacSha256 Hex
     *
     * @param bytes Data to digest
     * @param key   key
     * @return digest as a hex string
     */
    public static String hmacSha256Hex(final byte[] bytes, String key) {
        return encodeHex(hmacSha256(bytes, key));
    }

    /**
     * hmacSha384
     *
     * @param data Data to digest
     * @param key  key
     * @return digest as a byte array
     */
    public static byte[] hmacSha384(String data, String key) {
        return hmacSha384(ConvertHandler.toByteArray(data), key);
    }

    /**
     * hmacSha384
     *
     * @param bytes Data to digest
     * @param key   key
     * @return digest as a byte array
     */
    public static byte[] hmacSha384(final byte[] bytes, String key) {
        return digestHmac("HmacSHA384", bytes, key);
    }

    /**
     * hmacSha384 Hex
     *
     * @param data Data to digest
     * @param key  key
     * @return digest as a hex string
     */
    public static String hmacSha384Hex(String data, String key) {
        return encodeHex(hmacSha384(ConvertHandler.toByteArray(data), key));
    }

    /**
     * hmacSha384 Hex
     *
     * @param bytes Data to digest
     * @param key   key
     * @return digest as a hex string
     */
    public static String hmacSha384Hex(final byte[] bytes, String key) {
        return encodeHex(hmacSha384(bytes, key));
    }

    /**
     * hmacSha512
     *
     * @param data Data to digest
     * @param key  key
     * @return digest as a byte array
     */
    public static byte[] hmacSha512(String data, String key) {
        return hmacSha512(ConvertHandler.toByteArray(data), key);
    }

    /**
     * hmacSha512
     *
     * @param bytes Data to digest
     * @param key   key
     * @return digest as a byte array
     */
    public static byte[] hmacSha512(final byte[] bytes, String key) {
        return digestHmac("HmacSHA512", bytes, key);
    }

    /**
     * hmacSha512 Hex
     *
     * @param data Data to digest
     * @param key  key
     * @return digest as a hex string
     */
    public static String hmacSha512Hex(String data, String key) {
        return encodeHex(hmacSha512(ConvertHandler.toByteArray(data), key));
    }

    /**
     * hmacSha512 Hex
     *
     * @param bytes Data to digest
     * @param key   key
     * @return digest as a hex string
     */
    public static String hmacSha512Hex(final byte[] bytes, String key) {
        return encodeHex(hmacSha512(bytes, key));
    }

    /**
     * digest Hmac Hex
     *
     * @param algorithm 算法
     * @param bytes     Data to digest
     * @param key       key
     * @return digest as a hex string
     */
    public static String digestHmacHex(String algorithm, final byte[] bytes, String key) {
        return encodeHex(digestHmac(algorithm, bytes, key));
    }

    /**
     * digest Hmac
     *
     * @param algorithm 算法
     * @param bytes     Data to digest
     * @param key       key
     * @return digest as a byte array
     */
    public static byte[] digestHmac(String algorithm, final byte[] bytes, String key) {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(CharsetUtils.CHARSET_UTF_8), algorithm);
        try {
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            return mac.doFinal(bytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw ExceptionUtils.unchecked(e);
        }
    }

    /**
     * encode Hex
     *
     * @param bytes Data to Hex
     * @return bytes as a hex string
     */
    public static String encodeHex(byte[] bytes) {
        return HexUtils.encodeToString(bytes);
    }

    /**
     * decode Hex
     *
     * @param hexStr Hex string
     * @return decode hex to bytes
     */
    public static byte[] decodeHex(final String hexStr) {
        return HexUtils.decode(hexStr);
    }

    /**
     * 比较字符串，避免字符串因为过长，产生耗时
     *
     * @param a String
     * @param b String
     * @return 是否相同
     */
    public static boolean slowEquals(@Nullable String a, @Nullable String b) {
        if (a == null || b == null) {
            return false;
        }
        return slowEquals(a.getBytes(CharsetUtils.CHARSET_UTF_8), b.getBytes(CharsetUtils.CHARSET_UTF_8));
    }

    /**
     * 比较 byte 数组，避免字符串因为过长，产生耗时
     *
     * @param args1 byte array
     * @param args2 byte array
     * @return 是否相同
     */
    public static boolean slowEquals(@Nullable byte[] args1, @Nullable byte[] args2) {
        if (ArrayUtils.isEmpty(args1) || ArrayUtils.isEmpty(args2)) {
            return false;
        }
        assert args2 != null;
        if (args1.length != args2.length) {
            return false;
        }
        int diff = args1.length ^ args2.length;
        for (int i = 0; i < args1.length && i < args2.length; i++) {
            diff |= args1[i] ^ args2[i];
        }
        return diff == 0;
    }

}
