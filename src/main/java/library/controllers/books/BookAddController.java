package library.controllers.books;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import library.entities.Author;
import library.repository.AuthorRepository;
import library.entities.Book;
import library.repository.BookRepository;

public class BookAddController implements Initializable {

    private final AuthorRepository authorRepository = new AuthorRepository();
    private final BookRepository bookRepository = new BookRepository();

    @FXML private TextField title;
    @FXML private TextField description;
    @FXML private TextField authorId;

    @FXML private StackPane rootPane;

    private Book editable;

    private Runnable closeDialogCallback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void addPostOperationCallback(Runnable callback) {
        this.closeDialogCallback = callback;
    }

    public void setEditable(Book book) {
        this.editable = book;
        this.title.setText(book.getTitle());
        this.description.setText(book.getDescription());
        this.authorId.setText(book.getAuthor().getId().toString());
    }

    @FXML
    private void addBook(ActionEvent event) {
        String bookTitle = title.getText();
        String bookDescription = description.getText();
        String bookAuthorId = authorId.getText();

        if (bookTitle.isEmpty() || bookDescription.isEmpty() || bookAuthorId.isEmpty()) {
            //TODO: show user alert that all fields have to be filled
            return;
        }

        Long authorId = Long.parseLong(bookAuthorId);
        Author author = authorRepository.findOne(authorId);
        if (author == null) {
            //TODO: author with such ID doesn't exist, display error to user!
            return;
        }

        if (editable == null) {
            bookRepository.save(new Book(bookTitle, bookDescription, author));
        } else {
            Book book = bookRepository.findOne(editable.getId());
            book.setTitle(bookTitle);
            book.setDescription(bookDescription);
            book.setAuthor(author);
            bookRepository.merge(book);
        }
        clearEntries();
        closeStage();
        closeDialogCallback.run();
    }

    @FXML
    private void cancel(ActionEvent event) {
        closeStage();
    }

    private void clearEntries() {
        editable = null;
        title.clear();
        description.clear();
        authorId.clear();
    }

    private void closeStage() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
