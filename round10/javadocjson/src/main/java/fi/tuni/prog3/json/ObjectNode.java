
package fi.tuni.prog3.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Set;
import java.util.List;

/**
 * A class for representing a JSON object.
 */
public class ObjectNode extends Node implements Iterable<String> {
    TreeMap<String, Node> allTreeMap;

    /**
     * Constructs an initially empty JSON object node.
     */
    public ObjectNode() {
        this.allTreeMap = new TreeMap<>();
    }

    /**
     * Returns the number of JSON nodes stored under this JSON object.
     *
     * @return he number of JSON nodes under this JSON object.
     */
    public int size() {
        return allTreeMap.size();
    }

    /**
     * Returns the JSON node stored under the given name.
     * 
     * @param name the name of the name-node pair whose node should be returned.
     * @return the JSON node corresponding to name, or null if such node does not
     *         exist.
     */
    public Node get(String name) {
        return allTreeMap.get(name);
    }

    /**
     * Stores a name - JSON node pair into this JSON object. If a name-node pair
     * with the same name already exists, the previously existing node will be
     * replaced.
     * 
     * @param name the name of the name-node pair.
     * @param node the JSON node of the name-node pair.
     */
    public void set(String name, Node node) {
        allTreeMap.put(name, node);
    }

    /**
     * Returns a String iterator that iterates the names of the name-node pairs
     * stored in this JSON object in natural String order.
     *
     * @return a String iterator that iterates the names of the stored name-node
     *         pairs in natural String order.
     */
    public Iterator<String> iterator() {
        return new CustomIteratorObjectNode(allTreeMap);
    }

    private class CustomIteratorObjectNode implements Iterator<String> {
        int internalCount = 0;
        Set<String> iterString;
        TreeMap<String, Node> internalTreeMap;
        List<String> myList;

        private CustomIteratorObjectNode(TreeMap<String, Node> internalTreeMap) {
            this.internalTreeMap = internalTreeMap;
            this.iterString = internalTreeMap.keySet();
            this.myList = new ArrayList<>(iterString);
        }

        @Override
        public boolean hasNext() {
            if (internalTreeMap.size() >= internalCount + 1) {
                return true;
            }
            return false;
        }

        @Override
        public String next() {
            String val = myList.get(internalCount);
            internalCount++;
            return val;
        }
    }
}
