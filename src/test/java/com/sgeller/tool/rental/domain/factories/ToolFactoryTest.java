package com.sgeller.tool.rental.domain.factories;

import com.sgeller.tool.rental.domain.ChainsawTool;
import com.sgeller.tool.rental.domain.JackhammerTool;
import com.sgeller.tool.rental.domain.LadderTool;
import com.sgeller.tool.rental.domain.Tool;
import com.sgeller.tool.rental.domain.ToolCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.stream.Stream;

public class ToolFactoryTest {

    @Test
    public void testCreateToolChainsaw() {
        Tool tool = ToolFactory.createTool(ToolCode.CHNS.name());
        Assertions.assertEquals(ChainsawTool.class, tool.getClass());
        Assertions.assertEquals(ToolCode.CHNS.name(), tool.getToolCode());
        Assertions.assertEquals(ToolCode.CHNS.getBrand(), tool.getBrand());
    }

    @Test
    public void testCreateToolLadder() {
        Tool tool = ToolFactory.createTool(ToolCode.LADW.name());
        Assertions.assertEquals(LadderTool.class, tool.getClass());
        Assertions.assertEquals(ToolCode.LADW.name(), tool.getToolCode());
        Assertions.assertEquals(ToolCode.LADW.getBrand(), tool.getBrand());
    }

    @Test
    public void testCreateToolJackhammer() {
        Tool tool = ToolFactory.createTool(ToolCode.JAKD.name());
        Assertions.assertEquals(JackhammerTool.class, tool.getClass());
        Assertions.assertEquals(ToolCode.JAKD.name(), tool.getToolCode());
        Assertions.assertEquals(ToolCode.JAKD.getBrand(), tool.getBrand());
    }

    @Test
    public void testCreateToolAllValues() {
        Stream.of(ToolCode.values())
                .map(ToolCode::name)
                .map(ToolFactory::createTool)
                .allMatch(Objects::nonNull);
    }
}
