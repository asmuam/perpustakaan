/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.perpustakaan.rpc;

/**
 *
 * @author asmua
 */
public class JsonRpcResponse {

    private String jsonrpc;
    private Object result;
    private Object error;
    private String id;
//tambahkan method getter dan setter

    public JsonRpcResponse(Object result, String id) {
        this.result = result;
        this.id = id;
    }

    public JsonRpcResponse(String jsonrpc, Double result, Object error, String id) {
        this.jsonrpc = jsonrpc;
        this.result = result;
        this.error = error;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Object getError() {
        return error;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

}
