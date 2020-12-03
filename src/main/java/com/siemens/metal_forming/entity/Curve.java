package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.exception.exceptions.CurveCreationException;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE) @NoArgsConstructor @AllArgsConstructor @Builder(toBuilder = true)
@EqualsAndHashCode @ToString
@Entity @Table(name = "curves")
public class Curve {
    @EqualsAndHashCode.Exclude
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @OrderColumn(name = "index")
    @JoinColumn(name = "curve_id", nullable = false) //ToDo should be nullable=false but different point should be created
    List<CurvePoint> points = new ArrayList<>();

    public Curve(List<Float> torque, List<Float> speed){
        if(torque.size() != speed.size()) throw new CurveCreationException();

        points = IntStream.range(0,torque.size())
                .mapToObj(i -> new CurvePoint(torque.get(i), speed.get(i)))
                .collect(Collectors.toUnmodifiableList());
    }
}
