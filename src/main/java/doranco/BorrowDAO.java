package doranco;

import java.util.List;

public interface BorrowDAO {
    Borrow getBorrowById(int id);

    List<Borrow> getAllBorrows();

    void addBorrow(Borrow borrow);

    void updateBorrow(Borrow borrow);

    void deleteBorrow(int id);
}
