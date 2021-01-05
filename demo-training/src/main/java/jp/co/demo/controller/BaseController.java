package jp.co.demo.controller;

import jp.co.demo.common.BaseConst;
import jp.co.demo.exception.DemoServletException;
import jp.co.demo.security.LoginInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * BaseController
 */
public class BaseController {

    // ID
    protected static final String ID = "ID";
    // attribute sort
    protected static final String SORT = "sort";
    // attribute sort property
    protected static final String SORT_PROPERTY = "property";
    // attribute direction
    protected static final String DIRECTION = "direction";
    // attribute page
    protected static final String PAGE = "page";
    // attribute size
    protected static final String SIZE = "size";
    // attribute size default
    protected static final int SIZE_DEFAULT = 20;
    // attribute page number
    protected static final String PAGE_NUMBERS = "pageNumbers";
    // attribute total page
    protected static final String TOTAL_PAGES = "totalPages";
    // attribute total record
    protected static final String TOTAL_RECORD = "totalRecord";
    // attribute search condition
    protected static final String SEARCH_CONDITION = "searchCondition";
    // attribute condition maps
    protected static final String CONDITION_MAPS = "conditionMaps";
    // attribute list url
    protected static final String LIST_URL = "listUrl";
    // attribute sort default
    protected static final String SORT_DEFAULT = "created";
    // attribute logout
    protected static final String LOGOUT = "logout";
    // attribute verified
    protected static final String VERIFIED = "verified";
    // attribute reset password
    protected static final String RESET_PASSWORD = "resetPassword";
    // attribute key
    protected static final String KEY = "key";
    // attribute hash
    protected static final String HASH = "hash";
    // attribute search
    protected static final String SEARCH = "search";
    // attribute facility code default
    protected static final String PAGE_SIZE_MAP = "pageSizeMaps";

    protected LoginInfo getLoginInfo(HttpSession session) throws DemoServletException {
        LoginInfo loginInfo = (LoginInfo) session.getAttribute(BaseConst.USER_SESSION);
        if (Objects.nonNull(loginInfo)) {
            return loginInfo;
        }
        throw DemoServletException.permissionDenied();
    }

    protected String getBaseUrl(HttpServletRequest request) {
        return String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());
    }

    /**
     * Set pagination attribute
     *
     * @param pageable    pageable
     * @param totalRecord total record of list
     * @param listUrl     base url of page
     * @param model       model of page
     * @param <T>         type of search condition
     */
    protected <T> void setPaginationAttribute(Pageable pageable,
                                              long totalRecord,
                                              String listUrl,
                                              Model model,
                                              Map<String, T> conditionMap) {
        setCurrentSort(pageable.getSort(), model);
        setCurrentSize(pageable.getPageSize(), model);
        setCurrentPage(pageable.getPageNumber(), model);
        setPageNumbers(totalRecord, pageable.getPageSize(), model);
        setBaseURL(listUrl, model);
        setConditionMap(conditionMap, model);
        setSearchCondition(conditionMap, model);
        setPageSizeMap(conditionMap, pageable.getSort(), model);
    }

    /**
     * Set pagination attribute
     *
     * @param pageable        pageable
     * @param totalRecord     total record of list
     * @param listUrl         base url of page
     * @param model           model of page
     * @param searchCondition search condition
     * @param <T>             type of search condition
     */
    protected <T> void setPaginationAttribute(Pageable pageable,
                                              long totalRecord,
                                              String listUrl,
                                              Model model,
                                              T searchCondition,
                                              String searchConditionKey) {
        setCurrentSort(pageable.getSort(), model);
        setCurrentSize(pageable.getPageSize(), model);
        setCurrentPage(pageable.getPageNumber(), model);
        setPageNumbers(totalRecord, pageable.getPageSize(), model);
        setBaseURL(listUrl, model);
        setSearchCondition(searchCondition, model);

        Map<String, T> conditionMap = new HashMap<>();
        conditionMap.put(searchConditionKey, searchCondition);
        setConditionMap(conditionMap, model);
        setPageSizeMap(conditionMap, pageable.getSort(), model);
    }

    // set current page
    private void setCurrentPage(int page, Model model) {
        model.addAttribute(PAGE, page);
    }

    // set current size
    private void setCurrentSize(int size, Model model) {
        model.addAttribute(SIZE, size);
    }

    // set current sort
    private void setCurrentSort(Sort sort, Model model) {
        StringBuilder orderBy = new StringBuilder();
        Sort.Direction direction = Sort.Direction.ASC;
        String property = StringUtils.EMPTY;

        if (sort.stream().findAny().isPresent()) {
            Sort.Order order = sort.stream().findAny().get();
            direction = order.getDirection();
            property = order.getProperty();

            orderBy.append(property);
            orderBy.append(",");
            orderBy.append(direction);
        }

        model.addAttribute(SORT_PROPERTY, property);
        model.addAttribute(DIRECTION, direction);
        model.addAttribute(SORT, orderBy);
    }

    // set page number
    private void setPageNumbers(long totalRecord, int limit, Model model) {
        long totalPages = totalRecord > 0 ? ((totalRecord - 1) / limit) + 1 : 0;

        if (totalPages > 0) {
            model.addAttribute(PAGE_NUMBERS, LongStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList()));
            model.addAttribute(TOTAL_PAGES, totalPages);
            model.addAttribute(TOTAL_RECORD, totalRecord);
        } else {
            model.addAttribute(PAGE_NUMBERS, new ArrayList<>());
            model.addAttribute(TOTAL_PAGES, totalPages);
            model.addAttribute(TOTAL_RECORD, totalPages);
        }
    }

    // set base url of page
    private void setBaseURL(String listUrl, Model model) {
        model.addAttribute(LIST_URL, listUrl);
    }

    // set search condition of request
    private <T> void setSearchCondition(T searchCondition, Model model) {
        model.addAttribute(SEARCH_CONDITION, searchCondition);
    }

    // set search condition of request
    private <T> void setSearchCondition(Map<String, T> conditionMaps, Model model) {
        if (conditionMaps != null) {
            conditionMaps.forEach(model::addAttribute);
        }
    }

    // set condition maps
    private <T> void setConditionMap(Map<String, T> conditionMaps, Model model) {
        StringBuilder condition = new StringBuilder();
        condition.append("?page={page}");
        condition.append("&size={size}");
        condition.append("&sort={sort}");
        if (conditionMaps != null) {
            conditionMaps.forEach((key, value) -> {
                if (Objects.nonNull(value)) {
                    condition.append("&");
                    condition.append(key);
                    condition.append("=");
                    condition.append(value);
                }
            });
        }
        model.addAttribute(CONDITION_MAPS, condition);
    }

    // set page size maps
    private <T> void setPageSizeMap(Map<String, T> conditionMaps, Sort sort, Model model) {
        StringBuilder condition = new StringBuilder();
        condition.append("?page=0");
        condition.append("&sort=");

        if (sort != null) {
            sort.stream().forEach(order -> {
                condition.append(order.getProperty());
                condition.append(",");
                condition.append(order.getDirection());
            });
        }

        if (conditionMaps != null) {
            conditionMaps.forEach((key, value) -> {
                if (Objects.nonNull(value)) {
                    condition.append("&");
                    condition.append(key);
                    condition.append("=");
                    condition.append(value);
                }
            });
        }

        model.addAttribute(PAGE_SIZE_MAP, condition);
    }
}
