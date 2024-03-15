/**
 * This package provides classes for working with JSON data.
 * It includes classes to represent JSON nodes, such as arrays, objects, and values.
 */


package fi.tuni.prog3.json;


import java.util.Iterator;
import java.util.ArrayList;


public class ArrayNode extends Node implements Iterable<Node>{

    private final ArrayList<Node> nodeList;

    /**
     * Constructs an initially empty JSON array node.
     */
    public ArrayNode(){
        nodeList = new ArrayList<>();
    }

    /**
     * Returns the count of JSON nodes stored in this JSON array.
     * @return the count of JSON nodes in this JSON array.
     */
    public int size(){
        return nodeList.size();
    }

    /**
     * Appends a new JSON node to the end of this JSON array.
     * @param node - the new JSON node to be appended.
     */
    public void append(Node node){
        nodeList.add(node);
    }

    private class JsonArrayIterator implements Iterator<Node>{

        private int index;

        public JsonArrayIterator(){
            index=0;
        }
        
        @Override
        public boolean hasNext(){
            return index<nodeList.size();
        }

        @Override 
        public Node next(){
            return nodeList.get(index++);
        }
    }

    /**
     * Returns a Node iterator that iterates the JSON nodes stored in this JSON array.
     * @return a Node iterator that iterates the JSON nodes stored in this JSON array.
     */
    @Override
    public Iterator<Node> iterator(){
        return new JsonArrayIterator();
    }
}
