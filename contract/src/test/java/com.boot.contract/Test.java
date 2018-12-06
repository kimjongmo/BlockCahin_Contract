package com.boot.contract;

import com.boot.contract.util.ChainCode;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

    @Autowired
    private ChainCode chainCode;

    @org.junit.Test
    public void test() throws Exception{

        chainCode.insertBlock("C:/_contract/781966903/15.docx");
    }

}
