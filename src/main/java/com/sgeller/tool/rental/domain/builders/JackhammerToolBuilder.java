package com.sgeller.tool.rental.domain.builders;

import com.sgeller.tool.rental.domain.JackhammerTool;

public class JackhammerToolBuilder extends ToolBuilder<JackhammerTool, JackhammerToolBuilder> {

    @Override
    public JackhammerTool build() {
        return new JackhammerTool(this.toolCode, this.brand);
    }

    @Override
    public JackhammerToolBuilder getBuilder() {
        return this;
    }
}
