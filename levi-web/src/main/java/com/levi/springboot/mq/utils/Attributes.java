package com.levi.springboot.mq.utils;

public interface Attributes {
    String REQUEST_INTERCEPTED_TRUE = "request_intercepted_true";
    String REQUEST_STARTED_AT = "request_started_at";
    String REQUEST_UNIQUE_KEY = "request_unique_key";
    String REQUEST_STANDARD_CONTENT = "request_standard_content";
    String REQUEST_CONTENT_BODY = "request_content_body";
    String REQUEST_CONTENT_HEADER = "request_content_header";
    String REQUEST_CONTENT_CONFIG = "request_content_config";
    String REQUEST_HEADER_INTERRELATED_ID = "request_header_interrelated_id";


    String REQUEST_ATTRIUTE_WRAPPER = "request_attribute_wrapper";
    String RESPONSE_ATTRIBUTE_WRAPPER = "response_attribute_wrapper";
}
