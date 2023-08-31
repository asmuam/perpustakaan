/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.perpustakaan.rpc;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonRpcRequest {

    private String jsonrpc;
    private String method;
    private JsonNode params;
    private String id;
//tambahkan method getter dan setter

    public String getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public JsonNode getParams() {
        return params;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setParams(JsonNode params) {
        this.params = params;
    }
    
}
