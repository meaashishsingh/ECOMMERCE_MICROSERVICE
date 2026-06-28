package com.ecommerce.order.clients;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Optional;

@Configuration
public class ProductServiceClientConfig {

    // NORMAL builder
//    @Bean
//    public RestClient.Builder restClientBuilder() {
//        return RestClient.builder();
//    }

//     LOAD BALANCED RestClient
//    @Bean
//    @LoadBalanced
//    public RestClient.Builder loadBalancedRestClientBuilder() {
//        return RestClient.builder();
//    }

    @Bean
    public ProductServiceClient productServiceClient(RestClient.Builder loadBalancedRestClientBuilder) {

        RestClient restClient = loadBalancedRestClientBuilder.baseUrl("http://product-service").defaultStatusHandler(HttpStatusCode::is4xxClientError, ((request, response) -> Optional.empty())).build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(ProductServiceClient.class);
    }
}