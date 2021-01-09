package library.repository;

import java.util.List;
import library.entities.Book;

public class BookRepository extends CrudRepository<Book> {

    private static final String HIBERNATE_SELECT_QUERY = "from Book";

    public Book findOne(Long id) {
        return super.findOne(id, Book.class);
    }

    public List<Book> findAll() {
        return super.findAll(HIBERNATE_SELECT_QUERY, Book.class);
    }
}
