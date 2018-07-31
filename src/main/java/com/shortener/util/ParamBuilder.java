package com.shortener.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface ParamBuilder {

    static String getParams(Pageable pageable) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("?page=");
        buffer.append(String.valueOf(pageable.getPageNumber() + 1));
        buffer.append("&size=");
        buffer.append(String.valueOf(pageable.getPageSize()));
        pageable.getSort();
        buffer.append("&sort=");
        Sort sort = pageable.getSort();
        sort.forEach((order) -> {
            buffer.append(order.getProperty());
            if (order.getDirection() != Sort.Direction.ASC)
                buffer.append(",desc");
        });
        return buffer.toString();
    }

}
