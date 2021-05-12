import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
You are provided with a set of APIs for reading an XML document.
You’re tasked with designing and implementing an API to represent the hierarchical structure of XML.
Your API should capture all the values found in an XML document, including tag names and texts
<a>
  <b>
    <c>foo</c>
    <c></c>
  </b>
  <d>blah</d>
</a>
(BEGIN, “a”), (BEGIN, “b”), (BEGIN, “c”), (TEXT, “foo”), (END, “c”)...
*/
class Tokenizer {
    enum TokenType {
        BEGIN,
        END,
        TEXT,
    }

    private final TokenType[] types = {TokenType.BEGIN, TokenType.BEGIN, TokenType.BEGIN, TokenType.TEXT, TokenType.END,
            TokenType.BEGIN, TokenType.END, TokenType.END, TokenType.BEGIN, TokenType.TEXT,
            TokenType.END, TokenType.END};
    private final String[] values = {"a", "b", "c", "foo", "c", "c", "c", "b", "d", "blah", "d", "a"};

    private int index;

    Tokenizer() {
        index = 0;
    }

    Token nextToken() {
        Token token = null;
        if (index < values.length) {
            String value = values[index];
            TokenType type = types[index];
            token = new Token(value, type);

            index++;
        }

        return token;
    }

    public static class Node {
        private final String name;
        private String value;
        private final List<Node> children;

        Node(String name) {
            this.name = name;
            this.value = null;
            this.children = new ArrayList<>();
        }

        void setValue(String value) {
            this.value = value;
        }

        void addChild(Node node) {
            this.children.add(node);
        }

        @Override
        public String toString() {
            // We want the node to look like this: {a, [{b, [{c, "foo"}, ... ]}, ...}
            String prefix = (value == null) ? name : String.format("%s, %s", name, value);
            String childrenToString = "";
            if (!children.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Node node : children) {
                    stringBuilder.append(node);
                    stringBuilder.append(" ");
                }

                childrenToString = String.format(", [%s]", stringBuilder.toString());
            }

            return String.format("{%s%s}", prefix, childrenToString);
        }
    }

    class Token {
        private final String value;
        private final Tokenizer.TokenType type;

        Token(String value, Tokenizer.TokenType type) {
            this.value = value;
            this.type = type;
        }

        String getValue() {
            return this.value;
        }

        Tokenizer.TokenType getType() {
            return this.type;
        }

        @Override
        public String toString() {
            // We want the token to look like this: (BEGIN, “a”)
            return String.format("(%s, \"%s\")", type, value);
        }
    }

}
