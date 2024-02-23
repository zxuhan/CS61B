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

            encodedText = encodedText.allButFirstNBits(pos);

            length -= pos;
        }
        
        String output = charBuilder.toString();
        char[] outputSymbols = output.toCharArray();
        
        FileUtils.writeCharArray(args[1], outputSymbols);
    }
}
