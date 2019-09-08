package com.lawencon.linov.outsource.model.approval;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "item_requests")
public class ItemRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @NotBlank(message = "item can't be blank")
    @Size(min=3, max = 50, message = "name min length is 3 and max length is 50")
    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @NotBlank(message = "details can't be blank")
    @Size(min = 10, max = 250, message = "details min lenght is 10 and max length is 250")
    @Column(name = "details")
    private String details;

    @Column(name = "documents")
    private String documents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }
}
