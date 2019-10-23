package com.beikai.springbootmqkafkaconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2019/10/14
 * Time: 5:21 下午
 * Description: No Description
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserLog implements Serializable {

    private static final long serialVersionUID = -7319871177165642476L;

    private String username;
    private Integer age;
    private Long userId;

}
