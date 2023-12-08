package com.julys.eccomerce.eccomerce.response;

import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
  private Long id;
  private String dateOrder;
  private String userEmail;
  private String statusOrder;
  private Double totalOrder;
  private String message;

  public ObjectNode createJson() {

    ObjectMapper mapper = new ObjectMapper();

    ObjectNode json = mapper.createObjectNode();

    json.put("id", this.id);
    json.put("dateOrder", this.dateOrder);
    json.put("userEmail", this.userEmail);
    json.put("statusOrder", this.statusOrder);
    json.put("totalOrder", this.totalOrder);
    json.put("message", this.message);

    return json;
  }

}