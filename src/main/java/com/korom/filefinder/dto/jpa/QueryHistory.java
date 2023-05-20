package com.korom.filefinder.dto.jpa;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="query_history")
public class QueryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="username")
    private String username;
    @Column(name ="executed")
    private Date executed;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="queried_files", joinColumns = @JoinColumn(name = "id"))
    @Column(name="queried_files")
    private Set<QueriedFile> filePaths = new HashSet<>();

    public QueryHistory() {
    }

    public QueryHistory(String username, Date executed, Set<QueriedFile> filePaths) {
        this.username = username;
        this.executed = executed;
        this.filePaths = filePaths;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getExecuted() {
        return executed;
    }

    public void setExecuted(Date executed) {
        this.executed = executed;
    }

    public Set<QueriedFile> getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(Set<QueriedFile> filePaths) {
        this.filePaths = filePaths;
    }

    @Override
    public String toString() {
        return "QueryHistory{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", executed=" + executed +
                ", filePaths=" + filePaths +
                '}';
    }
}
