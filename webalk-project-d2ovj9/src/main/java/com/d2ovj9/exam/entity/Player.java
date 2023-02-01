package com.d2ovj9.exam.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "players")
public class Player {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotBlank(message = "This field cannot be blank!")
    @Size(min = 2, max = 30, message = "Must be at least 2 characters")
    @Pattern(regexp="^[A-Za-z]*$",message = "Name cannot contain numbers!")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "This field cannot be blank!")
    @Size(min = 2, max = 30, message = "Must be at least 2 characters")
    @Pattern(regexp="^[A-Za-z]*$",message = "Name cannot contain numbers!")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "day_of_birth")
    @NotNull(message = "This field cannot be blank!")
    private Date dayOfBirth;


    @Enumerated(value = EnumType.STRING)
    @Column(name = "position")
    private Position position;

    // Height / Weight validáció üzenet a messages.properties-ből jön, anélkül csúnya
    @Column(name = "height")
    @Min(value = 0, message = "Height must be greater than zero")
    @NotNull(message = "This field cannot be blank!")
    private Integer height;

    @Column(name = "weight")
    @DecimalMin(value = "0.0", message = "Weight must be greater than zero")
    @NotNull(message = "This field cannot be blank!")
    private Double weight;

    public Player() {}

    public Player(int id, String firstName, String lastName, Date dayOfBirth, Position position, int height, double weight) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dayOfBirth = dayOfBirth;
        this.position = position;
        this.height = height;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dayOfBirth=" + dayOfBirth +
                ", position=" + position +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
