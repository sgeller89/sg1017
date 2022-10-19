package com.sgeller.tool.rental.domain.builders;

import com.sgeller.tool.rental.domain.ChainsawTool;
import com.sgeller.tool.rental.domain.LadderTool;
import com.sgeller.tool.rental.domain.ToolCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LadderToolBuilderTest {

    @Test
    public void testBuild() {
        LadderTool tool = new LadderToolBuilder()
                .withToolCode(ToolCode.LADW.name())
                .withBrand(ToolCode.LADW.getBrand())
                .build();

        ChainsawTool expected = new ChainsawTool(ToolCode.LADW.name(), ToolCode.LADW.getBrand());

        Assertions.assertEquals(expected.getToolCode(), tool.getToolCode());
        Assertions.assertEquals(expected.getBrand(), tool.getBrand());
    }
}
