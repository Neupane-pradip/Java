
package fi.tuni.prog3.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class for representing a JSON array.
 */
public class ArrayNode extends Node implements Iterable<Node> {
    List<Node> allList;

    /**
     * Constructs an initially empty JSON array node.
     */
    public ArrayNode() {
        this.allList = new ArrayList<>();
    }

    /**
     * Returns the number of JSON nodes stored in this JSON array.
     *
     * @return the number of JSON nodes in this JSON array.
     */
    public int size() {
        return allList.size();
    }

    /**
     * Adds a new JSON node to the end of this JSON array.
     *
     * @param node the new JSON node to be added.
     */
    public void add(Node node) {
        allList.add(node);
    }

    /**
     * Returns a Node iterator that iterates the JSON nodes stored in this JSON
     * array.
     *
     * @return a Node iterator that iterates the JSON nodes stored in this JSON
     *         array.
     */
    public Iterator<Node> iterator() {
        return new CustomIterator<Node>(allList);
    }

    private class CustomIterator<E> implements Iterator<E> {
        int internalCount = 0;
        List<E> internalList;

        private CustomIterator(List<E> internalList) {
            this.internalList = internalList;
        }

        @Override
        public boolean hasNext() {
            if (internalList.size() >= internalCount + 1) {
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            E val = internalList.get(internalCount);
            internalCount++;
            return val;
        }

    }
}
