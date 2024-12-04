package com.kyhns7.web.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterWrapper {

    public FilterWrapper(ServletRequest request, ServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public FilterWrapper(ServletRequest request, ServletResponse response, FilterChain chain) {
        this.request = request;
        this.response = response;
        this.chain = chain;
    }

    ServletRequest request;

    ServletResponse response;

    FilterChain chain;

    public ServletRequest getRequest() {
        return request;
    }

    public ServletResponse getResponse() {
        return response;
    }

    public FilterChain getChain() {
        return chain;
    }
}
