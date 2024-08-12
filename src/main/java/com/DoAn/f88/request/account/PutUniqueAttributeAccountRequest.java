package com.DoAn.f88.request.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutUniqueAttributeAccountRequest {
    private String id;
    private String attribute;
    private String newAttribute;
}
