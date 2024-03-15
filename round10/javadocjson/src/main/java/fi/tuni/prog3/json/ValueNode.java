package fi.tuni.prog3.json;

/**
 * Represents a JSON value node.
 */
public class ValueNode extends Node {
    private Object value;

    /**
     * Constructs a null value node.
     */
    public ValueNode() {
        this.value = null;
    }

    /**
     * Constructs a value node with a double value.
     *
     * @param value The double value.
     */
    public ValueNode(double value) {
        this.value = value;
    }

    /**
     * Constructs a value node with a boolean value.
     *
     * @param value The boolean value.
     */
    public ValueNode(boolean value) {
        this.value = value;
    }

    /**
     * Constructs a value node with a string value.
     *
     * @param value The string value.
     */
    public ValueNode(String value) {
        this.value = value;
    }

    /**
     * Checks if the node is a value.
     *
     * @return always true since it's a value.
     */
    @Override
    public boolean isValue() {
        return true;
    }

    /**
     * Checks if the node is an array.
     *
     * @return always false since it's a value.
     */
    @Override
    public boolean isArray() {
        return false;
    }

    /**
     * Checks if the node is an object.
     *
     * @return always false since it's a value.
     */
    @Override
    public boolean isObject() {
        return false;
    }

    /**
     * Prints the JSON representation of the value node.
     */
    @Override
    public void printJson() {
        System.out.print(value);
    }

    /**
     * Checks if the value is a number.
     *
     * @return true if the value is a number, false otherwise.
     */
    public boolean isNumber() {
        return value instanceof Double;
    }

    /**
     * Checks if the value is a boolean.
     *
     * @return true if the value is a boolean, false otherwise.
     */
    public boolean isBoolean() {
        return value instanceof Boolean;
    }

    /**
     * Checks if the value is a string.
     *
     * @return true if the value is a string, false otherwise.
     */
    public boolean isString() {
        return value instanceof String;
    }

    /**
     * Checks if the value is null.
     *
     * @return true if the value is null, false otherwise.
     */
    public boolean isNull() {
        return value == null;
    }

    /**
     * Gets the numeric value of the node.
     *
     * @return The numeric value.
     */
    public double getNumber() {
        return (double) value;
    }

    /**
     * Gets the boolean value of the node.
     *
     * @return The boolean value.
     */
    public boolean getBoolean() {
        return (boolean) value;
    }

    /**
     * Gets the string value of the node.
     *
     * @return The string value.
     */
    public String getString() {
        return (String) value;
    }

    /**
     * Gets the null value of the node.
     *
     * @return The null value.
     */
    public Object getNull() {
        return value;
    }
}
