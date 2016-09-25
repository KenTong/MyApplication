package app_aad_exam.app_firebasecrud;

public class Chat {

    private String message;
    private String author;

    public Chat() {

    }
    public Chat(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}