package com.sgeller.tool.rental.domain.builders;

import com.sgeller.tool.rental.domain.ChainsawTool;
import com.sgeller.tool.rental.domain.JackhammerTool;
import com.sgeller.tool.rental.domain.ToolCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JackhammerToolBuilderTest {

    @Test
    public void testBuild() {
        JackhammerTool tool = new JackhammerToolBuilder()
                .withToolCode(ToolCode.JAKD.name())
                .withBrand(ToolCode.JAKD.getBrand())
                .build();

        ChainsawTool expected = new ChainsawTool(ToolCode.JAKD.name(), ToolCode.JAKD.getBrand());

        Assertions.assertEquals(expected.getToolCode(), tool.getToolCode());
        Assertions.assertEquals(expected.getBrand(), tool.getBrand());
    }
}
