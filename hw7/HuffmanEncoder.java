import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {

    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> frequencyTable = new HashMap<>();
        for (char ch : inputSymbols) {
            if (frequencyTable.containsKey(ch)) {
                int value = frequencyTable.get(ch);
                frequencyTable.replace(ch, value + 1);
            } else {
                frequencyTable.put(ch, 1);
            }
        }
        return frequencyTable;
    }

    public static void main(String[] args) {
        
        char[] inputSymbols = FileUtils.readFile(args[0]);
        ObjectWriter ow = new ObjectWriter(args[0] + ".huf");

        Map<Character, Integer> frequencyTable = buildFrequencyTable(inputSymbols);
        BinaryTrie binaryDecodingTrie = new BinaryTrie(frequencyTable);
        ow.writeObject(binaryDecodingTrie);

        Map<Character, BitSequence> lookupTable = binaryDecodingTrie.buildLookupTable();
        List<BitSequence> bitList = new ArrayList<>();
        for (char ch : inputSymbols) {
            BitSequence bs = lookupTable.get(ch);
            bitList.add(bs);
        }
        BitSequence encodedSequence = BitSequence.assemble(bitList);
        
        ow.writeObject(encodedSequence);
    }
}
