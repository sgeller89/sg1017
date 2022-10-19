package com.sgeller.tool.rental.domain.builders;

import com.sgeller.tool.rental.domain.ChainsawTool;

public class ChainsawToolBuilder extends ToolBuilder<ChainsawTool, ChainsawToolBuilder> {

    @Override
    public ChainsawTool build() {
        return new ChainsawTool(this.toolCode, this.brand);
    }

    @Override
    public ChainsawToolBuilder getBuilder() {
        return this;
    }
}
