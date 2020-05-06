package com.diana.yapis.output;

public class Element {
        private String element;

        public Element(String element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return element;
        }
    }
