package com.epam.homelibrary.common.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class BookListWrapper {
    @XmlElement
    private List<Book> list;

    public BookListWrapper() {
        this.list = new ArrayList<>();
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

}
