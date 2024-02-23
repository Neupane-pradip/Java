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

    @Override
    public void printJson() {
        System.out.print("{");
        Iterator<Map.Entry<String, Node>> iterator = nodeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Node> entry = iterator.next();
            System.out.print("\"" + entry.getKey() + "\": ");
            entry.getValue().printJson();
            if (iterator.hasNext()) {
                System.out.print(", ");
            }
        }
        System.out.print("}");
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
