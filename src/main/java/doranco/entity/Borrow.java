package doranco.entity;

import java.util.Date;

public class Borrow {
    private int id;
    private Date dateBorrow;
    private Date dateBackScheduled;
    private Date dateBack;
    private int idBook;
    private int idStudent;


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

    public Date getDateBackScheduled() {
        return dateBackScheduled;
    }

    public void setDateBackScheduled(Date dateBackScheduled) {
        this.dateBackScheduled = dateBackScheduled;
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