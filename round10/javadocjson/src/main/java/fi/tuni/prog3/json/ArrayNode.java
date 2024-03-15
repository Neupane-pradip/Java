/**
 * This package provides classes for working with JSON data.
 * It includes classes to represent JSON nodes, such as arrays, objects, and values.
 */

package fi.tuni.prog3.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a JSON array node.
 */
public class ArrayNode extends Node implements Iterable<Node> {
    private List<Node> nodeList;

    /**
     * Constructs an empty array node.
     */
    public ArrayNode() {
        this.nodeList = new ArrayList<>();
    }

    /**
     * Checks if the node is a value.
     *
     * @return always false since it's an array.
     */
    @Override
    public boolean isValue() {
        return false;
    }

    /**
     * Checks if the node is an array.
     *
     * @return always true since it's an array.
     */
    @Override
    public boolean isArray() {
        return true;
    }

    /**
     * Checks if the node is an object.
     *
     * @return always false since it's an array.
     */
    @Override
    public boolean isObject() {
        return false;
    }

    /**
     * Prints the JSON representation of the array node.
     */
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

    /**
     * Adds a node to the array.
     *
     * @param node The node to be added.
     */
    public void add(Node node) {
        nodeList.add(node);
    }

    /**
     * Returns the size of the array.
     *
     * @return The size of the array.
     */
    public int size() {
        return nodeList.size();
    }

    /**
     * Returns an iterator over the elements in the array.
     *
     * @return An iterator.
     */
    @Override
    public Iterator<Node> iterator() {
        return nodeList.iterator();
    }
}
