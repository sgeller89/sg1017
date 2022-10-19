package com.sgeller.tool.rental.domain;

import java.util.stream.Stream;

public enum ToolCode {
    CHNS("Stihl", ChainsawTool.TOOL_TYPE),
    LADW("Werner", LadderTool.TOOL_TYPE),
    JAKD("DeWalt", JackhammerTool.TOOL_TYPE),
    JAKR("Ridgid", JackhammerTool.TOOL_TYPE);

    private final String brand;
    private final String toolType;

    ToolCode(String brand, String toolType) {
        this.brand = brand;
        this.toolType = toolType;
    }

    public static ToolCode parse(String code) {
        return Stream.of(values())
                .filter(e -> e.name().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("invalid code: " + code));
    }

    public String getBrand() {
        return this.brand;
    }

    public String getToolType() {
        return this.toolType;
    }
}
