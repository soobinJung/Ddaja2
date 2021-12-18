package com.bng.ddaja.common.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.bng.ddaja.common.hateoas.CommonHateoas;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommonResource {
    private CommonDTO item;
    private List<Link> hateos;
    private String message;

    public CommonResource(CommonDTO item) {
        this.item = item;
    }
    public CommonResource(CommonDTO item, CommonHateoas[] hateos) {
        this.item = item;
        this.hateos = Arrays.stream(hateos).map(h -> h.initLink(item.getId())).collect(Collectors.toList());
    }

    public CommonResource(String message) {
        this.message = message;
    }
}
