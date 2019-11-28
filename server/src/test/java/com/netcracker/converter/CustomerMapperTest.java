package com.netcracker.converter;

import com.netcracker.config.TestApplication;
import org.apache.commons.codec.language.Metaphone;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class CustomerMapperTest {

    @Autowired
    private Metaphone metaphone;

    public CustomerMapperTest() {

    }


    @Test
    public void testMetaphone() {
        System.out.println("Angie " + metaphone.encode("Angie"));
        System.out.println("Shannon " + metaphone.encode("Shannon"));
        System.out.println("Eudora " + metaphone.encode("Eudora"));
        System.out.println("Forbes " + metaphone.encode("Forbes"));

        System.out.println("Benedicte " + metaphone.encode("Benedicte"));
        System.out.println("Hanalan " + metaphone.encode("Hanalan"));
        System.out.println("Tommasetti " + metaphone.encode("Tommasetti"));
        System.out.println("Pruvost " + metaphone.encode("Pruvost"));
        System.out.println("Burles " + metaphone.encode("Burles"));
        System.out.println("Chrippes " + metaphone.encode("Chrippes"));
        System.out.println("Loyns " + metaphone.encode("Loyns"));
        System.out.println("Jarrell " + metaphone.encode("Jarrell"));

        System.out.println("Toolin " + metaphone.encode("Toolin"));
        System.out.println("Schoenfisch " + metaphone.encode("Schoenfisch"));
        System.out.println("Leathe " + metaphone.encode("Leathe"));
        System.out.println("Connolly " + metaphone.encode("Connolly"));
        System.out.println("Matthewman " + metaphone.encode("Matthewman"));
        System.out.println("Dumphy " + metaphone.encode("Dumphy"));
        System.out.println("Barca " + metaphone.encode("Barca"));
        System.out.println("Shorland " + metaphone.encode("Shorland"));
        System.out.println("Pieracci " + metaphone.encode("Pieracci"));
        System.out.println("Caldeiro " + metaphone.encode("Caldeiro"));
        System.out.println("Feathersby " + metaphone.encode("Feathersby"));
        System.out.println("Sully " + metaphone.encode("Sully"));
        System.out.println("Boggas " + metaphone.encode("Boggas"));
        System.out.println("Blackmoor " + metaphone.encode("Blackmoor"));
        System.out.println("Elcombe " + metaphone.encode("Elcombe"));
        System.out.println("Abrahams " + metaphone.encode("Abrahams"));

        System.out.println(metaphone.getMaxCodeLen());
    }
}
