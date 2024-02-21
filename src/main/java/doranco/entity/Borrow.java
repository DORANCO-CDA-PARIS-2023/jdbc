package doranco.entity;

import java.util.Date;

public class Borrow {
    private int id;
    private Date dateBorrow;
    private Date dateBackSchedulde;
    private Date dateBack;
    private int bookId;
    private int studentId;

    public Borrow() {}

    public Borrow(int id, Date dateBorrow, Date dateBackSchedulde, Date dateBack, int bookId, int studentId) {
        this.id = id;
        this.dateBorrow = dateBorrow;
        this.dateBackSchedulde = dateBackSchedulde;
        this.dateBack = dateBack;
        this.bookId = bookId;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(Date dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    public Date getDateBackSchedulde() {
        return dateBackSchedulde;
    }

    public void setDateBackSchedulde(Date dateBackSchedulde) {
        this.dateBackSchedulde = dateBackSchedulde;
    }

    public Date getDateBack() {
        return dateBack;
    }

    public void setDateBack(Date dateBack) {
        this.dateBack = dateBack;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", dateBorrow=" + dateBorrow +
                ", dateBackSchedulde=" + dateBackSchedulde +
                ", dateBack=" + dateBack +
                ", bookId=" + bookId +
                ", studentId=" + studentId +
                '}';
    }
}