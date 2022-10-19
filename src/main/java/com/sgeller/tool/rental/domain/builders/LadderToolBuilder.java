package com.sgeller.tool.rental.domain.builders;

import com.sgeller.tool.rental.domain.LadderTool;

public class LadderToolBuilder extends ToolBuilder<LadderTool, LadderToolBuilder> {

    @Override
    public LadderTool build() {
        return new LadderTool(this.toolCode, this.brand);
    }

    @Override
    public LadderToolBuilder getBuilder() {
        return this;
    }
}
