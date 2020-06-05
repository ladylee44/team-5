package com.smartosc.team5.entities;

import com.smartosc.common.validate.annotation.ValidPrice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Team5
 *
 * @author Huupd
 * @created_at 04/06/2020 - 9:54 AM
 * @created_by Huupd
 */
@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @NotEmpty(message = "Please provide a name")
    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "price", precision = 20, scale = 3)
    @ValidPrice
    private double price;

    @CreationTimestamp
    @Column(name = "createdat", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updatedat")
    private Date updatedAt;

    @Column(name = "status")
    private int status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<OrderDetail> orderDetails;

}
