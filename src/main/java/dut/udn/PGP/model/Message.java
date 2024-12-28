package dut.udn.PGP.model;

public class Message {
    private String from;
    private String to;
    private String content;
    private String[] ciphertext;

    @Override
    public String toString() {
        return super.toString();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String[] getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String[] ciphertext) {
        this.ciphertext = ciphertext;
    }
}