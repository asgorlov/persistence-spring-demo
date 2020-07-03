package com.novaworld.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ango1019
 * Date: 02.07.2020
 * Time: 16:22
 */
@Entity
@Table(name = "officers")
public class Officer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Column(name = "first_name", nullable = false)
    private String first;

    @Column(name = "last_name", nullable = false)
    private String last;

    public Officer() {}

    public Officer(int id, Rank rank, String first, String last) {
        this.id = id;
        this.rank = rank;
        this.first = first;
        this.last = last;
    }

    public Officer(Rank rank, String first, String last) {
        this.rank = rank;
        this.first = first;
        this.last = last;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "Officer{" +
                "id=" + id +
                ", rank=" + rank +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Officer officer = (Officer) o;
        return id == officer.id &&
                rank == officer.rank &&
                Objects.equals(first, officer.first) &&
                Objects.equals(last, officer.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rank, first, last);
    }
}