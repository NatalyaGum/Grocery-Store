package by.edu.webstore.util;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PasswordEncoderTest {

    @Test
    public void testPasEncodePositive() {
        String password1 = "Asa58!te2*";
        String password2 = "Asa58!te2*";
        String resHex1 = PasswordEncoder.pasEncode(password1);
        String resHex2 = PasswordEncoder.pasEncode(password2);
        Assert.assertTrue(resHex1.equals(resHex2));
    }
    @Test
    public void testPasEncodeNegative() {
        String password1 = "Asa58!te2*";
        String password2 = "Nd891!!56q";
        String resHex1 = PasswordEncoder.pasEncode(password1);
        String resHex2 = PasswordEncoder.pasEncode(password2);
        Assert.assertFalse(resHex1.equals(resHex2));
    }
    }
