package com.verdeemcasa.api.Infra.Gemini.DTO;

import java.util.Collections;
import java.util.List;

public class GeminiRequestDTO {

    private final List<Content> contents;

    public GeminiRequestDTO(String text) {
        this.contents = Collections.singletonList(new Content(Collections.singletonList(new Part(text))));
    }

    public List<Content> getContents() {
        return contents;
    }

    public static class Content {
        private final List<Part> parts;

        public Content(List<Part> parts) {
            this.parts = parts;
        }

        public List<Part> getParts() {
            return parts;
        }
    }

    public static class Part {
        private final String text;

        public Part(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
