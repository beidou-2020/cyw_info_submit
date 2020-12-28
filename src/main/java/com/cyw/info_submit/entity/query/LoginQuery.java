package com.cyw.info_submit.entity.query;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ToString
public class LoginQuery implements Serializable {

    /**
     * 账号
     */
    @NotEmpty(message = "登录时账号不能为空")
    private String name;

    /**
     * 密码
     */
    @NotEmpty(message = "登录时密码不能为空")
    private String password;
}
