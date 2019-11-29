package com.netcracker.converter;

import com.netcracker.config.TestApplication;
import org.apache.commons.codec.language.Metaphone;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class CustomerMapperTest {

    @Autowired
    private Metaphone metaphone;


    @Test
    public void testMetaphone() {
        Assert.assertEquals("ANJ", metaphone.encode("Angie"));
        Assert.assertEquals("", metaphone.encode(null));
        Assert.assertNotEquals("AN", metaphone.encode("Angie"));
    }
}
