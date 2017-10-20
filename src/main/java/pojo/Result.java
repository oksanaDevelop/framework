package pojo;


public class Result implements Comparable {
    private String name;
    private String alpha2_code;
    private String alpha3_code;

    public Result() {

    }

    public Result(String name, String alpha2_code, String alpha3_code) {
        this.name = name;
        this.alpha2_code = alpha2_code;
        this.alpha3_code = alpha3_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2_code() {
        return alpha2_code;
    }

    public void setAlpha2_code(String alpha2_code) {
        this.alpha2_code = alpha2_code;
    }

    public String getAlpha3_code() {
        return alpha3_code;
    }

    public void setAlpha3_code(String alpha3_code) {
        this.alpha3_code = alpha3_code;
    }



    @Override
    public int compareTo(Object o) {
        Result result = (Result)o;
        int res = getName().compareTo(result.getName());
        if(res!=0)
            return res;
        res = getAlpha2_code().compareTo(result.getAlpha2_code());
        if(res!=0)
            return res;
        res = getAlpha3_code().compareTo(result.getAlpha3_code());
        return res;
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", alpha2_code='" + alpha2_code + '\'' +
                ", alpha3_code='" + alpha3_code + '\'' +
                '}';
    }
}
