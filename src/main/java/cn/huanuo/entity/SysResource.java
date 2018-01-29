package cn.huanuo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class SysResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;

    @Column(length = 1000)
    private String url;//url

    @Column(name = "resourceId", length = 50)
    private String resourceId;//资源ID

    @Column(length = 200)
    private String remark;//备注

    @Column(length = 400)
    private String resourceName;//资源名称

    @Column(length = 400)
    private Integer permission;//资源所对应的方法名

    @Column(length = 400)
    private String translate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SysResource)) return false;
        SysResource that = (SysResource) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUrl(), that.getUrl()) &&
                Objects.equals(getResourceId(), that.getResourceId()) &&
                Objects.equals(getRemark(), that.getRemark()) &&
                Objects.equals(getResourceName(), that.getResourceName()) &&
                Objects.equals(getPermission(), that.getPermission()) &&
                Objects.equals(getTranslate(), that.getTranslate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUrl(), getResourceId(), getRemark(), getResourceName(), getPermission(), getTranslate());
    }
}
