import java.util.Date;

public class Block {
    public String hash; // digital signature
    public String previousHash; //hash of the previous block
    private String data; // data contained in block
    private long timeStamp;

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
                        data
        );
        return calculatedhash;
    }
}
