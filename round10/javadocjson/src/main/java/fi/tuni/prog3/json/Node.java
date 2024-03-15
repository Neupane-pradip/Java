package fi.tuni.prog3.json;

/**
 * The abstract class representing a JSON node.
 */
public abstract class Node {
    /**
     * Checks if the node is an object.
     *
     * @return true if the node is an object, false otherwise.
     */
    public boolean isObject() {
        return this instanceof ObjectNode;
    }

    /**
     * Checks if the node is an array.
     *
     * @return true if the node is an array, false otherwise.
     */
    public boolean isArray() {
        return this instanceof ArrayNode;
    }

    /**
     * Checks if the node is a value.
     *
     * @return true if the node is a value, false otherwise.
     */
    public boolean isValue() {
        return this instanceof ValueNode;
    }

    /**
     * Prints the JSON representation of the node.
     */
    public abstract void printJson();
}
