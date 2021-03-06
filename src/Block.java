import java.util.Date;

public class Block {
    public String hash; // digital signature
    public String previousHash; //hash of the previous block
    private String data; // data contained in block
    private long timeStamp;
    private int nonce; // the pseudo-random number calculated by miner

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = generateSignature(); // using our own method for this
    }

    // here we generate signature from those parts of the block we don’t want to be tampered with
    public String generateSignature() {
        String calculatedhash = HashGenerator.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }

    /*
    We will require miners to do proof-of-work by trying different variable values in the block
    until its hash starts with a certain number of 0’s.
    Low difficulty like 1 or 2 can be solved nearly instantly on most computers,
    4-6 is suggested for testing.
     */
    public void mineBlock(int difficulty) {
        // TODO same string is used in isValid - maybe can be united
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = generateSignature();
        }
        System.out.println("Block Mined: " + hash);
    }
}
