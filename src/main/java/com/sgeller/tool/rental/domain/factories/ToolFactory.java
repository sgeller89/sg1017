package com.sgeller.tool.rental.domain.factories;

import com.sgeller.tool.rental.domain.ChainsawTool;
import com.sgeller.tool.rental.domain.JackhammerTool;
import com.sgeller.tool.rental.domain.LadderTool;
import com.sgeller.tool.rental.domain.Tool;
import com.sgeller.tool.rental.domain.ToolCode;
import com.sgeller.tool.rental.domain.builders.ChainsawToolBuilder;
import com.sgeller.tool.rental.domain.builders.JackhammerToolBuilder;
import com.sgeller.tool.rental.domain.builders.LadderToolBuilder;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ToolFactory {
    private static final Map<String, Function<ToolCode, Tool>> TOOL_FUNCTIONS = Map.of(
            ChainsawTool.TOOL_TYPE, ToolFactory::createChainsaw,
            LadderTool.TOOL_TYPE, ToolFactory::createLadder,
            JackhammerTool.TOOL_TYPE, ToolFactory::createJackhammer
    );

    public static Tool createTool(String toolCode) {
        return Optional.ofNullable(toolCode)
                .map(ToolCode::parse)
                .map(tool -> {
                    Function<ToolCode, Tool> function = TOOL_FUNCTIONS.get(tool.getToolType());
                    return function.apply(tool);
                })
                .orElseThrow(() -> new IllegalArgumentException("invalid toolCode: " + toolCode));
    }

    private static Tool createChainsaw(ToolCode toolCode) {
        return new ChainsawToolBuilder()
                .withToolCode(toolCode.name())
                .withBrand(toolCode.getBrand())
                .build();
    }

    private static Tool createLadder(ToolCode toolCode) {
        return new LadderToolBuilder()
                .withToolCode(toolCode.name())
                .withBrand(toolCode.getBrand())
                .build();
    }

    private static Tool createJackhammer(ToolCode toolCode) {
        return new JackhammerToolBuilder()
                .withToolCode(toolCode.name())
                .withBrand(toolCode.getBrand())
                .build();
    }
}
