import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.MinPQ;

public class BinaryTrie implements Serializable {
    private Node root;

    private class Node implements Comparable<Node>, Serializable {
        private char ch;
        private int freq;
        private Node left;
        private Node right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return (left == null) && (right == null);
        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }
    
    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        MinPQ<Node> minPQ = new MinPQ<>();
        for (Map.Entry<Character, Integer> m : frequencyTable.entrySet()) {
            Node p = new Node(m.getKey(), m.getValue(), null, null);
            minPQ.insert(p);
        }
        
        while (minPQ.size() > 1) {
            Node left = minPQ.delMin();
            Node right = minPQ.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            minPQ.insert(parent);
        }

        root = minPQ.delMin();
    }
    
    public Match longestPrefixMatch(BitSequence querySequence) {
        Node p = root;
        int length = querySequence.length();
        String s = "";
        for (int i = 0; i < length; i += 1) {
            if (p.isLeaf()) {
                break;
            }
            int bit = querySequence.bitAt(i);
            if (bit == 1) {
                p = p.right;
                s += "1";
            } else {
                p = p.left;
                s += "0";
            }
        }
        return new Match(new BitSequence(s), p.ch);
    }

    
    public Map<Character, BitSequence> buildLookupTable() {
        Map<Character, BitSequence> lookupTable = new HashMap<>();
        lookUpHelper(lookupTable, root, "");
        return lookupTable;
    }
    
    private void lookUpHelper(Map<Character, BitSequence> table, Node p, String s) {
        if (p.isLeaf()) {
            table.put(p.ch, new BitSequence(s));
            return;
        }
        lookUpHelper(table, p.left, s + "0");
        lookUpHelper(table, p.right, s + "1");
    }
}
