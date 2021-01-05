package jp.co.demo.security.repository;

import jp.co.demo.common.BaseConst;
import jp.co.demo.security.service.ContextService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

/**
 * UserContextRepository
 */
@Component
public class UserContextRepository extends HttpSessionSecurityContextRepository {

    private ContextService contextService;

    public UserContextRepository(ContextService contextService) {
        this.contextService = contextService;
    }

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder holder) {
        SecurityContext context = super.loadContext(holder);

        return contextService.getSecurityContext(holder, context, BaseConst.UID);
    }
}
