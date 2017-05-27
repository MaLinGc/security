package com.ml.security;

import com.ml.entity.Resource;
import com.ml.entity.RoleResource;
import com.ml.repository.ResourceRepository;
import com.ml.repository.RoleResourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 资源源数据定义，即定义某一资源可以被哪些角色访问
 */
@Slf4j
@Component
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private RoleResourceRepository roleResourceRepository;

    private LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> resourceMap = null;

    @PostConstruct
    private void loadResourceDefine() {
        if (resourceMap == null)
            resourceMap = new LinkedHashMap<>();
        List<Resource> resources = resourceRepository.findAll();
        resources.stream().forEach(resource -> {
            List<RoleResource> roleResources = roleResourceRepository.findByResourceId(resource.getId());
            List<ConfigAttribute> configAttributes = roleResources.stream()
                                                                  .map(roleResource -> new SecurityConfig(roleResource.getRole().getName()))
                                                                  .collect(Collectors.toList());
            resourceMap.put(new AntPathRequestMatcher(resource.getUrl()), configAttributes);
        });
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<>();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }
        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
