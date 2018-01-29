package cn.huanuo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SysRole {

    @Id
    @GeneratedValue
    private Long id;
    private String name = "";
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<SysResource> resources = new ArrayList<SysResource>(0);


    public List<SysResource> getResources() {
        return resources;
    }

    public void setResources(List<SysResource> resources) {
        this.resources = resources;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
