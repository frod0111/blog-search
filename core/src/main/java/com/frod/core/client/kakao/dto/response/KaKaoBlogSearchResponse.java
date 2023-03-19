package com.frod.core.client.kakao.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KaKaoBlogSearchResponse {
    private MetaResponse meta;
    private List<DocumentsResponse> documents;
}
