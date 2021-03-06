import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class NerdessChain {

    // Array for storing blocks
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;

    public static void main(String[] args) {

        blockchain.add(new Block("This is the first block of Nerdesschain", "0"));
        System.out.println("Mining Block 1");
        blockchain.get(0).mineBlock(difficulty);
        blockchain.add(new Block("This is the second block of Nerdesschain", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Mining Block 2");
        blockchain.get(1).mineBlock(difficulty);
        blockchain.add(new Block("And this one is the third block of Nerdesschain", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Mining Block 3");
        blockchain.get(2).mineBlock(difficulty);

        // check our blockchain for validity
        System.out.println("\nBlockchain is Valid: " + isChainValid());

        // Just to see what we have in our blockchain and make sure it's working
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);

        /* this part of code is for testing output and adding blocks manually
        // first block is meant to be called "genesis block" so let's stick to tradition
        // since there's no previous blocks and hashes - we pass 0 instead of previous hash value
        Block genesisBlock = new Block("This is the first block of Nerdesschain", "0");
        System.out.println("Hash for genesis (first) block : " + genesisBlock.hash);

        Block blockTwo = new Block("This is the second block of Nerdesschain", genesisBlock.hash);
        System.out.println("Hash for block 2 : " + blockTwo.hash);

        Block blockThree = new Block("And this one is the third block of Nerdesschain", blockTwo.hash);
        System.out.println("Hash for block 3 : " + blockThree.hash);
        */
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        //check if each block has a solved ( by mining ) hash
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes for integrity
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.hash.equals(currentBlock.generateSignature())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved bt checking amount of 0's in the beginning
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
