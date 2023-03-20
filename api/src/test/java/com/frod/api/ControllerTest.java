package com.frod.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frod.api.constant.ApiType;
import com.frod.api.constant.SortType;
import com.frod.api.controller.BlogSearchController;
import com.frod.api.dto.request.BlogSearchRequest;
import com.frod.api.service.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    @InjectMocks
    private BlogSearchController controller;
    @Mock
    private SearchService searchService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    @DisplayName("블로그 검색 API_파라미터_검증_테스트")
    public void paramValid() throws Exception {
        //given
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("keyword","");
        params.add("apiType",ApiType.KAKAO.name());
        params.add("pageNo","1");
        params.add("pageSize","10");
        params.add("sortType",SortType.ACCURACY_ORD.name());

        MultiValueMap<String,String> params2 = new LinkedMultiValueMap<>();
        params2.add("keyword","테스트");
        params2.add("apiType",ApiType.KAKAO.name());
        params2.add("pageNo","1");
        params2.add("pageSize","10");
        params2.add("sortType",SortType.ACCURACY_ORD.name());

        //when & then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/blog-search")
                        .params(params))
                .andExpect(status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/blog-search")
                        .params(params2))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("인기검색어 API 응답 테스트")
    public void rankApiTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/blog-search/rank"))
                .andExpect(status().isOk());
    }
}
