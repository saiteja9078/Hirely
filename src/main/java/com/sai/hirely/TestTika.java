package com.sai.hirely;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;

import java.io.File;

public class TestTika {
    public static void main(String[] args) throws Exception {
        Tika tika = new Tika();
        TikaConfig config = TikaConfig.getDefaultConfig();

        System.out.println(config.getParser().getClass());

        String text = tika.parseToString(
                new File("/Users/joyboy/learning/spring/Hirely/uploads/resumes/Coursera_Certificate_Submission_Notice.pdf")
        );

        System.out.println("Length = " + text.length());
        System.out.println(text);
    }
}