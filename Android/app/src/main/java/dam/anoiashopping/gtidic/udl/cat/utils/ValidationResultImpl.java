package dam.anoiashopping.gtidic.udl.cat.utils;

public class ValidationResultImpl {

    //TODO

    private final String msgError;
    private final boolean isError;

    public ValidationResultImpl (String msgError, boolean isError) {
        this.msgError = msgError;
        this.isError  = isError;
    }

    public String getMsgError() {
        return this.msgError;
    }

    public boolean isError() {
        return this.isError;
    }
}
