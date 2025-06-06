package com.springboot.bakefinity.model.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.*;
import lombok.*;


@Data
@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name="userinterests")
public class UserInterest {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "categoryId", column = @Column(name = "categoryId", nullable = false)),
            @AttributeOverride(name = "userId", column = @Column(name = "userId", nullable = false))
    })
    private InterestsId id;

    @ManyToOne
//    @MapsId("userId")
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    @ManyToOne
//    @MapsId("categoryId")
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private Category category;

    public UserInterest(InterestsId id) {
        this.id = id;

    }

// Constructors, getters, setters


}
