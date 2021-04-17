package dam.anoiashopping.gtidic.udl.cat.utils;

public class ResultImpl {

    private final int msgError;
    private final boolean isValid;

    public ResultImpl(int msgError, boolean isValid) {
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
