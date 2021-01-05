package jp.co.demo.security.service;

import jp.co.demo.common.BaseConst;
import jp.co.demo.security.AccountDetails;
import jp.co.demo.security.repository.ContextRepository;
import jp.co.demo.security.utils.AuthenticationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ContextService
 */
@Configuration
@Slf4j
public class ContextService {

    private ContextRepository contextRepository;

    public ContextService(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }

    /**
     * Login successfully stores SecurityContext in Redis
     */
    public void storesSecurityContextInRedis(HttpServletRequest request) throws IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        if (null != context && null != context.getAuthentication() && null != context.getAuthentication().getPrincipal()) {
            contextRepository.saveContext(
                    request.getSession().getId(),
                    AuthenticationUtils.toSerializableString(context),
                    request.getSession().getMaxInactiveInterval());
        }
    }

    /**
     * Remove security context by session id
     */
    public void removeSecurityContextBySessionId(HttpServletRequest request) {
        contextRepository.deleteKey(request.getSession().getId());
    }

    /**
     * Remove security context by cookie
     */
    public void removeSecurityContextByCookie(HttpServletRequest request) {
        contextRepository.deleteKey(AuthenticationUtils.getSid(request.getCookies()));
    }

    public SecurityContext getSecurityContext(HttpRequestResponseHolder holder, SecurityContext context, String id) {
        if (null == context || null == context.getAuthentication() || null == context.getAuthentication().getPrincipal()) {
            Object contextString = contextRepository.findContextById(holder.getRequest().getSession().getId());
            if (null != contextString) {
                try {
                    context = (SecurityContext) AuthenticationUtils.fromSerializableString(contextString.toString());
                } catch (IOException | ClassNotFoundException e) {
                    log.error("Cannot deserialize security context !!!");
                }
            }

            if (null == contextString && context != null) {
                context.setAuthentication(null);
            }
        } else if ((context.getAuthentication().getPrincipal() instanceof AccountDetails && !BaseConst.UID.equals(id))) {
            context.setAuthentication(null);
        }

        return context;
    }
}
