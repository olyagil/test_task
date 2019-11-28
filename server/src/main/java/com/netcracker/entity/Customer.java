package com.netcracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = "modifiedWhen",
        allowGetters = true)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "customers_id_seq", initialValue = 26, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "first_name_metaphone")
    private String firstNameMetaphone;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "last_name_metaphone")
    private String lastNameMetaphone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_when", nullable = false)
    @LastModifiedDate
    private Date modifiedWhen;

    @ManyToOne
    @JoinColumn(name = "customer_types")
    private CustomerType type;


}
