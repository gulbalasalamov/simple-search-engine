package model;

public class Document {
    static int ID = 0;
    int id;
    String text;

    public Document(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Document" + getId() + "\n" + getText();
    }
}
