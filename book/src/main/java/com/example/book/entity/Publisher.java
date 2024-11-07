package com.example.book.entity;

import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Publisher {
    @SequenceGenerator(name = "book_publisher_seq_gen", sequenceName = "publisher_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_publisher_seq_gen")
    @Column(name = "publisher_id")
    @Id
    public Long id;

    @Column(name = "publisher_name", nullable = false)
    public String name;
}
