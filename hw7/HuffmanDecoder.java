public class HuffmanDecoder {
    public static void main(String[] args) {
        ObjectReader or = new ObjectReader(args[0]);
        
        BinaryTrie binaryDecodingTrie = (BinaryTrie) or.readObject();
        BitSequence encodedText = (BitSequence) or.readObject();
        StringBuilder sb = new StringBuilder();
        
        while (encodedText.length() > 0) {
            Match m = binaryDecodingTrie.longestPrefixMatch(encodedText);
            sb.append(m.getSymbol());
            
            int pos = m.getSequence().length();
            BitSequence restSequence = new BitSequence(); 
            for (int i = pos; i < encodedText.length(); i += 1) {
                restSequence.appended(encodedText.bitAt(i));
            }
            encodedText = restSequence;
        }
        
        String output = sb.toString();
        char[] outputSymbols = output.toCharArray();
        
        FileUtils.writeCharArray(args[1], outputSymbols);
    }
}
