package com.hari.sb.httpdemo.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // since Data annotation compalins about super calls methods we added this
public class Item extends AuditFields {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ItemId")
    Integer itemId;

    @Column(name = "itemName")
    @NonNull
    String itemName;

   /* @Embedded // Automatically maps the fields inside AuditMetadata to the items table
    private AuditFields audit = new AuditFields(); // Initialize to avoid NullPointerException

    public AuditFields getAudit() { return audit; }
    public void setAudit(AuditFields audit) { this.audit = audit; }*/
}
