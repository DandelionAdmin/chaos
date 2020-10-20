package club.gclmit.chaos.core.text;

import club.gclmit.chaos.core.util.SystemUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

/**
 * <p>
 *  常见编码集封装
 * </p>
 *
 * @author gclm
 */
public class CharsetUtils {

    private CharsetUtils(){ }
    
    /**
     * ISO-8859-1
     */
    public static final String ISO_8859_1 = "ISO-8859-1";
    /**
     * UTF-8
     */
    public static final String UTF_8 = "UTF-8";
    /**
     * GBK
     */
    public static final String GBK = "GBK";

    /**
     * ISO-8859-1
     */
    public static final Charset CHARSET_ISO_8859_1 = StandardCharsets.ISO_8859_1;

    /**
     * UTF-8
     */
    public static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;
    /**
     * GBK
     */
    public static final Charset CHARSET_GBK;

    static {
        //避免不支持GBK的系统中运行报错 issue#731
        Charset charset = null;
        try {
            charset = Charset.forName(GBK);
        } catch (UnsupportedCharsetException e) {
            //ignore
        }
        CHARSET_GBK = charset;
    }

    /**
     * 效验字符集；如果给定的字符集为null，则返回默认的字符集。
     *
     * @param charset 字符集，为空则返回默认字符集
     * @return Charset
     * @throws UnsupportedCharsetException 编码不支持
     */
    public static Charset toCharset(final Charset charset) {
        return charset == null ? Charset.defaultCharset() : charset;
    }

    /**
     * 转换为Charset对象
     *
     * @param charset 字符集，为空则返回默认字符集
     * @return Charset
     * @throws UnsupportedCharsetException 编码不支持
     */
    public static Charset toCharset(final String charset) {
        return StringUtils.isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset);
    }

    /**
     * 转换字符串的字符集编码
     *
     * @param source      字符串
     * @param srcCharset  源字符集，默认ISO-8859-1
     * @param destCharset 目标字符集，默认UTF-8
     * @return 转换后的字符集
     */
    public static String convert(String source, String srcCharset, String destCharset) {
        return convert(source, Charset.forName(srcCharset), Charset.forName(destCharset));
    }

    /**
     * 转换字符串的字符集编码<br>
     * 当以错误的编码读取为字符串时，打印字符串将出现乱码。<br>
     * 此方法用于纠正因读取使用编码错误导致的乱码问题。<br>
     * 例如，在Servlet请求中客户端用GBK编码了请求参数，我们使用UTF-8读取到的是乱码，此时，使用此方法即可还原原编码的内容
     * <pre>
     * 客户端 -》 GBK编码 -》 Servlet容器 -》 UTF-8解码 -》 乱码
     * 乱码 -》 UTF-8编码 -》 GBK解码 -》 正确内容
     * </pre>
     *
     * @param source      字符串
     * @param srcCharset  源字符集，默认ISO-8859-1
     * @param destCharset 目标字符集，默认UTF-8
     * @return 转换后的字符集
     */
    public static String convert(String source, Charset srcCharset, Charset destCharset) {
        if (null == srcCharset) {
            srcCharset = StandardCharsets.ISO_8859_1;
        }

        if (null == destCharset) {
            destCharset = StandardCharsets.UTF_8;
        }

        if (StringUtils.isBlank(source) || srcCharset.equals(destCharset)) {
            return source;
        }
        return new String(source.getBytes(srcCharset), destCharset);
    }

    /**
     * 系统字符集编码，如果是Windows，则默认为GBK编码，否则取 {@link CharsetUtils#defaultCharsetName()}
     *
     * @return 系统字符集编码
     * @see CharsetUtils#defaultCharsetName()
     * @since 3.1.2
     */
    public static String systemCharsetName() {
        return systemCharset().name();
    }

    /**
     * 系统字符集编码，如果是Windows，则默认为GBK编码，否则取 {@link CharsetUtils#defaultCharsetName()}
     *
     * @return 系统字符集编码
     * @since 3.1.2
     */
    public static Charset systemCharset() {
        return SystemUtils.isWindows() ? CHARSET_GBK : defaultCharset();
    }

    /**
     * 系统默认字符集编码
     *
     * @return 系统字符集编码
     */
    public static String defaultCharsetName() {
        return defaultCharset().name();
    }

    /**
     * 系统默认字符集编码
     *
     * @return 系统字符集编码
     */
    public static Charset defaultCharset() {
        return Charset.defaultCharset();
    }

}