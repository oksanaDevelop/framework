package pojo;


public class Result {
    private String name;
    private String alpha2_code;
    private String alpha3_code;

    public Result(String name, String alpha2_code, String alpha3_code) {
        this.name = name;
        this.alpha2_code = alpha2_code;
        this.alpha3_code = alpha3_code;
    }

    private String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String getAlpha2_code() {
        return alpha2_code;
    }

    public void setAlpha2_code(String alpha2_code) {
        this.alpha2_code = alpha2_code;
    }

    private String getAlpha3_code() {
        return alpha3_code;
    }

    public void setAlpha3_code(String alpha3_code) {
        this.alpha3_code = alpha3_code;
    }

    @Override
    public boolean equals(Object obj) {
        Result result = (Result) obj;
        return (getName().equals(result.getName()) && getAlpha2_code().equals(result.getAlpha2_code()) && getAlpha3_code().equals(result.getAlpha3_code()));
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
