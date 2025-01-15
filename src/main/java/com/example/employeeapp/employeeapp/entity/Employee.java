package com.example.employeeapp.employeeapp.entity;

import com.example.employeeapp.employeeapp.encryption.Encryption;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    @SequenceGenerator(name = "id_sequence", sequenceName = "ID_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "department")
    private String department;

    @Column(name = "salary")
    private BigDecimal salary;

    public void setEmail(String email) {
        try {
            this.email = Encryption.encrypt(email);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting email", e);
        }
    }

    public String getEmail() {
        try {
            return Encryption.decrypt(this.email);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting email", e);
        }
    }
}
