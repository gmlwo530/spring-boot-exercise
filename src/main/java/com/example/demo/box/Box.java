package com.example.demo.box;

import com.example.demo.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(
        name = "box",
        indexes = @Index(
                name = "idx_box_code",
                columnList = "code",
                unique = true
        )
)
public class Box {
    @Id
    @Column(name = "box_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String code;

    private String name;

    @OneToMany(mappedBy = "box", cascade = CascadeType.ALL)
    private Set<Item> items;
}
