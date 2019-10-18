package com.vladsch.flexmark.test.util;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.test.spec.ResourceLocation;
import com.vladsch.flexmark.test.spec.SpecExample;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.jetbrains.annotations.NotNull;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final public class ComboIssuesSpecTest extends CoreRendererSpecTest {
    private static final String SPEC_RESOURCE = "/core_issues_ast_spec.md";

    protected static final Map<String, DataHolder> optionsMap = new HashMap<>();
    static {
        optionsMap.put("block-no-interrupt-paragraph", new MutableDataSet().set(Parser.BLOCK_QUOTE_INTERRUPTS_PARAGRAPH, false));
        optionsMap.put("fixed-indent", new MutableDataSet().setFrom(ParserEmulationProfile.FIXED_INDENT));
        optionsMap.put("html-comment-full-lines", new MutableDataSet().set(Parser.HTML_BLOCK_COMMENT_ONLY_FULL_LINE, true));
        optionsMap.put("allow-javascript", new MutableDataSet().set(HtmlRenderer.SUPPRESSED_LINKS, ""));
        optionsMap.put("pass-through", new MutableDataSet().set(HtmlRenderer.FORMAT_FLAGS, HtmlRenderer.PASS_THROUGH));
        optionsMap.put("strip-indent", new MutableDataSet().set(TestUtils.SOURCE_INDENT, "> > "));
        optionsMap.put("no-html-blocks", new MutableDataSet().set(Parser.HTML_BLOCK_PARSER, false));
        optionsMap.put("sub-parse", new MutableDataSet()
                .set(TestUtils.SOURCE_PREFIX, "" +
                        "Source Prefix\n" +
                        "")
                .set(TestUtils.SOURCE_SUFFIX, "" +
                        "Source Suffix\n" +
                        "")
        );

        final List<String> customHtmlBlockTags = new ArrayList<>(Parser.HTML_BLOCK_TAGS.getFrom(null));
        customHtmlBlockTags.add("warp10-warpscript-widget");
        optionsMap.put("custom-html-block", new MutableDataSet()
                .set(Parser.HTML_BLOCK_TAGS, customHtmlBlockTags)
                .set(Parser.HTML_BLOCK_DEEP_PARSER, true)
                .set(Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS, false)
                .set(Parser.HTML_BLOCK_DEEP_PARSE_FIRST_OPEN_TAG_ON_ONE_LINE, true)
                .set(Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS_PARTIAL_TAG, false)
        );
        optionsMap.put("deep-html-parser", new MutableDataSet()
                .set(Parser.HTML_BLOCK_DEEP_PARSER, true)
                .set(Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS, false)
                .set(Parser.HTML_BLOCK_DEEP_PARSE_FIRST_OPEN_TAG_ON_ONE_LINE, true)
                .set(Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS_PARTIAL_TAG, false)
        );
    }
    public ComboIssuesSpecTest(@NotNull SpecExample example) {
        super(example, optionsMap);
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        return getTestData(SPEC_RESOURCE);
    }

    @Override
    public @NotNull ResourceLocation getSpecResourceLocation() {
        return ResourceLocation.of(SPEC_RESOURCE);
    }
}
