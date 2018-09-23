package com.boot.contract.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class User extends BaseEntity{
    private String userId;
    private String userPassword;
    private String userName;
    private String userAddress;
    private String userPhone;
    private String userEmail;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> board;
}
