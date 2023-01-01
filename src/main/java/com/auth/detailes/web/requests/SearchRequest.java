package com.auth.detailes.web.requests;

import lombok.Data;

/**
 * @author 	: Yassin OUICHOU | Ouichou.IT@gmail.com
 */

@Data
public class SearchRequest {

    private int size = 20;
    private int page = 0;
    private String sort = "asc";
    private String sortColumn = "id";

    private String dateCreationfrom;
    private String dateCreationTo;
    private Long createBy;
}
