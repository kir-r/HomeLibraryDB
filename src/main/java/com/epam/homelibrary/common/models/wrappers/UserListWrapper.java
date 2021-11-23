package com.epam.homelibrary.common.models.wrappers;

import com.epam.homelibrary.common.models.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class UserListWrapper {
    @XmlElement
    private List<User> list;

    public UserListWrapper() {
        this.list = new ArrayList<>();
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }
}
