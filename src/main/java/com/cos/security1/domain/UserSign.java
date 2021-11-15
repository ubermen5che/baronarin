package com.cos.security1.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class UserSign {

    @Id @GeneratedValue
    @Column(name = "SIGN_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="email")
    private User user;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "IS_NFT")
    private String isNFT;

    public void setUser(User user){
        this.user = user;

        if(!user.getUserSigns().contains(this)){
            user.getUserSigns().add(this);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
