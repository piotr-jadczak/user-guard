package com.pj.userguard.client.entity;

import com.pj.userguard.util.jpa.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "client_configurations")
public class ClientConfiguration extends AuditableEntity {
}
