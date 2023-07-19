package com.example.action;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpUtils {

    /**
     * `server.forward-headers-strategy: native` 를 등록했는데도 적절한 ip가 안찾아졌음.
     * framework 로 등록했더니 미국 ip가 나옴.
     * 마지막 수단으로 직접 조회를 방식을 택했으나, X-Forwarded-For 헤더 만으로는 찾지 못함. (null)
     * 따라서 여러 폴백이 있는 방식 (아래 링크를 참고하여 작성) 을 택함
     * @see <a href="https://blog.yevgnenll.me/posts/find-client-ip-from-http-request-header">X-Forwarded-For</a>
     */
    private static final String[] IP_HEADER_CANDIDATES = {
        "X-Forwarded-For",
        "Proxy-Client-IP",
        "WL-Proxy-Client-IP",
        "HTTP_X_FORWARDED_FOR",
        "HTTP_X_FORWARDED",
        "HTTP_X_CLUSTER_CLIENT_IP",
        "HTTP_CLIENT_IP",
        "HTTP_FORWARDED_FOR",
        "HTTP_FORWARDED",
        "HTTP_VIA",
        "REMOTE_ADDR"
    };

    public static String getRemoteIp(HttpServletRequest request) {
        for (String headerKey: IP_HEADER_CANDIDATES) {
            String headerValue = request.getHeader(headerKey);
            if (isValid(headerValue)) {
                log.info("Forward header is matched. key:{}, value:{}", headerKey, headerValue);
                return headerValue.split(",")[0];
            }
        }
        String defaultAddr = request.getRemoteAddr();
        log.info("매치된 Forward header가 없어 기본값을 반환합니다. : {}", defaultAddr);
        return defaultAddr;
    }

    private static boolean isValid(String headerValue) {
        return StringUtils.isNotEmpty(headerValue)
            && !headerValue.equalsIgnoreCase("unknown");
    }

}
