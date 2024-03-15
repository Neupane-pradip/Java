
package fi.tuni.prog3.json;

/**
 * This class encapsulates a JSON value. The encapsulated value can be a double, a boolean, a String, or null.
 */
public final class ValueNode extends Node {

    private final Object data;

    /**
     * Constructs a JSON value node that encapsulates the provided double value.
     * @param data The double value to be encapsulated in the new JSON value node.
     */
    public ValueNode(double data) {
        this.data = data;
    }

    /**
     * Constructs a JSON value node that encapsulates the provided boolean value.
     * @param data The boolean value to be encapsulated in the new JSON value node.
     */
    public ValueNode(boolean data) {
        this.data = data;
    }

    /**
     * Constructs a JSON value node that encapsulates the provided string or null.
     * @param data The string or null to be encapsulated in the new JSON value node.
     */
    public ValueNode(String data) {
        this.data = data;
    }

    /**
     * Verifies if this value node encapsulates a number (double).
     * @return true if this node encapsulates a double value, otherwise false.
     */
    public boolean isNumeric() {
        return data instanceof Number;
    }

    /**
     * Verifies if this value node encapsulates a boolean value.
     * @return true if this node encapsulates a boolean value, otherwise false.
     */
    public boolean isBool() {
        return data instanceof Boolean;
    }

    /**
     * Verifies if this value node encapsulates a string.
     * @return true if this node encapsulates a string, otherwise false.
     */
    public boolean isText() {
        return data instanceof String;
    }

    /**
     * Verifies if this value node encapsulates null.
     * @return true if this node encapsulates null, otherwise false.
     */
    public boolean isNone() {
        return data == null;
    }

    /**
     * Retrieves the encapsulated value as a number (double).
     * @return the encapsulated number as a double value.
     * @throws IllegalStateException if the encapsulated value is not a number.
     */
    public double getNumeric() {
        if (!isNumeric()) {
            throw new IllegalStateException();
        }
        return (double) data;
    }

    /**
     * Retrieves the encapsulated value as a boolean value.
     * @return the encapsulated boolean value.
     * @throws IllegalStateException if the encapsulated value is not a boolean value.
     */
    public boolean getBool() {
        if (!isBool()) {
            throw new IllegalStateException();
        }
        return (boolean) data;
    }

    /**
     * Retrieves the encapsulated value as a string.
     * @return the encapsulated string.
     * @throws IllegalStateException if the encapsulated value is not a string.
     */
    public String getText() {
        if (!isText()) {
            throw new IllegalStateException();
        }
        return (String) data;
    }

    /**
     * Retrieves the encapsulated value as null.
     * @return null.
     * @throws IllegalStateException if the encapsulated value is not null.
     */
    public Object getNone() {
        if (!isNone()) {
            throw new IllegalStateException();
        }
        return null;
    }
}
