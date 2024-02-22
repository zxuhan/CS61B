public class HuffmanDecoder {
    public static void main(String[] args) {
        ObjectReader or = new ObjectReader(args[0]);
        ObjectWriter ow = new ObjectWriter(args[1]);
        
        BinaryTrie binaryDecodingTrie = (BinaryTrie) or.readObject();
        BitSequence encodedText = (BitSequence) or.readObject();
        StringBuilder sb = new StringBuilder();
        
        while (encodedText.length() > 0) {
            Match m = binaryDecodingTrie.longestPrefixMatch(encodedText);
            sb.append(m.getSymbol());
            
            int length = m.getSequence().length();
            String s1 = encodedText.toString();
            String s2 = s1.substring(length);
            encodedText = new BitSequence(s2);
        }
        
        String output = sb.toString();
        char[] outputSymbols = output.toCharArray();
        ow.writeObject(outputSymbols);
    }
}
