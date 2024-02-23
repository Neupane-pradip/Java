package fi.tuni.prog3.json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ObjectNode extends Node implements Iterable<String> {
    private Map<String, Node> nodeMap;

    public ObjectNode() {
        this.nodeMap = new TreeMap<>();
    }

    @Override
    public boolean isValue() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isObject() {
        return true;
    }

    public void printJson(int indentLevel) {
    String singleIndent = "  "; // Two spaces for each level of indentation
    String currentIndent = singleIndent.repeat(indentLevel);

    System.out.println("{");

    Iterator<Map.Entry<String, Node>> iterator = nodeMap.entrySet().iterator();
    while (iterator.hasNext()) {
        Map.Entry<String, Node> entry = iterator.next();

        // Print key with current indentation
        System.out.print(currentIndent + "\"" + entry.getKey() + "\": ");

        Node value = entry.getValue();

        // If the value is an object, print it recursively with increased indentation
        if (value instanceof ObjectNode) {
            System.out.println();
            ((ObjectNode) value).printJson(indentLevel + 1);
        } else if (value instanceof ArrayNode) {
            // If the value is an array, print it with increased indentation
            System.out.println();
            ((ArrayNode) value).printJson(indentLevel + 1);
        } else {
            // Otherwise, print the value directly
            value.printJson();
        }

        // If there are more entries, print a comma and newline
        if (iterator.hasNext()) {
            System.out.print(",");
            System.out.println();
        }
    }

    // Print closing brace with proper indentation
    System.out.println();
    System.out.print(currentIndent + "}");
}





    public Node get(String key) {
        return nodeMap.get(key);
    }

    public void set(String key, Node node) {
        nodeMap.put(key, node);
    }

    public int size() {
        return nodeMap.size();
    }

    @Override
    public Iterator<String> iterator() {
        return nodeMap.keySet().iterator();
    }
}
