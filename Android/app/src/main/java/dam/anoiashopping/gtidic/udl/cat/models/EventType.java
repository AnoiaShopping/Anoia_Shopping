package dam.anoiashopping.gtidic.udl.cat.models;

import dam.anoiashopping.gtidic.udl.cat.R;

public enum EventType {

    H("H","hackathon"),LP("LP","lanparty"),LC("LC","livecoding");

    String name;

    String id;



    EventType(String _id, String _name){

        id = _id;

        name = _name;

    }

    public static int getImageResource(EventType e){



        switch(e){

            case H:

                return R.drawable.avataricon6;

            case LP:

                return R.drawable.as1;

            case LC:

                return R.drawable.dibuixfotoperfil;

            default:

                return -1;

        }



    }
}
