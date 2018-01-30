package cn.huanuo.service.sys;

import cn.huanuo.dao.sys.SysReSourceRepository;
import cn.huanuo.dao.sys.SysRoleRepository;
import cn.huanuo.entity.SysResource;
import cn.huanuo.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    SysReSourceRepository sysReSourceRepository;

    @Autowired
    SysRoleRepository sysRoleRepository;


    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @PostConstruct
    public void loadResource() {

        List<SysRole> list = sysRoleRepository.findAll();

        resourceMap = new HashMap<>();

        if (list != null && list.size() > 0) {
            for (SysRole sysRole : list) {

                ConfigAttribute ca = new SecurityConfig(sysRole.getName());
                List<SysResource> resources = sysRole.getResources();
                if (resources != null && resources.size() > 0) {
                    for (SysResource resource : resources) {
                        String url = resource.getUrl();

                        if (resourceMap.containsKey(url)) {
                            Collection<ConfigAttribute> roles = new ArrayList<>();
                            roles.add(ca);

                        } else {
                            Collection<ConfigAttribute> roles = new ArrayList<>();
                            roles.add(ca);
                            resourceMap.put(url, roles);
                        }

                    }
                }
            }
        }


    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        FilterInvocation filterInvocation = (FilterInvocation) o;
        if (resourceMap == null)
            loadResource();

        Iterator<String> ite = resourceMap.keySet().iterator();

        while (ite.hasNext()) {
            String url = ite.next();
            RequestMatcher matcher = new AntPathRequestMatcher(url);
            if (matcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(url);
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
