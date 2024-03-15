/**
 * An abstract base class for various types of JSON nodes.
 */
package fi.tuni.prog3.json;

/**
 * An abstract base class for various types of JSON nodes.
 */
public abstract class Node {

  /**
   * The sole constructor. It will be called, typically implicitly, by constructors of subclasses.
   */
  protected Node(){
  }

  /**
   * Verifies if this node is a representation of a JSON object.
   * @return true if this node is a representation of a JSON object, otherwise false.
   */
  public boolean isObjectType() {
    return this instanceof ObjectNode;
  }

  /**
   * Verifies if this node is a representation of a JSON array.
   * @return true if this node is a representation of a JSON array, otherwise false.
   */
  public boolean isArrayType() {
    return this instanceof ArrayNode;
  }

  /**
   * Verifies if this node is a representation of a JSON value.
   * @return true if this node is a representation of a JSON value, otherwise false.
   */
  public boolean isValueType() {
    return this instanceof ValueNode;
  }
}
