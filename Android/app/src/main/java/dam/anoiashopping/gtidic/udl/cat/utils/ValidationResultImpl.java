package dam.anoiashopping.gtidic.udl.cat.utils;

public class ValidationResultImpl {

    //TODO

    private final String msgError;
    private final boolean isValid;

    public ValidationResultImpl (String msgError, boolean isValid) {
        this.msgError = msgError;
        this.isValid  = isValid;
    }

    public String getMsgError() {
        return this.msgError;
    }

    public boolean isValid() {
        return this.isValid;
    }
}
