package com.sgeller.tool.rental.domain.builders;

import com.sgeller.tool.rental.domain.Tool;

public abstract class ToolBuilder<T extends Tool, B extends ToolBuilder> implements Builder<T> {

    protected String toolCode;
    protected String brand;

    public ToolBuilder<T, B> withToolCode(String toolCode) {
        this.toolCode = toolCode;
        return this.getBuilder();
    }

    public ToolBuilder<T, B> withBrand(String brand) {
        this.brand = brand;
        return this.getBuilder();
    }

    public abstract B getBuilder();
}
