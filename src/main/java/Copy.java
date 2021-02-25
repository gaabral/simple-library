import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@Setter
public class Copy {
    private final long id;
    private final Book book;
    private String borrowerName;
    private boolean lent;

    public Copy(Book book, MyIdSequence idSequence) {
        this.book = book;
        this.id = idSequence.getNextValue();
    }

    public long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public boolean isLent() {
        return lent;
    }

    public void setLent(boolean lent) {
        this.lent = lent;
    }
}
