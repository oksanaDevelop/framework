package pojo;


import java.util.Arrays;

public class RestResponse implements Comparable {
    String[] messages;
    Result result;
    int responseCode;
    String name;
    String alpha2_code;
    String alpha3_code;


    public RestResponse(){
        System.out.println("fuck");

    }


    public RestResponse(String messages, Result result, int responseCode) {
        this.messages= new String[]{messages};
        this.result = result;
        this.responseCode = responseCode;
        System.out.println("fuck");

    }

    public RestResponse(int responseCode, String name, String alpha2_code, String alpha3_code, String... messages) {
        this.name = name;
        this.result = new Result(name, alpha2_code, alpha3_code);
        this.responseCode = responseCode;
        this.messages= messages;

    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
        System.out.println("fuck");

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
    public int compareTo(Object o) {
        RestResponse restResponse = (RestResponse) o;
        int res = getMessages()[0].compareTo(restResponse.getMessages()[0]);
        if (res!=0)
            return res;
        res = responseCode-restResponse.getResponseCode();
        if (res!=0)
            return res;
        return getResult().compareTo(restResponse.getResult());
    }
}

