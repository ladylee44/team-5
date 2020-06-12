package com.smartosc.team5.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 11/06/2020 - 03:28 PM
 * @created_by ThaoPhuong
 * @since 11/06/2020
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ApiLog {
    @Id
    @GeneratedValue
    private Long id;
    private Date calledTime;
    private String data;
    private String errorMessage;
    private int retryNum;
}
