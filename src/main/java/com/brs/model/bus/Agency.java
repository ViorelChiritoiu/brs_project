package com.brs.model.bus;

import com.brs.model.user.UserAccount;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    @NotNull
    private String details;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private UserAccount owner;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    private List<Bus> buses;

    public Agency() {
    }

    public Agency(@NotNull String code, @NotNull String name, @NotNull String details,
                  @NotNull UserAccount owner, List<Bus> buses) {
        this.code = code;
        this.name = name;
        this.details = details;
        this.owner = owner;
        this.buses = buses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public UserAccount getOwner() {
        return owner;
    }

    public void setOwner(UserAccount owner) {
        this.owner = owner;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agency agency = (Agency) o;
        return Objects.equals(id, agency.id) &&
                Objects.equals(code, agency.code) &&
                Objects.equals(name, agency.name) &&
                Objects.equals(details, agency.details) &&
                Objects.equals(owner, agency.owner) &&
                Objects.equals(buses, agency.buses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, details, owner, buses);
    }

    @Override
    public String toString() {
        return "Agency{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", owner=" + owner +
                ", buses=" + buses +
                '}';
    }
}
