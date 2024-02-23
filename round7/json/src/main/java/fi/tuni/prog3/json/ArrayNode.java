package fi.tuni.prog3.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayNode extends Node implements Iterable<Node> {
    private List<Node> nodeList;

    public ArrayNode() {
        this.nodeList = new ArrayList<>();
    }

    @Override
    public boolean isValue() {
        return false;
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public boolean isObject() {
        return false;
    }

    @Override
    public void printJson() {
        System.out.print("[");
        Iterator<Node> iterator = nodeList.iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            node.printJson();
            if (iterator.hasNext()) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    public void add(Node node) {
        nodeList.add(node);
    }

    public int size() {
        return nodeList.size();
    }

    @Override
    public Iterator<Node> iterator() {
        return nodeList.iterator();
    }
}
