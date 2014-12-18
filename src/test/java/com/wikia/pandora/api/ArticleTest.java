package com.wikia.pandora.api;

import com.wikia.pandora.api.util.HalResponseRenderer;
import junit.framework.TestCase;
import org.junit.Test;

public class ArticleTest extends TestCase {

    @Test
    public void testArticleSerialization() throws java.io.IOException {
        Article a = new Article.Builder()
                .content("fooo")
                .id(1)
                .title("foo")
                .build();

        HalResponseRenderer renderer = new HalResponseRenderer(a);
        assertTrue(renderer.render().length() > 0);
    }

}