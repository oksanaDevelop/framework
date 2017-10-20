package pojo;


import java.util.Arrays;

public class RestResponse  {
    String[] messages;
    Result result;
    int responseCode;


    public RestResponse(){
    }


    public RestResponse(String messages, Result result, int responseCode) {
        this.messages= new String[]{messages};
        this.result = result;
        this.responseCode = responseCode;
    }

    public RestResponse(String messages, int responseCode, String name, String alpha2_code, String alpha3_code) {
        this.messages= new String[]{messages};
        this.result = new Result(name, alpha2_code, alpha3_code);
        this.responseCode = responseCode;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "messages=" + Arrays.toString(messages) +
                ", result=" + result +
                ", responseCode=" + responseCode +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        RestResponse restResponse = (RestResponse) obj;
        boolean messageEquality = getMessages()[0].equals(restResponse.getMessages()[0]);
        boolean codeEquality = (getResponseCode()==(restResponse.getResponseCode()));
        boolean resultEquality = getResult().equals(restResponse.getResult());
        return (messageEquality&&codeEquality&&resultEquality);
    }
}

