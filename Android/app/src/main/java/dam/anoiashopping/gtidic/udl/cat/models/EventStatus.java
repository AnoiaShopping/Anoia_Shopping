package dam.anoiashopping.gtidic.udl.cat.models;
import dam.anoiashopping.gtidic.udl.cat.R;

public enum EventStatus {
    O("O","open"),C("C","closed"),G("G","ongoing"), U("U", "undefined");

    String name;
    String id;

    EventStatus(String _id, String _name){
        id = _id;
        name = _name;
    }

    public String getName(){
        return name;
    }

    public static int getColourResource(EventStatus e){
        switch(e){
            case O:
                return R.color.C1;
            case C:
                return R.color.C2;
            case G:
                return R.color.C3;
            default:
                return -1;
        }
    }
}
