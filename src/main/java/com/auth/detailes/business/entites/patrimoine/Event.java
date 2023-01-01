package com.auth.detailes.business.entites.patrimoine;

import com.auth.detailes.business.entites.common.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type_evenemnt")
    private String type;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    //@OneToMany(mappedBy = "event")
    //private Set<Notification> notifications = new HashSet<>();;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Patrimoine patrimoine;

}
