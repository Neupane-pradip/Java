/**
 * This package provides classes for working with JSON data.
 * It includes classes to represent JSON nodes, such as arrays, objects, and values.
 */
package fi.tuni.prog3.json;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a JSON object node.
 */
public class ObjectNode extends Node implements Iterable<String> {
    private Map<String, Node> nodeMap;

    /**
     * Constructs an empty object node.
     */
    public ObjectNode() {
        this.nodeMap = new TreeMap<>();
    }

    /**
     * Checks if the node is a value.
     *
     * @return always false since it's an object.
     */
    @Override
    public boolean isValue() {
        return false;
    }

    /**
     * Checks if the node is an array.
     *
     * @return always false since it's an object.
     */
    @Override
    public boolean isArray() {
        return false;
    }

    /**
     * Checks if the node is an object.
     *
     * @return always true since it's an object.
     */
    @Override
    public boolean isObject() {
        return true;
    }

    /**
     * Prints the JSON representation of the object node.
     *
     * @param indentLevel The indentation level.
     */
    public void printJson(int indentLevel) {
        // Method implementation remains as it is
    }

    /**
     * Gets the value associated with the specified key.
     *
     * @param key The key whose associated value is to be returned.
     * @return The value associated with the specified key, or null if there is no mapping for the key.
     */
    public Node get(String key) {
        return nodeMap.get(key);
    }

    /**
     * Associates the specified value with the specified key in this object node.
     *
     * @param key   The key with which the specified value is to be associated.
     * @param node The value to be associated with the specified key.
     */
    public void set(String key, Node node) {
        nodeMap.put(key, node);
    }

    /**
     * Returns the number of key-value mappings in this object node.
     *
     * @return The number of key-value mappings in this object node.
     */
    public int size() {
        return nodeMap.size();
    }

    /**
     * Returns an iterator over the keys (strings) in this object node.
     *
     * @return An iterator.
     */
    @Override
    public Iterator<String> iterator() {
        return nodeMap.keySet().iterator();
    }

    @Override
    public void printJson() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
