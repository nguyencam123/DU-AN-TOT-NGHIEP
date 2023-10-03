package com.example.demo.models;

import com.example.demo.models.Embeddable.DetailBookingId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "detailbooking")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DetailBooking {

    @EmbeddedId
    private DetailBookingId id;

    @Column(name = "datestart")
    private Date dateStart;

    @Column(name = "dateend")
    private Date dateEnd;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "booking")
    private CancellationPolicyRoom cancellationPolicyRoom;
}
