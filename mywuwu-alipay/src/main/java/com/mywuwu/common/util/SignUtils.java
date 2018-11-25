package com.mywuwu.common.util;

import com.alibaba.druid.util.Base64;
import org.springframework.beans.factory.annotation.Value;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 *
 *
 *
 *
 */
public class SignUtils {
    @Value("alipay.public.sign_type")
    private static String ALGORITHM;

    private static String SIGN_ALGORITHMS = "SHA256withRsa";

    private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

    @Value("alipay.public.charset")
    private static String DEFAULT_CHARSET ;

    private static String getAlgorithms(boolean rsa2) {
        return rsa2 ? SIGN_SHA256RSA_ALGORITHMS : SIGN_ALGORITHMS;
    }

    public static String sign(String content, String privateKey, boolean rsa2) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.altBase64ToByteArray(privateKey));
            KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                    .getInstance(getAlgorithms(rsa2));

            signature.initSign(priKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));

            byte[] signed = signature.sign();

            return Base64.byteArrayToBase64(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
