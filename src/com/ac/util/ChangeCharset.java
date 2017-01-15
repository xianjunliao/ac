package com.ac.util;

import java.io.UnsupportedEncodingException;

public class  ChangeCharset {
    /** 7λASCII�ַ�Ҳ����ISO646-US��Unicode�ַ�Ļ�������      */
    public static final String US_ASCII = "US-ASCII";
    /** ISO������ĸ�� No.1��Ҳ����ISO-LATIN-1     */
    public static final String ISO_8859_1 = "ISO-8859-1";
    /** 8 λ UCS ת����ʽ     */
    public static final String UTF_8 = "UTF-8";
    /** 16 λ UCS ת����ʽ��Big Endian(��͵�ַ��Ÿ�λ�ֽڣ��ֽ�˳��     */
    public static final String UTF_16BE = "UTF-16BE";
    /** 16 λ UCS ת����ʽ��Litter Endian����ߵ�ַ��ŵ�λ�ֽڣ��ֽ�˳��     */
    public static final String UTF_16LE = "UTF-16LE";
    /** 16 λ UCS ת����ʽ���ֽ�˳���ɿ�ѡ���ֽ�˳��������ʶ     */
    public static final String UTF_16 = "UTF-16";
    /** ���ĳ����ַ�     **/
    public static final String GBK = "GBK";
    
    public static final String GB2312 = "GB2312";
    
    /** ���ַ����ת����US-ASCII��     */
    public static String toASCII(String str) throws UnsupportedEncodingException {
        return changeCharset(str, US_ASCII);
    }
    
    /** ���ַ����ת����ISO-8859-1     */
    public static String toISO_8859_1(String str) throws UnsupportedEncodingException {
        return changeCharset(str, ISO_8859_1);
    }
    
    /** ���ַ����ת����UTF-8     */
    public static String toUTF_8(String str) throws UnsupportedEncodingException {
        return changeCharset(str, UTF_8);
    }
    
    /** ���ַ����ת����UTF-16BE     */
    public static String toUTF_16BE(String str) throws UnsupportedEncodingException{
        return changeCharset(str, UTF_16BE);
    }
    
    /** ���ַ����ת����UTF-16LE     */
    public static String toUTF_16LE(String str) throws UnsupportedEncodingException {
        return changeCharset(str, UTF_16LE);
    }
    
    /** ���ַ����ת����UTF-16     */
    public static String toUTF_16(String str) throws UnsupportedEncodingException {
        return changeCharset(str, UTF_16);
    }
    
    /** ���ַ����ת����GBK     */
    public static String toGBK(String str) throws UnsupportedEncodingException {
        return changeCharset(str, GBK);
    }
    
    /** ���ַ����ת����GB2312     */
    public static String toGB2312(String str) throws UnsupportedEncodingException {
        return changeCharset(str,GB2312);
    }
    
    /**
     * �ַ����ת����ʵ�ַ���
     * @param str    ��ת�����ַ�
     * @param newCharset    Ŀ�����
     */
    public static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
        if(str != null) {
            //��Ĭ���ַ��������ַ���ϵͳ��أ�����windowsĬ��ΪGB2312
            byte[] bs = str.getBytes();
            return new String(bs, newCharset);    //���µ��ַ��������ַ�
        }
        return null;
    }
    
    /**
     * �ַ����ת����ʵ�ַ���
     * @param str    ��ת�����ַ�
     * @param oldCharset    Դ�ַ�
     * @param newCharset    Ŀ���ַ�
     */
    public static String changeCharset(String str, String oldCharset, String newCharset) throws UnsupportedEncodingException {
        if(str != null) {
            //��Դ�ַ��������ַ�
            byte[] bs = str.getBytes(oldCharset);
            return new String(bs, newCharset);
        }
        return null;
    }

}
