public class HuffmanDecoder {
    public static void main(String[] args) {
        ObjectReader or = new ObjectReader(args[0]);
        
        BinaryTrie binaryDecodingTrie = (BinaryTrie) or.readObject();
        BitSequence encodedText = (BitSequence) or.readObject();
        StringBuilder charBuilder = new StringBuilder();
        int length = encodedText.length();
        
        while (length > 0) {
            Match m = binaryDecodingTrie.longestPrefixMatch(encodedText);
            charBuilder.append(m.getSymbol());
            int pos = m.getSequence().length();

            String original = encodedText.toString();
            String rest = original.substring(pos); 
            encodedText = new BitSequence(rest);

            length -= pos;
        }
        
        String output = charBuilder.toString();
        char[] outputSymbols = output.toCharArray();
        
        FileUtils.writeCharArray(args[1], outputSymbols);
    }
}
