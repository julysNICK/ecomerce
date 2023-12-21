package com.julys.eccomerce.eccomerce.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julys.eccomerce.eccomerce.config.SecurityConfiguration;
import com.julys.eccomerce.eccomerce.entity.Product;
import com.julys.eccomerce.eccomerce.error.ErrorBuilder;
import com.julys.eccomerce.eccomerce.response.product.ProductResponse;
import com.julys.eccomerce.eccomerce.rest.ProductController;
import com.julys.eccomerce.eccomerce.service.JwtService;
import com.julys.eccomerce.eccomerce.service.product.ProductService;
import com.julys.eccomerce.eccomerce.validator.ProductValidator;

import io.micrometer.core.ipc.http.HttpSender.Response;

import java.util.List;

import java.math.BigDecimal;

@WebMvcTest(ProductController.class)
@Import(SecurityConfiguration.class)
public class ProductControllerTest {
        @MockBean
        private ProductService productService;

        @MockBean
        private JwtService jwtService;

        @MockBean
        private AuthenticationProvider authenticationProvider;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void test_requisition_without_jwt() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/api/products/hello"))
                                .andExpect(MockMvcResultMatchers.status().isForbidden());
        }

        @WithMockUser
        @Test
        public void test_create_product_status_success() throws Exception {

                Product product = createProduct("Teste", "Teste", 10.0, 10L);

                Mockito.doReturn(ResponseEntity.status(HttpStatus.CREATED).body(product))
                                .when(productService).createProduct(Mockito.any(Product.class));

                mockMvc.perform(MockMvcRequestBuilders.post("/api/products/")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(product)))
                                .andExpect(MockMvcResultMatchers.status().isCreated());
        }

        @WithMockUser
        @Test
        public void test_create_product_json_success() throws Exception {

                Product product = createProduct("Teste", "Teste", 10.0, 10L);

                ProductResponse productResponse = createProductResponse(product);

                Mockito.doReturn(ResponseEntity.status(HttpStatus.CREATED).body(productResponse))
                                .when(productService).createProduct(Mockito.any(Product.class));

                mockMvc.perform(MockMvcRequestBuilders.post("/api/products/")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(product)))
                                .andExpect(MockMvcResultMatchers.content()
                                                .json(objectMapper.writeValueAsString(productResponse)));
        }

        @WithMockUser
        @Test
        public void test_create_product_argument_blank_status() throws Exception {

                Product product = createProduct("", "", 10.0, 10L);

                Mockito.doReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product))
                                .when(productService).createProduct(Mockito.any(Product.class));

                mockMvc.perform(MockMvcRequestBuilders.post("/api/products/")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(product)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

        @WithMockUser
        @Test
        public void test_create_product_argument_blank_json() throws Exception {

                Product product = createProduct("", "", 10.0, 10L);

                List<String> errors = new ProductValidator().validateProduct(product);

                ResponseEntity errorsJson = new ErrorBuilder().buildResponseEntity(errors,
                                HttpStatus.BAD_REQUEST);

                Mockito.doReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsJson.getBody()))
                                .when(productService).createProduct(Mockito.any(Product.class));

                mockMvc.perform(MockMvcRequestBuilders.post("/api/products/")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(product)))
                                .andExpect(MockMvcResultMatchers.content()
                                                .json(objectMapper.writeValueAsString(errorsJson.getBody())));
        }

        // delete
        @WithMockUser
        @Test
        public void test_delete_product_success_status() throws Exception {
                Product product = createProduct("Teste", "Teste", 10.0, 10L);

                Mockito.doReturn(ResponseEntity.status(HttpStatus.OK).body(product))
                                .when(productService).deleteProduct(Mockito.anyLong());

                mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(product)))
                                .andExpect(MockMvcResultMatchers.status().isOk());

        }

        @WithMockUser
        @Test
        public void test_delete_product_success_json() throws Exception {
                Product product = createProduct("Teste", "Teste", 10.0, 10L);

                ProductResponse productResponse = deleteProductResponse(product);

                Mockito.doReturn(ResponseEntity.status(HttpStatus.OK).body(productResponse))
                                .when(productService).deleteProduct(Mockito.anyLong());

                mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(product)))
                                .andExpect(MockMvcResultMatchers.content()
                                                .json(objectMapper.writeValueAsString(productResponse)));

        }

        @WithMockUser
        @Test
        public void test_delete_product_not_found_status() throws Exception {
                Product product = createProduct("Teste", "Teste", 10.0, 10L);

                Mockito.doReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product))
                                .when(productService).deleteProduct(Mockito.anyLong());

                mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(product)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        }

        @WithMockUser
        @Test
        public void test_delete_product_not_found_json() throws Exception {
                Product product = createProduct("Teste", "Teste", 10.0, 10L);

                List<String> errors = List.of("Product not found");

                ResponseEntity errorsJson = new ErrorBuilder().buildResponseEntity(errors,
                                HttpStatus.NOT_FOUND);

                Mockito.doReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsJson.getBody()))
                                .when(productService).deleteProduct(Mockito.anyLong());
                mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(product)))
                                .andExpect(MockMvcResultMatchers.content()
                                                .json(objectMapper.writeValueAsString(errorsJson.getBody())));

        }

        // test patch
        @WithMockUser
        @Test
        public void test_patch_product_success_status() throws Exception {
                Product product = createProduct("Teste", "Teste", 10.0, 10L);

                Mockito.doReturn(ResponseEntity.status(HttpStatus.OK).body(product))
                                .when(productService).updateProduct(Mockito.anyLong(),
                                                Mockito.any(Product.class));
                mockMvc.perform(MockMvcRequestBuilders.patch("/api/products/update/1")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(product)))
                                .andExpect(MockMvcResultMatchers.status().isOk());

        }

        @WithMockUser
        @Test
        public void test_patch_product_success_json() throws Exception {
                Product product = createProduct("Teste", "Teste", 10.0, 10L);

                ProductResponse productResponse = updateProductResponse(product);

                Mockito.doReturn(ResponseEntity.status(HttpStatus.OK).body(productResponse))
                                .when(productService).updateProduct(Mockito.anyLong(),
                                                Mockito.any(Product.class));

                mockMvc.perform(MockMvcRequestBuilders.patch("/api/products/update/1")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(product)))
                                .andExpect(MockMvcResultMatchers.content()
                                                .json(objectMapper.writeValueAsString(productResponse)));

        }

        @WithMockUser
        @Test
        public void test_patch_product_not_found_status() throws Exception {
                Product product = createProduct("Teste", "Teste", 10.0, 10L);

                Mockito.doReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product))
                                .when(productService).updateProduct(Mockito.anyLong(),
                                                Mockito.any(Product.class));

                mockMvc.perform(MockMvcRequestBuilders.patch("/api/products/update/1")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(product)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        }

        @WithMockUser
        @Test
        public void test_patch_product_not_found_json() throws Exception {
                Product product = createProduct("Teste", "Teste", 10.0, 10L);

                List<String> errors = List.of("Product not found");

                ResponseEntity errorsJson = new ErrorBuilder().buildResponseEntity(errors,
                                HttpStatus.NOT_FOUND);

                Mockito.doReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsJson.getBody()))
                                .when(productService).updateProduct(Mockito.anyLong(),
                                                Mockito.any(Product.class));

                mockMvc.perform(MockMvcRequestBuilders.patch("/api/products/update/1")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(product)))
                                .andExpect(MockMvcResultMatchers.content()
                                                .json(objectMapper.writeValueAsString(errorsJson.getBody())));

        }

        @WithMockUser
        @Test
        public void test_patch_product_id_null_status() throws Exception {
                Product product = createProduct("Teste", "Teste", 10.0, 10L);

                Mockito.doReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product))
                                .when(productService).updateProduct(Mockito.anyLong(),
                                                Mockito.any(Product.class));

                mockMvc.perform(MockMvcRequestBuilders.patch("/api/products/update/")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(product)))
                                .andExpect(MockMvcResultMatchers.status().isNotFound());

        }

        private Product createProduct(String name, String description, Double priceArgument, Long stock) {
                BigDecimal price = BigDecimal.valueOf(priceArgument);

                BigDecimal stockConv = new BigDecimal(stock);

                Product product = new Product();
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                product.setStock(stockConv);

                return product;
        }

        private ProductResponse createProductResponse(Product product) {
                ProductResponse productResponse = new ProductResponse();
                productResponse.setStatus("OK");
                productResponse.setMessage("Product created");
                productResponse.setProduct(product);

                return productResponse;

        }

        private ProductResponse deleteProductResponse(Product product) {
                ProductResponse productResponse = new ProductResponse();
                productResponse.setStatus("OK");
                productResponse.setMessage("Product deleted");
                productResponse.setProduct(product);

                return productResponse;

        }

        private ProductResponse updateProductResponse(Product product) {
                ProductResponse productResponse = new ProductResponse();
                productResponse.setStatus("OK");
                productResponse.setMessage("Product updated");
                productResponse.setProduct(product);

                return productResponse;

        }
}