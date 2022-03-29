package com.lefarmico.springjwtwebservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private Long clientId;

    @Column
    private Long categoryId;

    @Column
    private Long nextQuizTime;

    @Column
    private Long wordsInTest;

    @Column
    private Long languageId;
}