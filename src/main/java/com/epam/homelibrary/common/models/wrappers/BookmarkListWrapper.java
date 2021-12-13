package com.epam.homelibrary.common.models.wrappers;

import com.epam.homelibrary.common.models.Bookmark;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class BookmarkListWrapper {
    @XmlElement
    private List<Bookmark> list;

    public BookmarkListWrapper() {
        this.list = new ArrayList<>();
    }

    public List<Bookmark> getList() {
        return list;
    }

    public void setList(List<Bookmark> list) {
        this.list = list;
    }
}
