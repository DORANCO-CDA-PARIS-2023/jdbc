package doranco.entity;

import java.util.Date;

public final class Borrow {

    private int id;
    private Date dateBorrow;
    private Date dateBackSchedulde;
    private Date dateBack;
    private int idBook;
    private int idStudent;

    public Borrow() {
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

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }
}
