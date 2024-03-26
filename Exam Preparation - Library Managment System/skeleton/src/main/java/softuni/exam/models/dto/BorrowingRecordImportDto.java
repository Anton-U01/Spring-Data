package softuni.exam.models.dto;

import org.apache.tomcat.jni.Local;
import softuni.exam.util.LocalDateAdaptor;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "borrowing_record")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordImportDto {
    @XmlElement(name = "borrow_date")
    @XmlJavaTypeAdapter(LocalDateAdaptor.class)
    private LocalDate borrowDate;
    @XmlElement(name = "return_date")
    @XmlJavaTypeAdapter(LocalDateAdaptor.class)
    private LocalDate returnDate;
    @XmlElement
    private BookSeedDto book;
    @XmlElement
    private MemberSeedDto member;
    @XmlElement
    @Size(min = 3,max = 100)
    private String remarks;


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BookSeedDto getBook() {
        return book;
    }

    public void setBook(BookSeedDto book) {
        this.book = book;
    }

    public MemberSeedDto getMember() {
        return member;
    }

    public void setMember(MemberSeedDto member) {
        this.member = member;
    }
}
