package com.bng.ddaja.common.dto;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.bng.ddaja.common.enums.CommonEnum;
import com.bng.ddaja.common.hateoas.CommonHateoas;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {
    private int totalCount;
    public CommonPage page;
    private List<? extends CommonResource> items;
    private CommonDTO item;

    public CommonResponse(List<? extends CommonDTO> dtoList) {
        items = dtoList.stream().map(d -> new CommonResource(d)).collect(Collectors.toList());
        totalCount = dtoList.size();
    }

    public CommonResponse(int totalCount, CommonResource item) {
        this.totalCount = totalCount;
        List<CommonResource> resourceList = new LinkedList<>();
        resourceList.add(item);
        this.items = resourceList;
    }

    public CommonResponse(int totalCount, List<CommonResource> items) {
        this.totalCount = totalCount;
        this.items = items;
    }

    public CommonResponse(Page<? extends CommonDTO> pageDTO, CommonHateoas[] hateos) {
        items = pageDTO.stream().map(dto -> new CommonResource(dto, hateos)).collect(Collectors.toList());
        totalCount = pageDTO.getSize();
        page = new CommonPage(pageDTO);
    }

    public CommonResponse(CommonEnum[] searchOptions) {
        items = Arrays.stream(searchOptions).map(e -> e.toCommonEnumResource()).collect(Collectors.toList());
        totalCount = searchOptions.length;
    }

    public CommonResponse(CommonDTO item) {
        this.totalCount = 1;
        this.item = item;
    }
}
