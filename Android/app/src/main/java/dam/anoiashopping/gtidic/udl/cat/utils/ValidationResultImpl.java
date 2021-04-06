package dam.anoiashopping.gtidic.udl.cat.utils;

public class ValidationResultImpl {

    //TODO

    private final int msgError;
    private final boolean isValid;

    public ValidationResultImpl (int msgError, boolean isValid) {
        this.msgError = msgError;
        this.isValid  = isValid;
    }

    public int getMsgError() {
        return this.msgError;
    }

    public boolean isValid() {
        return this.isValid;
    }
}
