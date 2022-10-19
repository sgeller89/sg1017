package com.sgeller.tool.rental.domain.builders;

import com.sgeller.tool.rental.domain.ChainsawTool;
import com.sgeller.tool.rental.domain.ToolCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChainsawToolBuilderTest {

    @Test
    public void testBuild() {
        ChainsawTool tool = new ChainsawToolBuilder()
                .withToolCode(ToolCode.CHNS.name())
                .withBrand(ToolCode.CHNS.getBrand())
                .build();

        ChainsawTool expected = new ChainsawTool(ToolCode.CHNS.name(), ToolCode.CHNS.getBrand());

        Assertions.assertEquals(expected.getToolCode(), tool.getToolCode());
        Assertions.assertEquals(expected.getBrand(), tool.getBrand());
    }
}
